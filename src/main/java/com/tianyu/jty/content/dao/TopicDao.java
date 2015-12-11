package com.tianyu.jty.content.dao;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.content.entity.Topic;
import org.springframework.stereotype.Repository;

/**
 * Created by xtao on 2015/11/2.
 */

@Repository
public class TopicDao extends HibernateDao<Topic, Integer> {
}
