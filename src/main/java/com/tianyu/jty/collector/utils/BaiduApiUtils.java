package com.tianyu.jty.collector.utils;

import com.tianyu.jty.collector.entity.LocationForConvert;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.util.StringUtils;

/**
 * Created by xtao on 2015/11/30.
 */
public class BaiduApiUtils {

    private static final String BAIDU_API_BASE = "http://apis.baidu.com/baidunuomi/openapi/";
    private static final String API_KEY = "d836a4a1fd6e2861e3c80636d4377abb";
    private static final String DEAL_DETAIL = "dealdetail?";
    private static final String DEAL_SHOPS = "dealshops?";
    private static final String SERACH_DEALS = "searchdeals?";
    private static final String SEARCH_SHOPS = "searchshops?";
    private static final Integer SH_CITY_ID = 200010000;

    public static String getBaiduNuomiDealDetail(Integer dealId){
        StringBuilder url = new StringBuilder(BAIDU_API_BASE);
        url.append(DEAL_DETAIL);
        url.append("deal_id=").append(dealId);
        Header header = new BasicHeader("apikey", API_KEY);
        return HttpClientUtil.getInctence().doGet(url.toString(),header);
    }

    public static String getBaiduNuomiDealShops(Integer dealId, Integer city_id){
        StringBuilder url = new StringBuilder(BAIDU_API_BASE);
        url.append(DEAL_SHOPS);
        url.append("deal_id=").append(dealId);
        url.append("&city_id=").append(city_id);
        Header header = new BasicHeader("apikey", API_KEY);
        return HttpClientUtil.getInctence().doGet(url.toString(),header);
    }

    public static String searchDeals(String keyword, LocationForConvert location){
        StringBuilder url = new StringBuilder(BAIDU_API_BASE);
        url.append(SERACH_DEALS);
        url.append("keyword=").append(keyword);
        if(location!=null) {
            url.append("&location=").append(location.getLng()).append(",").append(location.getLat());
            url.append("&radius=").append(location.getDistance());
        }
        url.append("&city_id=").append(SH_CITY_ID);
        Header header = new BasicHeader("apikey", API_KEY);
        return HttpClientUtil.getInctence().doGet(url.toString(),header);
    }

    public static String searchShops(String keyword, LocationForConvert location){
        StringBuilder url = new StringBuilder(BAIDU_API_BASE);
        url.append(SEARCH_SHOPS);
        url.append("city_id=").append(SH_CITY_ID);
        url.append("&cat_ids").append("320,326");
        if(!StringUtils.isEmpty(keyword)){
            url.append("&keyword=").append(keyword);
        }
        if(location!=null) {
            url.append("&location=").append(location.getLng()).append(",").append(location.getLat());
            url.append("&radius=").append(location.getDistance());
        }
        Header header = new BasicHeader("apikey", API_KEY);
        return HttpClientUtil.getInctence().doGet(url.toString(),header);
    }


    public static void main(String[] args) {
        System.out.println(searchDeals("小辉哥火锅",new LocationForConvert(121.0,31.0,3000.0)));
    }
}
