package com.tianyu.jty.wechat.msg.req;

import com.tianyu.jty.wechat.util.MessageBuilder;

/**
 * Created by xtao on 2015/11/24.
 */
public class BaseEvent extends BaseReq {
    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BaseEvent() {
        setMsgType(ReqType.EVENT);
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", ReqType.EVENT);
        mb.addData("Event", event);
        return mb.toString();
    }
}
