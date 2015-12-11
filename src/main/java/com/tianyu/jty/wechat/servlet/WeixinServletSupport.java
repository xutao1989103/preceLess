package com.tianyu.jty.wechat.servlet;

import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.wechat.msg.BaseMsg;
import com.tianyu.jty.wechat.msg.req.*;
import com.tianyu.jty.wechat.util.ChangeCharset;
import com.tianyu.jty.wechat.util.MessageUtil;
import com.tianyu.jty.wechat.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xtao on 2015/11/24.
 */
@SuppressWarnings("serial")
public abstract class WeixinServletSupport extends HttpServlet {

    private static final String LOCATION_STATUS_HAVA = "have";
    private static final String LOCATION_STATUS_NO = "no";
    private static Map userLocaion = new HashMap<String, Object>();

    /**
     * 该方法返回token
     */
    protected abstract String getToken();

    /**
     * 该方法的唯一作用是验证来自微信服务器的消息<br>
     * 如果没有特殊需要，不建议重写该方法
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=UTF-8");
        // 确认请求来自微信服务器
        if (isLegal(request)) {
            PrintWriter out = response.getWriter();
            out.print(request.getParameter("echostr"));
            out.close();
        } else {
            ChangeCharset changeCharset = new ChangeCharset();
            PrintWriter out = response.getWriter();
            out.print(changeCharset.toUTF_8("请求非法"));
            out.close();
        }
    }

    /**
     * 该方法验证消息是否来自微信服务器，并处理来自微信服务器的消息<br>
     * 如果没有特殊需要，不建议重写该方法
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
//        if (!isLegal(request)) {
//            // 非法请求，默认不予响应
//            return;
//        }

        // 处理消息
        String resp = processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }

    private String saveLocation(HttpServletRequest request,Map<String, String> reqMap) {
        String fromUserName = reqMap.get("FromUserName");
        String msgType = reqMap.get("MsgType");
        if(msgType.equals(ReqType.LOCATION)){
            double latitude = Double.parseDouble(reqMap.get("Location_X"));
            double longitude = Double.parseDouble(reqMap.get("Location_Y"));
            LocationForConvert location = new LocationForConvert(longitude,latitude);
            userLocaion.put(fromUserName, location);
            return LOCATION_STATUS_HAVA;
        }else {
            if(userLocaion.get(fromUserName) == null){
                return LOCATION_STATUS_NO;
            }else {
                return LOCATION_STATUS_HAVA;
            }
        }
    }

    /**
     * 处理消息
     */
    private String processRequest(HttpServletRequest request) {

        Map<String, String> reqMap = MessageUtil.parseXml(request);
        String locStatus = saveLocation(request, reqMap);
        String fromUserName = reqMap.get("FromUserName");
        String toUserName = reqMap.get("ToUserName");
        String msgType = reqMap.get("MsgType");
        LocationForConvert location = new LocationForConvert();
        if(userLocaion.get(fromUserName) != null){
            location = (LocationForConvert)userLocaion.get(fromUserName);
        }

        BaseMsg msg = null;// 要发送的消息

        // 事件推送
        if (msgType.equals(ReqType.EVENT)) {
            // 事件类型
            String eventType = reqMap.get("Event");

            // 二维码事件
            String ticket = reqMap.get("Ticket");
            if (ticket != null) {
                String eventKey = reqMap.get("EventKey");
                QrCodeEvent event = new QrCodeEvent(eventKey, ticket);
                buildBasicEvent(reqMap, event);
                msg = handleQrCodeEvent(event);
            }
            // 订阅
            if (eventType.equals(EventType.SUBSCRIBE)) {
                BaseEvent event = new BaseEvent();
                buildBasicEvent(reqMap, event);
                msg = handleSubscribe(event);
            }
            // 取消订阅
            else if (eventType.equals(EventType.UNSUBSCRIBE)) {
                BaseEvent event = new BaseEvent();
                buildBasicEvent(reqMap, event);
                msg = handleUnsubscribe(event);
            }
            // 点击菜单拉取消息时的事件推送
            else if (eventType.equals(EventType.CLICK)) {
                String eventKey = reqMap.get("EventKey");
                MenuEvent event = new MenuEvent(eventKey);
                buildBasicEvent(reqMap, event);
                msg = handleMenuClickEvent(event);
            }
            // 点击菜单跳转链接时的事件推送
            else if (eventType.equals(EventType.VIEW)) {
                String eventKey = reqMap.get("EventKey");
                MenuEvent event = new MenuEvent(eventKey);
                buildBasicEvent(reqMap, event);
                msg = handleMenuViewEvent(event);
            }
            // 上报地理位置事件
            else if (eventType.equals(EventType.LOCATION)) {
                double latitude = Double.parseDouble(reqMap.get("Latitude"));
                double longitude = Double.parseDouble(reqMap.get("Longitude"));
                double precision = Double.parseDouble(reqMap.get("Precision"));
                LocationEvent event = new LocationEvent(latitude, longitude,
                        precision);
                buildBasicEvent(reqMap, event);
                msg = handleLocationEvent(event);
            }

        } else {// 接受普通消息

            // 文本消息
            if (msgType.equals(ReqType.TEXT)) {
                String content = reqMap.get("Content");
                TextReqMsg textReqMsg = new TextReqMsg(content);
                buildBasicReqMsg(reqMap, textReqMsg);
                msg = handleTextMsg(textReqMsg, location);
            }
            // 图片消息
            else if (msgType.equals(ReqType.IMAGE)) {
                String picUrl = reqMap.get("PicUrl");
                String mediaId = reqMap.get("MediaId");
                ImageReqMsg imageReqMsg = new ImageReqMsg(picUrl, mediaId);
                buildBasicReqMsg(reqMap, imageReqMsg);
                msg = handleImageMsg(imageReqMsg);
            }
            // 音频消息
            else if (msgType.equals(ReqType.VOICE)) {
                String format = reqMap.get("Format");
                String mediaId = reqMap.get("MediaId");
                String recognition = reqMap.get("Recognition");
                VoiceReqMsg voiceReqMsg = new VoiceReqMsg(mediaId, format,
                        recognition);
                buildBasicReqMsg(reqMap, voiceReqMsg);
                msg = handleVoiceMsg(voiceReqMsg);
            }
            // 视频消息
            else if (msgType.equals(ReqType.VIDEO)) {
                String thumbMediaId = reqMap.get("ThumbMediaId");
                String mediaId = reqMap.get("MediaId");
                VideoReqMsg videoReqMsg = new VideoReqMsg(mediaId, thumbMediaId);
                buildBasicReqMsg(reqMap, videoReqMsg);
                msg = handleVideoMsg(videoReqMsg);
            }
            // 地理位置消息
            else if (msgType.equals(ReqType.LOCATION)) {
                double locationX = Double.parseDouble(reqMap.get("Location_X"));
                double locationY = Double.parseDouble(reqMap.get("Location_Y"));
                int scale = Integer.parseInt(reqMap.get("Scale"));
                String label = reqMap.get("Label");
                LocationReqMsg locationReqMsg = new LocationReqMsg(locationX,
                        locationY, scale, label);
                buildBasicReqMsg(reqMap, locationReqMsg);
                msg = handleLocationMsg(locationReqMsg);
            }
            // 链接消息
            else if (msgType.equals(ReqType.LINK)) {
                String title = reqMap.get("Title");
                String description = reqMap.get("Description");
                String url = reqMap.get("Url");
                LinkReqMsg linkReqMsg = new LinkReqMsg(title, description, url);
                buildBasicReqMsg(reqMap, linkReqMsg);
                msg = handleLinkMsg(linkReqMsg);
            }

        }

        if (msg == null) {
            // 回复空串是微信的规定，代表不回复
            return "";
        }

        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        return msg.toXml();
    }

