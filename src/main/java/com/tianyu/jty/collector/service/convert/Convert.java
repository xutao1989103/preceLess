package com.tianyu.jty.collector.service.convert;

import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.wechat.msg.req.LocationEvent;

/**
 * Created by xtao on 2015/11/27.
 */
public interface Convert<F,T> {
     T convert(F f, LocationForConvert location);
}
