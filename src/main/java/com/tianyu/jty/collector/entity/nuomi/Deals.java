package com.tianyu.jty.collector.entity.nuomi;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
public class Deals {
    private Integer total;
    private List<Deal> deals;

    public Deals() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}
