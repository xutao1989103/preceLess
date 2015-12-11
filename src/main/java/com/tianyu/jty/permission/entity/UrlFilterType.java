package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/17.
 */
public enum UrlFilterType {
    NONE(0),URL(1),URL_METHOD(2);
    private Integer code;

    UrlFilterType(Integer code) {
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }
}
