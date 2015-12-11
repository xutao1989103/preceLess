package com.tianyu.jty.collector.entity;

/**
 * Created by xtao on 2015/11/30.
 */
public class Params {
    private String keyword;
    private Double lng;
    private Double lat;

    public Params() {
    }

    public Params(String keyword, Double lng, Double lat) {
        this.keyword = keyword;
        this.lng = lng;
        this.lat = lat;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
}
