package com.tianyu.jty.wechat.entity;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/12/4.
 */
public class DistanceCompare implements Comparable<DistanceCompare> {
    private Double distance;
    private Map<Double, Map<String, String>> map;

    public DistanceCompare(Double distance, Map map) {
        this.distance = distance;
        this.map = map;
    }

    public DistanceCompare() {
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Double, Map<String, String>> getMap() {
        return map;
    }

    public Map<String, String> getSonMap(){
        return map.get(distance);
    }

    public void setMap(Map<Double, Map<String, String>> map) {
        this.map = map;
    }

    public static List<DistanceCompare> convertAndSort(List<Map<Double, Map<String, String>>> mapList){
        List<DistanceCompare> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(mapList)) return list;
        for(Map<Double, Map<String, String>> map: mapList){
            if(map.size() == 1){
                List<Double> keys = Lists.newArrayList(map.keySet());
                list.add(new DistanceCompare(keys.get(0),map));
            }
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public int compareTo(DistanceCompare o) {
        if(this.distance == o.distance) return 0;
        if(this.distance > o.distance){
            return 1;
        }else {
            return -1;
        }
    }
}
