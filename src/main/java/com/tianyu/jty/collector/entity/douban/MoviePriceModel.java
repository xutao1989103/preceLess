package com.tianyu.jty.collector.entity.douban;

/**
 * Created by xtao on 2015/12/7.
 */
public class MoviePriceModel {
    private String webSite;
    private Integer lowest;
    private String lowestStr;
    private Integer highest;
    private String highestStr;
    private Integer average;
    private String averageStr;
    private String discount;
    private String mark;

    public MoviePriceModel() {
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Integer getLowest() {
        return lowest;
    }

    public void setLowest(Integer lowest) {
        this.lowest = lowest;
    }

    public String getLowestStr() {
        return lowestStr;
    }

    public void setLowestStr(String lowestStr) {
        this.lowestStr = lowestStr;
    }

    public Integer getHighest() {
        return highest;
    }

    public void setHighest(Integer highest) {
        this.highest = highest;
    }

    public String getHighestStr() {
        return highestStr;
    }

    public void setHighestStr(String highestStr) {
        this.highestStr = highestStr;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public String getAverageStr() {
        return averageStr;
    }

    public void setAverageStr(String averageStr) {
        this.averageStr = averageStr;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
