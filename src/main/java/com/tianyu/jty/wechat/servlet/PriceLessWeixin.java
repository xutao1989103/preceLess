package com.tianyu.jty.wechat.servlet;

import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.service.MessageService;
import com.tianyu.jty.wechat.msg.BaseMsg;
import com.tianyu.jty.wechat.msg.TextMsg;
import com.tianyu.jty.wechat.msg.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Created by xtao on 2015/11/24.
 */
@WebServlet(urlPatterns = "/wechat")
public class PriceLessWeixin extends WeixinServletSupport {

    private static final String TOKEN = "xutaoWechat";

    private static WebApplicationContext context;

    protected MessageService messageService ;

    @Override
    public void init() throws ServletException{
        messageService= (MessageService)findBean("messageService");
    }

    private Object findBean(String beanName) {
        if(context == null){
            context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        }
        return context.getBean(beanName);
    }

    @Override
    protected String getToken() {
        return TOKEN;
    }

    @Override
    protected BaseMsg handleSubscribe(BaseEvent event){
        return new TextMsg("欢迎订阅 3+ 回复类型+内容就能得到你想要的比价结果，开始体验吧！");
    }

    @Override
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        return new TextMsg("感谢您的使用，我们会一直进步！");
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg, LocationForConvert location) {
        WechatReqMsg reqMsg = new WechatReqMsg(msg);
        String result = "";
        try {
            result = messageService.getMessageByReqMsg(reqMsg, location);
        }catch (Exception e){
            e.printStackTrace();
            result = e.getMessage();
        }
        if(location == null){
            result = "请发送一个位置消息获得更好服务!\n" + result;
        }
        return new TextMsg(result);
    }

    @Override
    protected BaseMsg handleLocationMsg(LocationReqMsg msg) {
        WechatReqMsg reqMsg = new WechatReqMsg(msg);
        String result = "";
        try {
            result = messageService.getMessageByReqMsg(reqMsg,new LocationForConvert(msg.getLocationY(),msg.getLocationX()));
        }catch (Exception e){
            e.printStackTrace();
            result = e.getMessage();
        }
        return new TextMsg(result);
    }

    @Override
    protected BaseMsg handleMenuClickEvent(MenuEvent event) {
        BaseMsg respMsg = onMenuClick(event.getEventKey());
        return responseMsgOrDefault(respMsg, event);
    }

    /**
     * 回应菜单点击事件
     *
     * @param eventKey
     *            事件KEY值，与自定义菜单接口中KEY值对应
     *
     * @return 回应用户的消息
     */
    private BaseMsg onMenuClick(String eventKey) {
        if(EventKey.HELP.equals(eventKey)){
            return new TextMsg("发送一个位置消息，您就能看到附近的比价信息啦！在比价之前发送位置会获得更好的体验哦。。");
        }else if(EventKey.GOOD.equals(eventKey)){
            return new TextMsg("感谢您的鼓励，我们会做的更好！");
        }
        return null;
    }

    /**
     * 如果respEvent不为null，则返回respEvent，否则返回默认消息
     */
    private BaseMsg responseMsgOrDefault(BaseMsg respMsg, BaseEvent reqEvent) {
        if (respMsg != null) {
            return respMsg;
        }

        return handleDefaultEvent(reqEvent);
    }

}
