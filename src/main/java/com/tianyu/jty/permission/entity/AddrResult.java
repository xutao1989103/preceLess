package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/19.
 */
public class AddrResult {
    private Integer status;
    private AddressResult result;

    public AddrResult(){

    }

    public AddrResult(Integer status, AddressResult result) {
        this.status = status;
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AddressResult getResult() {
        return result;
    }

    public void setResult(AddressResult result) {
        this.result = result;
    }
}
