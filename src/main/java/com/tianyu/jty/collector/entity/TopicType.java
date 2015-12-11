package com.tianyu.jty.collector.entity;

/**
 * Created by xtao on 2015/11/25.
 */
public enum TopicType {
    MOVIE(0),GROUP(1),TICKET(2),CAR(3),TRIP(4),HOME(5);
    private Integer code;

    public Integer getCode(){
        return this.code;
    }

    TopicType(Integer code){
        this.code = code;
    }

    public static TopicType ofCode(Integer code) {
        for(TopicType type:TopicType.values()){
            if(type.getCode() == code) return type;
        }
        return null;
    }
}
