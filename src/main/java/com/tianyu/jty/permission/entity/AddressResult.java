package com.tianyu.jty.permission.entity;

import java.util.List;

/**
 * Created by xtao on 2015/11/19.
 */
public class AddressResult {
    private Integer status;
    private Location location;
    private String formatted_address;
    private String business;
    private AddressComponent addressComponent;
    private List<Poi> pois;

    public AddressResult(){

    }

    public AddressResult(Integer status, Location location, String formatted_address, String business, AddressComponent addressComponent) {
        this.status = status;
        this.location = location;
        this.formatted_address = formatted_address;
        this.business = business;
        this.addressComponent = addressComponent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    @Override
    public String toString() {
        return "AddressResult{" +
                "status=" + status +
                ", location=" + location +
                ", formatted_address='" + formatted_address + '\'' +
                ", business='" + business + '\'' +
                ", addressComponent=" + addressComponent +
                '}';
    }
}
