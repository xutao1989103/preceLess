package com.tianyu.jty.wechat.msg.req;

import com.tianyu.jty.wechat.util.MessageBuilder;

/**
 * Created by xtao on 2015/11/24.
 */
public class MenuEvent extends BaseEvent {
    private String eventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

    /**
     * 得到事件KEY值，与自定义菜单接口中KEY值对应
     */
    public String getEventKey() {
        return eventKey;
    }

    @Override
    /**
     * 得到事件类型，有CLICK和VIEW两种
     */
    public String getEvent() {
        return super.getEvent();
    }

    public MenuEvent(String eventKey) {
        super();
        this.eventKey = eventKey;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toString());
        mb.addData("EventKey", eventKey);
        return mb.toString();
    }

    @Override
    public String toString() {
        return "MenuEvent [eventKey=" + eventKey + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + "]";
    }
}
