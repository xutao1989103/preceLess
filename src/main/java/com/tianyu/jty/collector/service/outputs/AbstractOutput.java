package com.tianyu.jty.collector.service.outputs;

import com.tianyu.jty.collector.entity.Site;

/**
 * Created by xtao on 2015/11/9.
 */
public abstract class AbstractOutput implements Output,Runnable {

    private Site site;

    @Override
    public void run() {
        try {
            output(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
