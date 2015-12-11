package com.tianyu.jty.collector.entity.nuomi;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
public class Deal {
    private Integer deal_id;
    private List<Integer> city_ids;
    private String title;
    private String description;
    private String long_title;
    private Integer market_price;
    private Integer current_price;
    private Double score;
    private Integer distance;
    private String image;
    private String deal_url;

    public Deal() {
    }

    public Integer getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(Integer deal_id) {
        this.deal_id = deal_id;
    }

    public List<Integer> getCity_ids() {
        return city_ids;
    }

    public void setCity_ids(List<Integer> city_ids) {
        this.city_ids = city_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLong_title() {
        return long_title;
    }

    public void setLong_title(String long_title) {
        this.long_title = long_title;
    }

    public Integer getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Integer market_price) {
        this.market_price = market_price;
    }

    public Integer getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Integer current_price) {
        this.current_price = current_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeal_url() {
        return deal_url;
    }

    public void setDeal_url(String deal_url) {
        this.deal_url = deal_url;
    }
}
