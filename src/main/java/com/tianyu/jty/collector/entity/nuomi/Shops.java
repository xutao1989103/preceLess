package com.tianyu.jty.collector.entity.nuomi;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
public class Shops {
    private Integer total;
    private List<Shop> shops;

    public Shops() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
