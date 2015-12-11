package com.tianyu.jty.collector.entity.douban;

/**
 * Created by xtao on 2015/12/7.
 */
public class Rating {
    private Integer max;
    private Float average;
    private Integer stars;
    private Integer min;

    public Rating() {
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
