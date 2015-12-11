package com.tianyu.jty.collector.entity;

import org.springframework.util.StringUtils;

/**
 * Created by xtao on 2015/11/27.
 */
public class LocationForConvert {
    private Double lng;
    private Double lat;
    private Double distance = 2500.0;

    public LocationForConvert() {
    }

    public LocationForConvert(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public LocationForConvert(String lng, String lat) {
        if(StringUtils.isEmpty(lng) || StringUtils.isEmpty(lat)){
            lng = "121.481033";
            lat = "31.238802";
        }
        this.lng = Double.valueOf(lng);
        this.lat = Double.valueOf(lat);
    }

    public LocationForConvert(Double lng, Double lat, Double distance) {
        this.lng = lng;
        this.lat = lat;
        this.distance = distance;
    }

    public boolean isEmpty(){
        return lng == null || lat == null;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "LocationForConvert{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", distance=" + distance +
                '}';
    }
}
