package com.tianyu.jty.collector.service.extractor;

import com.tianyu.jty.collector.entity.SiteType;

/**
 * Created by xtao on 2015/11/25.
 */
public class ExtractorFactory {
    public static Extractor getExtractor(SiteType type){
        if(type == SiteType.HTML){
            return new HtmlExtractor();
        }else if(type ==SiteType.JSON){
            return new JsonExtractor();
        }else if(type == SiteType.JS){
            return new JsExtractor();
        }else {
            return new HtmlExtractor();
        }
    }
}
