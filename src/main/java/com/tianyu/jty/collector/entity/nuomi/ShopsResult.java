package com.tianyu.jty.collector.entity.nuomi;

/**
 * Created by xtao on 2015/11/30.
 */
public class ShopsResult {
    private Integer errno;
    private String msg;
    private Shops data;

    public ShopsResult() {
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

    public Shops getData() {
        return data;
    }

    public void setData(Shops data) {
        this.data = data;
    }
}
