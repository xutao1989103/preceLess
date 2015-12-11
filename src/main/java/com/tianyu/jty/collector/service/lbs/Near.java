package com.tianyu.jty.collector.service.lbs;

import com.tianyu.jty.collector.entity.LocationForConvert;

/**
 * Created by xtao on 2015/11/30.
 */
public interface Near<O> {
    O getNear(LocationForConvert location);
}
