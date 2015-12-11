package com.tianyu.jty.collector.service.convert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.Website;
import com.tianyu.jty.collector.utils.DistanceUtil;
import com.tianyu.jty.system.service.ConfigService;
import com.tianyu.jty.wechat.entity.DistanceCompare;
import com.tianyu.jty.wechat.entity.LocalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xtao on 2015/12/3.
 */
@Service
public class ListToLocalConvert implements Convert<List<Map<String, Object>>, List<LocalModel>> {

    private static Integer TOP_NUM = 2;
    private static final String DIAN_PING = "dianping";
    private static final String NUO_MI= "nuomi";

    @Autowired
    private ConfigService configService;

    public List<LocalModel> convert(List<Map<String, Object>> list, LocationForConvert location, Integer num){
        TOP_NUM = num;
        return convert(list, location);
    }

    @Override
    public List<LocalModel> convert(List<Map<String, Object>> list, LocationForConvert location) {
        if(CollectionUtils.isEmpty(list)) return Lists.newArrayList();
        List<LocalModel> localModels = Lists.newArrayList();
        for(Map<String, Object> map: list){
            Website website = (Website)map.get("website");
            Object details = map.get("details");
            if(details!=null){
                List<Map<String, String>> items = (List<Map<String, String>>)details;
                if(items == null) items = Lists.newArrayList();
                if(items.size() > TOP_NUM){
                    items = findTops(items, location);
                }
                for(Map<String, String> item: items){
                    if(item!=null){
                        localModels.add(buildLocalModel(website, item));
                    }

                }
            }
        }

        cleanLocalModels(localModels);
        return localModels;
    }

    private void cleanLocalModels(List<LocalModel> list) {
        if(CollectionUtils.isEmpty(list)) return ;
        Iterator<LocalModel> it = list.iterator();
        while (it.hasNext()){
            LocalModel model = it.next();
            if(model.isEmpty()){
                it.remove();
            }
        }
    }

    private LocalModel buildLocalModel(Website website, Map<String, String> map){
        LocalModel localModel = new LocalModel();
        localModel.setWebsiteName(replaceWithConfigs(website.getName()));
        localModel.setWebsiteUrl(website.getSite());
        localModel.setPic(map.get("pic"));
        localModel.setShopName(map.get("shopName"));
        localModel.setAddress(map.get("address"));
        localModel.setDistance(0);
        localModel.setOriginPrice("");
        localModel.setPrice("");
        localModel.setDiscount("");
        localModel.setStars(0);
        localModel.setContent(map.get("price"));
        localModel.setUrl(map.get("url"));

        if(DIAN_PING.equals(website.getName())){
            localModel.setUrl("http://www.dianping.com"+ map.get("url"));
        }
        return localModel;
    }

    private String replaceWithConfigs(String input){
        Map<String, String> configs = configService.getConfigs();
        String result = configs.get(input);
        if(StringUtils.isEmpty(result)){
            return input;
        }else {
            return result;
        }
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


        List<DistanceCompare> compareList = DistanceCompare.convertAndSort(all);
        for(int i = 0; i< TOP_NUM; i++){
            tops.add(compareList.get(i).getSonMap());
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
}
