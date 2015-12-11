package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.*;
import com.tianyu.jty.permission.utils.BaiduMapUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xtao on 2015/11/20.
 */
public class AreaChecker extends AbstractLbsChecker {
    @Override
    public CheckResult check(Location location, UrlFilter urlFilter) {
        List<String> areas = Arrays.asList(urlFilter.getArea().split(","));
        AddressResult address = BaiduMapUtils.locationToAddress(location);
        if(!isInArea(areas, address)){
            return new CheckResult(false, " Not In Area");
        }
        return new CheckResult(true, "OK");
    }
}
