package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.*;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by xtao on 2015/11/20.
 */
public abstract class AbstractLbsChecker implements LbsCheck {

    protected CheckResult compareDistance(Location location, UrlFilter urlFilter){
        Long distance = urlFilter.getDistance();
        Location loc = BaiduMapUtils.addressToLocation(urlFilter.getLocation());
        Double realDistance = BaiduMapUtils.getDistance(location, loc);
        if(realDistance > distance){
            return new CheckResult(false, "Distance Less " + (realDistance - distance));
        }
        else return new CheckResult(true, "OK");
    }

    protected boolean isInArea(List<String> areas, AddressResult address) {
        boolean isIn = false;
        for(String area: areas){
            if(equalsAddress(area, address)) {
                isIn = true;
                break;
            }
        }
        return isIn;
    }

    protected boolean equalsAddress(String area, AddressResult address) {
        if(StringUtils.isEmpty(area) || address == null) return false;
        AddressComponent component = address.getAddressComponent();
        return !StringUtils.isEmpty(address.getFormatted_address())&&(address.getFormatted_address().indexOf(area) != -1) ||
                !StringUtils.isEmpty(address.getBusiness())&&(address.getBusiness().indexOf(area) != -1) ||
                area.equals(component.getCountry()) ||
                area.equals(component.getProvince()) ||
                area.equals(component.getCity()) ||
                area.equals(component.getDirection()) ||
                area.equals(component.getStreet());
    }
}
