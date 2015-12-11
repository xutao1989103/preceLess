package com.tianyu.jty.collector.entity.nuomi;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
public class DealShops {
    private Integer errno;
    private String msg;
    private List<Shop> shops;

    public DealShops() {
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
