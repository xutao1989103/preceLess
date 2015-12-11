package com.tianyu.jty.collector.service.outputs;

import com.tianyu.jty.collector.entity.Site;
import org.springframework.stereotype.Service;

/**
 * Created by xtao on 2015/11/8.
 */
@Service("consoleOutput")
public class ConsoleOutput extends AbstractOutput {
    @Override
    public void output(Site site) {
        System.out.println(site.getResult());
    }
}
