package com.tianyu.jty.collector.entity;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.SiteType;
import com.tianyu.jty.collector.entity.Website;
import com.tianyu.jty.content.entity.Topic;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/8.
 */
public class Site {
    private Website website;
    private SiteType type;
    private Map<String ,Object> result = Maps.newHashMap();
    //html
    private String url;
    private Map<String, String> name2selector;
    private Map<String, String> name2method;
    private boolean onlyOneExtract;
    //json
    private String keyword;
    private LocationForConvert location;
    private List<String> jsons;

    public Site(Website website, Map map) {
        this.website = website;
        this.url = website.getApiUrl() + map.get("name");
        this.keyword = map.get("name").toString();
        this.type = SiteType.codeOf(website.getSiteType());
        if(map.get("lng") != null && map.get("lat") != null){
            this.location = new LocationForConvert(map.get("lng").toString(), map.get("lat").toString());
        }

        if(type == SiteType.HTML) {
            setName2selector(website);
        }else if(type == SiteType.JSON){
            setJsons(website);
        }
    }

    private void setJsons(Website website) {
        List<String> jsons = Lists.newArrayList();
        if(!StringUtils.isEmpty(website.getRulestr())){
            Iterable<String> strs = Splitter.on(',').split(website.getRulestr());
            for(String str: strs){
                jsons.add(str);
            }
        }
        this.jsons = jsons;
    }

    private void setName2selector(Website website){
        Map rules = Maps.newHashMap();
        if(!StringUtils.isEmpty(website.getRulestr())){
            Iterable<String> strs = Splitter.on(';').split(website.getRulestr());
            for(String str: strs){
                String[] s = str.split(":");
                rules.put(s[0],s[1]);
            }
        }

        String detail = "detail";
        if(rules.keySet().contains(detail)){
            Map details = Maps.newHashMap();
            if( rules.get(detail) != null){
                String detailString = rules.get(detail).toString();
                Iterable<String> detailStr = Splitter.on('&').split(detailString);
                for(String str: detailStr){
                    String[] s = str.split("%");
                    details.put(s[0],s[1]);
                }
            }
            rules.put(detail,details);
        }
        this.name2selector = rules;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getName2selector() {
        return name2selector;
    }

    public void setName2selector(Map<String, String> name2selector) {
        this.name2selector = name2selector;
    }

    public Map<String, String> getName2method() {
        return name2method;
    }

    public void setName2method(Map<String, String> name2method) {
        this.name2method = name2method;
    }

    public boolean isOnlyOneExtract() {
        return onlyOneExtract;
    }

    public void setOnlyOneExtract(boolean onlyOneExtract) {
        this.onlyOneExtract = onlyOneExtract;
    }

    public SiteType getType() {
        return type;
    }

    public void setType(SiteType type) {
        this.type = type;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public LocationForConvert getLocation() {
        return location;
    }

    public void setLocation(LocationForConvert location) {
        this.location = location;
    }

    public List<String> getJsons() {
        return jsons;
    }

    public void setJsons(List<String> jsons) {
        this.jsons = jsons;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
