package com.tianyu.jty.wechat.util;

import com.google.gson.Gson;
import com.tianyu.jty.collector.utils.HttpClientUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by xtao on 2015/11/24.
 */
public class SignUtil {
    private static final char[] digit = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static Gson gson = new Gson();
    /**
     * 验证签名
     */
    public static boolean checkSignature(String token, String signature,
                                         String timestamp, String nonce) {
        if (Validator.hasNull(token, signature, timestamp, nonce)) {
            return false;
        }
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equalsIgnoreCase(signature);
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private static String byteToStr(byte[] byteArray) {
        int len = byteArray.length;
        StringBuilder strDigest = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        return new String(tempArr);
    }

    /**
     * 获取接口访问凭证
     *
     * @param appid
     * @param secret
     * @return
     */
    public static AccessToken getAccessToken(String appid, String secret){
        StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
        sb.append("&appid=").append(appid);
        sb.append("&secret=").append(secret);
        String jsonStr = HttpClientUtil.getInctence().doGet(sb.toString());
        AccessToken accessToken = gson.fromJson(jsonStr, AccessToken.class);
        return accessToken;
    }

    /**
     * 调用微信js接口的临时票据
     *
     * @param accessToken
     * @return
     */
    public static String getJsApiTicket(String accessToken) {
        StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi");
        sb.append("&access_token=").append(accessToken);
        String jsonStr = HttpClientUtil.getInctence().doGet(sb.toString());
        Ticket ticket = gson.fromJson(jsonStr, Ticket.class);
        if(ticket!=null && ticket.getErrcode() == 0){
            return ticket.getTicket();
        }
        return "";
    }

}
