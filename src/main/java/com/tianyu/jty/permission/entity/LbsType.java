package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/20.
 */
public enum LbsType {
    NONE(0),LOCATION(1),AREA(2),LOCATION_AND_AREA(3);
    private Integer code;
    LbsType(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public static LbsType valueOf(Integer code){
        for(LbsType lbsType: LbsType.values()){
            if (lbsType.getCode() == code) return lbsType;
        }
        return null;
    }
}
