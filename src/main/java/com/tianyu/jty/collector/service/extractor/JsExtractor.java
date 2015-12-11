package com.tianyu.jty.collector.service.extractor;

import com.tianyu.jty.collector.entity.Site;

/**
 * Created by xtao on 2015/11/8.
 */
public class JsExtractor extends Extractor {
    @Override
    public void init(Site site) {
        this.site = site;
    }

    @Override
    public void extract() throws Exception {

    }
}
