package com.tianyu.jty.collector.service;

import com.google.common.collect.Lists;
import com.tianyu.jty.collector.dao.WebsiteDao;
import com.tianyu.jty.collector.entity.TopicType;
import com.tianyu.jty.collector.entity.Website;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xtao on 2015/11/25.
 */
@Service
@Transactional(readOnly=true)
public class WebsiteService extends BaseService<Website,Integer> {

    @Autowired
    private WebsiteDao websiteDao;

    @Override
    public HibernateDao<Website, Integer> getEntityDao() {
        return websiteDao;
    }

    public List<Website> getAllWebsitesByType(TopicType topicType) {
        List<Website> result = Lists.newArrayList();
        for(Website w: this.getAll()){
            if(w.getType() == topicType.getCode()) result.add(w);
        }
        return result;
    }
}
