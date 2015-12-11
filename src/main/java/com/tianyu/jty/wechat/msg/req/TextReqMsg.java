package com.tianyu.jty.wechat.msg.req;

import com.tianyu.jty.wechat.util.MessageBuilder;

/**
 * Created by xtao on 2015/11/24.
 */
public class TextReqMsg extends BaseReqMsg {
    private String content;// 文本消息内容

    /**
     * 得到文本消息内容
     */
    public String getContent() {
        return content;
    }

    public TextReqMsg(String content) {
        super();
        this.content = content;
        setMsgType(ReqType.TEXT);
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", ReqType.TEXT);
        mb.addData("Content", content);
        mb.addTag("MsgId", msgId);
        mb.surroundWith("xml");
        return mb.toString();
    }

    @Override
    public String toString() {
        return "TextReqMsg [content=" + content + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }
}
