package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.CheckResult;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.entity.UrlFilter;

/**
 * Created by xtao on 2015/11/20.
 */
public interface LbsCheck {
    CheckResult check(Location location, UrlFilter urlFilter);
}
