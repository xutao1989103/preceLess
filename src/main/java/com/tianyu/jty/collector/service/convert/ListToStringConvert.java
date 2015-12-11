package com.tianyu.jty.collector.service.convert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.Website;
import com.tianyu.jty.collector.utils.DistanceUtil;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import com.tianyu.jty.wechat.msg.req.LocationEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by xtao on 2015/11/27.
 */
@Service
public class ListToStringConvert implements Convert<List<Map<String, Object>>, String> {

    private static final Integer TOP_NUM = 2;
    private static final String DIAN_PING_STR = "dianping";

    @Override
    public String convert(List<Map<String, Object>> list,LocationForConvert location) {
        StringBuilder sb = new StringBuilder();
        for(Map<String, Object> map : list) {
            try {
                Website website = (Website)map.get("website");
                List<Map<String, String>> details = (List<Map<String, String>>) map.get("details");
                if(details == null) details = Lists.newArrayList();
                cleanList(details);
                if(details.size() > TOP_NUM){
                    details = findTops(details, location);
                }
                sb.append(website.getName()).append(": ");
                buildPriceInfo(website, sb, details);
                sb.append("\n");
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return sb.toString();
    }

    private void buildPriceInfo(Website website, StringBuilder sb, List<Map<String, String>> details) {
        if(DIAN_PING_STR.equals(website.getName())){
            for(Map<String,String> detail : details){
                String shopName = detail.get("shopName");
                shopName = shopName.substring(0, shopName.indexOf(")") + 1);
                String price = detail.get("price");
                price = buildDianpingPrice(price);
                String address = detail.get("address");
                sb.append(shopName).append("").append(price);
            }
        }else {
            for(Map<String,String> detail : details){
                String shopName = detail.get("shopName");
                String price = detail.get("price");
                String address = detail.get("address");
                sb.append(shopName).append("").append(price).append(";");
            }
        }
    }

    private String buildDianpingPrice(String price) {
        StringBuilder sb = new StringBuilder();
        String[] prices = price.split(" ");
        prices[0] = "";
        if(prices != null && prices.length > 0){
            for(String str :prices){
                try {
                    sb.append(str).append("\n");
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return sb.toString();
    }

    private void cleanList(List<Map<String, String>> list){
        if(list == null) return;
        for(int i=0; i< list.size();i++){
            if(list.get(i).size()==0) list.remove(i);
        }
    }

    private List findTops(List<Map<String, String>> list,LocationForConvert location){
        List<Map<String, String>> tops = Lists.newArrayList();
        Map<Double, Map> temps = Maps.newHashMap();
        if(location.isEmpty()){
            for(int i = 0; i< TOP_NUM; i++){
                tops.add(list.get(i));
            }
            return tops;
        }
        List<Double> dis = Lists.newArrayList();
        List<Future> futures = Lists.newArrayList();
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Map<Double, Map<String, String>>> all = Lists.newArrayList();
        for(Map map: list){
            DistanceUtil du = new DistanceUtil(map,location);
            Future future = executor.submit(du);
            futures.add(future);
        }
        executor.shutdown();
        getFutures(all, futures);
        for(Map<Double, Map<String, String>> a :all){
            dis.addAll(a.keySet());
            temps.putAll(a);
        }
        Collections.sort(dis);
        System.out.println(dis);
        for(int i = 0; i< TOP_NUM; i++){
            tops.add(temps.get(dis.get(i)));
        }
        return tops;
    }

    private void getFutures(List all, List<Future> futures){
        for (Future future: futures){
            try {
                all.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private Double getDistance(Map<String,String> item,LocationForConvert location){
        String address = item.get("address");
        if(StringUtils.isEmpty(address) || location.isEmpty()) return Double.MAX_VALUE;
        Location shopLocation =  BaiduMapUtils.addressToLocation(address);
        if(shopLocation.isEmpty()) return Double.MAX_VALUE;
        Location wechatLocaiton = new Location(location.getLng(),location.getLat());
        Double realDistance =  BaiduMapUtils.getDistance(shopLocation, wechatLocaiton);
        return realDistance;
    }

    private boolean weatherAdd(String shopName, String price,String address, LocationForConvert location){
        if(StringUtils.isEmpty(shopName) || StringUtils.isEmpty(price)) return false;
        if(StringUtils.isEmpty(address)) return true;
        if(location == null){
            return true;
        }else {
            Location shopLocation =  BaiduMapUtils.addressToLocation(address);
            Location wechatLocaiton = new Location(location.getLng(),location.getLat());
            Double realDistance =  BaiduMapUtils.getDistance(shopLocation, wechatLocaiton);
            System.out.println(location.getDistance() - realDistance);
            return realDistance <= location.getDistance();
        }
    }
}
