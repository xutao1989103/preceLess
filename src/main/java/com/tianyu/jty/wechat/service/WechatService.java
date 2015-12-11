package com.tianyu.jty.wechat.service;

import com.tianyu.jty.wechat.util.PastUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xtao on 2015/12/2.
 */
@Service
public class WechatService {
    private static final String appid = "wx068040701a1427b3";
    private static final String secret = "732f372bccb33b7a84cc6aeb1f8b2041";

    public Map<String, String> getParams(HttpServletRequest request){
        return PastUtil.getParam(appid, secret, request);
    }
}
