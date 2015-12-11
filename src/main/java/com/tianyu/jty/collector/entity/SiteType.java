package com.tianyu.jty.collector.entity;

/**
 * Created by xtao on 2015/11/8.
 */
public enum SiteType {
    HTML(0),JSON(1),JS(2);
    private Integer code;
    private SiteType(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static SiteType codeOf(Integer code){
        for(SiteType type: SiteType.values()) {
            if (type.getCode() == code) return type;
        }
        return null;
    }
}
