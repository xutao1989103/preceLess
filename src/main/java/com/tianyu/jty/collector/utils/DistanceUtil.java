package com.tianyu.jty.collector.utils;

import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by xtao on 2015/11/30.
 */
public class DistanceUtil implements Callable<Map<Double,Map>> {
    private Map<String,String> item;
    private LocationForConvert location;

    public DistanceUtil() {
    }

    public DistanceUtil(Map<String, String> item, LocationForConvert location) {
        this.item = item;
        this.location = location;
    }

    @Override
    public Map<Double, Map> call() throws Exception {
        Map<Double, Map> result = Maps.newHashMap();
        result.put(getDistance(item,location), item);
        return result;
    }

    private Double getDistance(Map<String,String> item,LocationForConvert location){
        String address = item.get("address");
        if(StringUtils.isEmpty(address) || location.isEmpty()) return getRandom();
        Location shopLocation =  BaiduMapUtils.addressToLocation(address);
        if(shopLocation.isEmpty()) return getRandom();
        Location wechatLocaiton = new Location(location.getLng(),location.getLat());
        Double realDistance =  BaiduMapUtils.getDistance(shopLocation, wechatLocaiton);
        return realDistance;
    }

    private Double getRandom(){
        Random random = new Random();
        double bigHalf = random.nextDouble()*0.5 + 0.5;
        return Double.MAX_VALUE * bigHalf;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }

    public void setLocation(LocationForConvert location) {
        this.location = location;
    }
}
