package com.tianyu.jty.content.service;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.content.dao.TopicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by xtao on 2015/11/2.
 */

@Service
@Transactional(readOnly = true)
public class ContentService extends BaseService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public HibernateDao getEntityDao() {
        return topicDao;
    }
}
