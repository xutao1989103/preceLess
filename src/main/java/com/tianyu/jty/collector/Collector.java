package com.tianyu.jty.collector;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.SiteType;
import com.tianyu.jty.content.entity.Topic;
import com.tianyu.jty.wechat.util.ChangeCharset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/8.
 */
public class Collector {
    public static void main(String[] args) throws IOException {
        DianpingHtmlExtractor();
        NuomiHtmlExtractor();
//        String url = "http://www.dianping.com/search/keyword/1/0_%E5%B0%8F%E8%BE%89%E5%93%A5%E7%81%AB%E9%94%85(%E6%97%A5%E6%9C%88%E5%85%89%E5%B9%BF%E5%9C%BA%E5%BA%97)";
//        //Document document = Jsoup.connect("http://www.dianping.com/search/keyword/1/0_%E5%B0%8F%E8%BE%89%E5%93%A5%E7%81%AB%E9%94%85(%E6%97%A5%E6%9C%88%E5%85%89%E5%B9%BF%E5%9C%BA%E5%BA%97)").get();
//        HttpClientUtil httpClientUtil = HttpClientUtil.getInctence();
//        String string = httpClientUtil.doGet(url);
//        Document document = Jsoup.parse(string);
//        System.out.println(string);
    }

    private static void jsonExtractor(){
        String url = "http://apistore.baidu.com/apiworks/servicecommentlist?serviceId=508&pageNum=1&_=1447040270856";
        List<String> keys = Lists.newArrayList("totalRecord");
        SiteType type = SiteType.HTML;
        Topic topic = new Topic();
//        Site site = new Site(url, type, topic);
//        site.withJsonKeys(keys);
//        Extractor extractor = new JsonExtractor(site,new ConsoleOutput());
//        extractor.addOutputer(new FileOutput());
//        extractor.execute();
    }

    private static void NuomiHtmlExtractor(){

    }

    private static void DianpingHtmlExtractor() {
        String shopName = "小肥羊（民主路店）";
        String url = null;
        try {
            ChangeCharset changeCharset = new ChangeCharset();
            System.out.println(changeCharset.toUTF_8(shopName));
            System.out.println(URLEncoder.encode(shopName,"UTF-8"));
            url = "http://www.dianping.com/search/keyword/1/0_" + changeCharset.toUTF_8(shopName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, String> name2regex = Maps.newHashMap();
        name2regex.put("shopName","div.tit > a > h4");
        name2regex.put("price","div.svr-info");

    }
}
