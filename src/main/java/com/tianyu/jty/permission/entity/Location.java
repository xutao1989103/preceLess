package com.tianyu.jty.permission.entity;

import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;

/**
 * Created by xtao on 2015/11/19.
 */
public class Location {
    private Double lng;
    private Double lat;

    public Location(){

    }

    public Location(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Location(String lng, String lat) {
        if(!StringUtils.isEmpty(lng)){
            this.lng = Double.valueOf(lng);
        }
        if(!StringUtils.isEmpty(lat)){
            this.lat = Double.valueOf(lat);
        }
    }

    public static Location newLocation(ServletRequest servletRequest){
        String lng = servletRequest.getParameter("lng");
        String lat = servletRequest.getParameter("lat");
        return new Location(lng, lat);
    }

    public boolean isEmpty(){
        return this.lng == null || this.lat == null;
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

    @Override
    public String toString() {
        return "Location{" +
                "lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
