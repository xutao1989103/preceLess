package com.tianyu.jty.collector.service.extractor;



import com.google.common.collect.Lists;
import com.tianyu.jty.collector.entity.Site;
import com.tianyu.jty.collector.service.outputs.AbstractOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Created by xtao on 2015/11/8.
 */
public abstract class Extractor implements Callable<Map<String,Object>>{

    public static final Logger LOGGER = LoggerFactory.getLogger(Extractor.class);

    protected Site site = null;

    protected List<AbstractOutput> outputers = Lists.newArrayList();

    public Extractor(){

    }

    public Extractor(Site site) {
        this.site = site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public abstract void init(Site site);

    public void addOutputer(AbstractOutput output) {
        this.outputers.add(output);
    }

    public abstract void extract() throws Exception;

    protected void output() {
        for(AbstractOutput out: outputers){
            out.setSite(site);
            new Thread(out).start();
        }
    }

    public void execute(){
        try {
            extract();
            output();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("Exception e" + site.getUrl(), e);
        }
    }

    @Override
    public Map<String, Object> call() throws Exception {
        this.execute();
        return site.getResult();
    }

}
