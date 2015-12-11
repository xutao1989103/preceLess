package com.tianyu.jty.collector.jobs;

import com.tianyu.jty.system.entity.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xtao on 2015/11/15.
 */
@DisallowConcurrentExecution
public class DianpingCollectorTask implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println("task name = [" + scheduleJob.getName() + "]"+ " 在 " + dateFormat.format(new Date())+" 时开始运行");
        dianpingTask();
    }

    private void dianpingTask(){
//        String shopName = "小肥羊（民主路店）";
//        String url = "http://www.dianping.com/search/keyword/1/0_" + shopName;
//        Map<String, String> name2regex = Maps.newHashMap();
//        name2regex.put("shopName","a > h4");
//        name2regex.put("price","ul.itemlist > li");
//        SiteType type = SiteType.HTML;
//        Topic topic = new Topic();
//        Site site = new Site(url, type, topic);
//        site.withName2Selector(name2regex);
//        Extractor extractor = new HtmlExtractor(site,new ConsoleOutput());
//        extractor.addOutputer(new FileOutput());
//        extractor.execute();
    }
}
