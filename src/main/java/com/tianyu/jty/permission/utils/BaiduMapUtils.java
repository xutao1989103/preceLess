package com.tianyu.jty.permission.utils;

import com.google.gson.Gson;
import com.tianyu.jty.collector.utils.HttpClientUtil;
import com.tianyu.jty.permission.entity.AddrResult;
import com.tianyu.jty.permission.entity.AddressResult;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.entity.Result;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by xtao on 2015/11/19.
 */
public class BaiduMapUtils {
    private static final String AK = "B14d4b2ee3c07a7e9b021d24cde222c0";
    private static final String BAIDU_MAP_URL = "http://api.map.baidu.com/geocoder/v2/?";
    private static final Integer POIS = 0;
    private static Gson gson = new Gson();
    private static final Double EARTH_RADIUS = 6378.137;

    private static String buildToCoordsUrl(String city, String address) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder(BAIDU_MAP_URL);
        if (!StringUtils.isEmpty(address)) {
            result.append("address=").append(URLEncoder.encode(address, "utf-8"));
        }
        if (!StringUtils.isEmpty(city)) {
            result.append("&city=").append(URLEncoder.encode(city, "utf-8"));
        }
        result.append("&output=json&ak=").append(AK);
        return result.toString();
    }

    private static String buildToAddressUrl(Location location) {
        StringBuilder result = new StringBuilder(BAIDU_MAP_URL);
        if (location != null) {
            result.append("location=").append(location.getLat()).append(",").append(location.getLng());
        }
        result.append("&pois=1&output=json&ak=").append(AK);
        return result.toString();
    }

    public static String addressToCoords(String address) {
        return addressToCoords(null, address);
    }

    public static String addressToCoords(String city, String buildingName) {
        String result = "{}";
        try {
            result = HttpClientUtil.getInctence().doGet(buildToCoordsUrl(city, buildingName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String coordsToAddress(Location location) {
        return HttpClientUtil.getInctence().doGet(buildToAddressUrl(location));
    }

    public static Location addressToLocation(String address) {
        String str = addressToCoords(address);
        Result result = gson.fromJson(str, Result.class);
        if(result!=null && result.getResult()!=null){
            return result.getResult().getLocation();
        }
        return new Location();
    }

    public static AddressResult locationToAddress(Location location) {
        String str = coordsToAddress(location);
        AddrResult result = gson.fromJson(str, AddrResult.class);
        return result.getResult();
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(Location loc1, Location loc2) {
        if(loc1 == null || loc2 == null ||loc1.isEmpty() || loc2.isEmpty()) return 0;
        double radLat1 = rad(loc1.getLat());
        double radLat2 = rad(loc2.getLat());
        double a = radLat1 - radLat2;
        double b = rad(loc1.getLng()) - rad(loc2.getLng());
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = s*1000;
        return s;
    }

    public static void main(String[] args) {
        String str = addressToCoords("曹安公路4800号");

        Result result = gson.fromJson(str, Result.class);
        System.out.println(result.getResult().getLocation());
        Location loc = new Location(result.getResult().getLocation().getLng(), result.getResult().getLocation().getLat());
        String res = coordsToAddress(loc);
        AddrResult addrResult = gson.fromJson(res, AddrResult.class);
        System.out.println(addrResult.getResult());
    }
}