    /**
     * 处理文本消息
     */
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理文本消息
     */
    protected BaseMsg handleTextMsg(TextReqMsg msg, LocationForConvert location) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理图片消息
     */
    protected BaseMsg handleImageMsg(ImageReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理语音消息
     */
    protected BaseMsg handleVoiceMsg(VoiceReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理视频消息
     */
    protected BaseMsg handleVideoMsg(VideoReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理地理位置消息
     */
    protected BaseMsg handleLocationMsg(LocationReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理链接消息
     */
    protected BaseMsg handleLinkMsg(LinkReqMsg msg) {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理扫描带参数二维码事件
     */
    protected BaseMsg handleQrCodeEvent(QrCodeEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理上报地理位置事件
     */
    protected BaseMsg handleLocationEvent(LocationEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理点击菜单拉取消息时的事件推送
     */
    protected BaseMsg handleMenuClickEvent(MenuEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理点击菜单跳转链接时的事件推送
     */
    protected BaseMsg handleMenuViewEvent(MenuEvent event) {
        return handleDefaultEvent(event);
    }

    /**
     * 处理订阅事件<br>
     * 默认不回复
     */
    protected BaseMsg handleSubscribe(BaseEvent event) {
        return null;
    }

    /**
     * 处理取消订阅事件<br>
     * 默认不回复
     */
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        return null;
    }

    /**
     * 处理消息的默认方式<br>
     * 如果不重写该方法，则默认不返回任何消息
     */
    protected BaseMsg handleDefaultMsg(BaseReqMsg msg) {
        return null;
    }

    /**
     * 设置处理事件的默认方式<br>
     * 如果不重写该方法，则默认不返回任何消息
     */
    protected BaseMsg handleDefaultEvent(BaseEvent event) {
        return null;
    }

    /**
     * 为事件普通消息对象添加基本参数<br>
     * 参数包括：MsgId、MsgType、FromUserName、ToUserName和CreateTime
     */
    private void buildBasicReqMsg(Map<String, String> reqMap, BaseReqMsg reqMsg) {
        addBasicReqParams(reqMap, reqMsg);
        reqMsg.setMsgId(reqMap.get("MsgId"));
    }

    /**
     * 为事件推送对象添加基本参数<br>
     * 参数包括：Event、MsgType、FromUserName、ToUserName和CreateTime
     */
    private void buildBasicEvent(Map<String, String> reqMap, BaseEvent event) {
        addBasicReqParams(reqMap, event);
        event.setEvent(reqMap.get("Event"));
    }

    /**
     * 为请求对象添加基本参数，包括MsgType、FromUserName、ToUserName和CreateTime<br>
     * 请求对象包括普通消息和事件推送
     */
    private void addBasicReqParams(Map<String, String> reqMap, BaseReq req) {
        req.setMsgType(reqMap.get("MsgType"));
        req.setFromUserName(reqMap.get("FromUserName"));
        req.setToUserName(reqMap.get("ToUserName"));
        req.setCreateTime(Long.parseLong(reqMap.get("CreateTime")));
    }

    /**
     * 判断请求是否来自微信服务器
     */
    private boolean isLegal(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(getToken(), signature, timestamp, nonce);
    }
}
