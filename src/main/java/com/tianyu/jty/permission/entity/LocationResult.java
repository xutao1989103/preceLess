package com.tianyu.jty.permission.entity;

import java.util.List;

/**
 * Created by xtao on 2015/11/19.
 */
public class LocationResult {
    private Location location;
    private Integer precise;
    private Integer confidence;
    private String level;

    public LocationResult() {

    }

    public LocationResult(Location location, Integer precise, Integer confidence, String level) {
        this.location = location;
        this.precise = precise;
        this.confidence = confidence;
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getPrecise() {
        return precise;
    }

    public void setPrecise(Integer precise) {
        this.precise = precise;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LocationResult{" +
                "location=" + location +
                ", precise=" + precise +
                ", confidence=" + confidence +
                ", level='" + level + '\'' +
                '}';
    }
}
