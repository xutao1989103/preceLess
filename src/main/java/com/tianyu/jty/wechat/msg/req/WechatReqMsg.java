package com.tianyu.jty.wechat.msg.req;

import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.TopicType;

import java.util.Map;

/**
 * Created by xtao on 2015/11/27.
 */
public class WechatReqMsg {
    private TextReqMsg textReqMsg;
    private LocationReqMsg locationReqMsg;

    public WechatReqMsg() {
    }

    public WechatReqMsg(TextReqMsg textReqMsg) {
        this.textReqMsg = textReqMsg;
    }

    public WechatReqMsg(LocationReqMsg locationReqMsg) {
        this.locationReqMsg = locationReqMsg;
    }

    public WechatReqMsg(TextReqMsg textReqMsg, LocationReqMsg locationReqMsg) {
        this.textReqMsg = textReqMsg;
        this.locationReqMsg = locationReqMsg;
    }

    public TopicType getType(){


        return TopicType.GROUP;
    }

    public Map<String, String> getParams() {
        Map resutl =  Maps.newHashMap();
        resutl.put("name", textReqMsg.getContent());
        resutl.put("lat", locationReqMsg==null?null:locationReqMsg.getLocationX());
        resutl.put("lng", locationReqMsg==null?null:locationReqMsg.getLocationY());
        return resutl;
    }

    public TextReqMsg getTextReqMsg() {
        return textReqMsg;
    }

    public void setTextReqMsg(TextReqMsg textReqMsg) {
        this.textReqMsg = textReqMsg;
    }

    public LocationReqMsg getLocationReqMsg() {
        return locationReqMsg;
    }

    public void setLocationReqMsg(LocationReqMsg locationReqMsg) {
        this.locationReqMsg = locationReqMsg;
    }
}
