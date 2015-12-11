package com.tianyu.jty.collector.entity.nuomi;

/**
 * Created by xtao on 2015/11/30.
 */
public class DealResult {
    private Integer errno;
    private String msg;
    private Deals data;

    public DealResult() {
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

    public Deals getData() {
        return data;
    }

    public void setData(Deals data) {
        this.data = data;
    }
}
