package com.tianyu.jty.wechat.msg.req;

/**
 * Created by xtao on 2015/11/24.
 */
public class BaseReqMsg extends BaseReq {
    String msgId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
