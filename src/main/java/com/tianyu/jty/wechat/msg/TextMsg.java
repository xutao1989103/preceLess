package com.tianyu.jty.wechat.msg;

import com.tianyu.jty.wechat.util.MessageBuilder;

/**
 * Created by xtao on 2015/11/24.
 */
public final class TextMsg extends BaseMsg {
    // 回复的消息内容
    private StringBuilder contentBuilder;

    public String getContent() {
        return contentBuilder.toString();
    }

    public void setContent(String content) {
        contentBuilder = new StringBuilder(content);
    }

    public TextMsg() {
        contentBuilder = new StringBuilder();
    }

    public TextMsg(String content) {
        setContent(content);
    }

    /**
     * 添加消息内容
     */
    public TextMsg add(String text) {
        contentBuilder.append(text);
        return this;
    }

    /**
     * 添加换行符
     */
    public TextMsg addln() {
        return add("\n");
    }

    /**
     * 先添加消息内容text，再添加换行符
     */
    public TextMsg addln(String text) {
        contentBuilder.append(text);
        return addln();
    }

    /**
     * 添加文本为text,链接为url的超链接. 形如
     * <p>
     * &lt;a href=&quot;url&quot;&gt;text&lt;/a&gt;
     * </p>
     */
    public TextMsg addLink(String text, String url) {
        contentBuilder.append("<a href=\"").append(url).append("\">")
                .append(text).append("</a>");
        return this;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("Content", contentBuilder.toString());
        mb.addData("MsgType", RespType.TEXT);
        mb.surroundWith("xml");
        return mb.toString();
    }

    @Override
    public String toString() {
        return "TextMsg [content=" + getContent() + "]";
    }
}
