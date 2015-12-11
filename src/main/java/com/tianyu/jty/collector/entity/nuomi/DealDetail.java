package com.tianyu.jty.collector.entity.nuomi;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
public class DealDetail {
    private Integer errno;
    private String msq;
    public Deal deal;

    public DealDetail() {
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsq() {
        return msq;
    }

    public void setMsq(String msq) {
        this.msq = msq;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
}
