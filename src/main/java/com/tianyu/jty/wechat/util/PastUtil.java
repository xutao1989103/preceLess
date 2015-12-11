package com.tianyu.jty.wechat.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xtao on 2015/12/2.
 */
public class PastUtil {
    public static AccessToken token = null;
    public static String time = null;
    public static String jsapi_ticket = null;
    /**
     *
     * @param appId   公账号appId
     * @param appSecret
     * @return
     */
    public static Map<String, String> getParam(String appId,String appSecret, HttpServletRequest request){
        if(token == null){
            token = SignUtil.getAccessToken(appId, appSecret);
            jsapi_ticket = SignUtil.getJsApiTicket(token.getAccess_token());
            time = getTime();
        }else{
            if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //每小时刷新一次
                token = null;
                token = SignUtil.getAccessToken(appId, appSecret);
                jsapi_ticket = SignUtil.getJsApiTicket(token.getAccess_token());
                time = getTime();
            }
        }

        String url = getUrl(request);
        Map<String, String> params = sign(jsapi_ticket, url);
        params.put("appid", appId);
        return params;
    }

    private static String getUrl(HttpServletRequest request){

        StringBuffer requestUrl = request.getRequestURL();

        String queryString = request.getQueryString();
        String url = requestUrl +"?"+queryString;
        return url;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    //获取当前系统时间 用来判断access_token是否过期
    public static String getTime(){
        Date dt=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
}
