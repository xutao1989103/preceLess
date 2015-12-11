package com.tianyu.jty.wechat.entity;

import org.springframework.util.StringUtils;

/**
 * Created by xtao on 2015/12/3.
 */
public class LocalModel {
    private String websiteName;
    private String websiteUrl;
    private String pic;
    private String url;
    private String shopName;
    private String address;
    private Integer distance;
    private String originPrice;
    private String price;
    private String discount;
    private Integer stars;
    private String content;

    public LocalModel() {
    }

    public boolean isEmpty(){
        return StringUtils.isEmpty(websiteName) || StringUtils.isEmpty(shopName) || StringUtils.isEmpty(content);
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
