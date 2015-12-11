package com.tianyu.jty.collector.service.extractor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.tianyu.jty.collector.entity.Constants;
import com.tianyu.jty.collector.entity.Site;
import com.tianyu.jty.collector.entity.nuomi.DealDetail;
import com.tianyu.jty.collector.entity.nuomi.DealShops;
import com.tianyu.jty.collector.entity.nuomi.Shop;
import com.tianyu.jty.collector.service.outputs.AbstractOutput;
import com.tianyu.jty.collector.utils.BaiduApiUtils;
import com.tianyu.jty.collector.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by xtao on 2015/11/8.
 */
public class HtmlExtractor extends Extractor {
    private Set<String> keys;
    private static final String DETAIL_STR = "detail";
    private static final String URL_STR = "url";
    private static final String ADDRESS_STR = "address";

    private static final String NUO_MI = "nuomi";
    private static final String PRICE = "price";
    private static final String SHOP_NAME = "shopName";

    public HtmlExtractor() {
    }

    public HtmlExtractor(Site site, AbstractOutput outputer) {
        super(site);
        this.outputers.add(outputer);
        this.keys = site.getName2selector().keySet();
    }

    @Override
    public void init(Site site) {
        this.site = site;
        this.keys = site.getName2selector().keySet();
    }

    @Override
    public void extract() throws Exception {
        site.getResult().put("website",site.getWebsite());
        HttpClientUtil httpClientUtil = HttpClientUtil.getInctence();
        String string = httpClientUtil.doGet(site.getUrl());
        Document document = Jsoup.parse(string);
        Elements liElements = document.select(site.getName2selector().get(Constants.LI_NODE));
        Map map = site.getName2selector();
        map.remove(Constants.LI_NODE);
        dealElements(liElements, map);
    }

    private void dealElements(Elements elements, Map map){
        List result = Lists.newArrayList();
        for(int i=0; i< elements.size(); i++){
            dealLiNode(result, elements.get(i), map);
        }
        site.getResult().put("details", result);
    }

    private void dealLiNode(List all, Element element, Map map){
        Map liResult = Maps.newHashMap();
        for(Object key: map.keySet()){
           if(!DETAIL_STR.equals(key.toString())){
               String selector = site.getName2selector().get(key.toString());
               if(selector.indexOf("@") != -1){
                   String[] urlSelectorAndAttr = selector.split("@");
                   String str = element.select(urlSelectorAndAttr[0]).attr(urlSelectorAndAttr[1]);
                   liResult.put(key,str);
               }else {
                   Element ele = element.select(selector).first();
                   if(ele != null)  liResult.put(key, ele.text());
               }
           }
        }
        if(map.keySet().contains(DETAIL_STR)){
            Map<String, String> detailMap = (Map<String, String>) map.get(DETAIL_STR);
            String urlSelector = detailMap.get(URL_STR);
            String[] urlSelectorAndAttr = urlSelector.split("@");
            String dealId = element.select(urlSelectorAndAttr[0]).attr(urlSelectorAndAttr[1]);
//            if(NUO_MI.equals(site.getWebsite().getName())){
//                nuomiDealDetail(all, dealId, liResult);
//            }
        }

        all.add(liResult);
    }

    private void nuomiDealDetail(List all, String dealIdStr, Map<String, String> result) {
        if(StringUtils.isEmpty(dealIdStr)) return;
        System.out.println(new Date());
        System.out.println(dealIdStr);
        Integer dealId = Integer.valueOf(dealIdStr);
        String detailJson = BaiduApiUtils.getBaiduNuomiDealDetail(dealId);
        String dealShopsJson = BaiduApiUtils.getBaiduNuomiDealShops(dealId, 200010000);
        Gson gson = new Gson();
        DealDetail detail = gson.fromJson(detailJson, DealDetail.class);
        DealShops dealShops = gson.fromJson(dealShopsJson, DealShops.class);
        List<Shop> shops = dealShops.getShops();
        StringBuilder shopNames = new StringBuilder();
        StringBuilder address = new StringBuilder();
        for(Shop shop : shops){
            Map map = new HashMap<String, String>();
            map.put(PRICE,detail.getDeal().getLong_title());
            map.put(SHOP_NAME,shop.getName());
            map.put(ADDRESS_STR, shop.getAddress());
            all.add(map);
        }
    }

    private Object extractContentByMethod(Element e, String key){
        Object result = "";
        if(site.getName2method()!=null){
            String method = site.getName2method().get(key);
            if("text".equals(method)){
                result = e.text();
            }else if("attr".equals(method)){

            }else {
                result = e.text();
            }
        }else {
            result = e.text();
        }
        return result;
    }

    private boolean isOnlyOne(){
        return site.getName2selector().size() == 1;
    }

}
