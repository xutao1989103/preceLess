package com.tianyu.jty.collector.service.outputs;

import com.tianyu.jty.collector.entity.Site;

/**
 * Created by xtao on 2015/11/8.
 */
public interface Output {
    void output(Site site) throws Exception;
}
