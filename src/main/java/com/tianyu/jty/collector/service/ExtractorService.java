package com.tianyu.jty.collector.service;

import com.google.common.collect.Lists;
import com.tianyu.jty.collector.entity.Site;
import com.tianyu.jty.collector.entity.TopicType;
import com.tianyu.jty.collector.entity.Website;
import com.tianyu.jty.collector.service.extractor.Extractor;
import com.tianyu.jty.collector.service.extractor.ExtractorFactory;
import com.tianyu.jty.collector.service.outputs.ConsoleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xtao on 2015/11/25.
 */
@Service
public class ExtractorService {

    @Autowired
    private WebsiteService websiteService;

    public List<Map<String, Object>> doExtract(TopicType type, Map params){
        List result = Lists.newArrayList();
        List<Website> websites = websiteService.getAllWebsitesByType(type);
        List<Site> sites = initSites(websites, params);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Map<String,Object>>> futures = Lists.newArrayList();
        for(Site site: sites){
            Extractor extractor = ExtractorFactory.getExtractor(site.getType());
            extractor.init(site);
            extractor.addOutputer(new ConsoleOutput());
            Future future = executor.submit(extractor);
            futures.add(future);
        }
        executor.shutdown();
        getFutures(result, futures);
        return result;
    }

    private void getFutures(List result, List<Future<Map<String, Object>>> futures) {
        for (Future future: futures){
            try {
                result.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Site> initSites(List<Website> websites,Map<String, Object> map) {
        List<Site> sites = Lists.newArrayList();
        for(Website website: websites) {
            Site site = new Site(website, map);
            sites.add(site);
        }
        return sites;
    }



}
