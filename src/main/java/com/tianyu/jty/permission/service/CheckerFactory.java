package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.LbsType;


/**
 * Created by xtao on 2015/11/20.
 */
public class CheckerFactory {
    public static LbsCheck getChecker(LbsType type){
        switch (type){
            case NONE: return null;
            case LOCATION: return new LocationChecker();
            case AREA: return new AreaChecker();
            case LOCATION_AND_AREA: return new LocAndAreaChecker();
            default: return new LocAndAreaChecker();
        }
    }
}
