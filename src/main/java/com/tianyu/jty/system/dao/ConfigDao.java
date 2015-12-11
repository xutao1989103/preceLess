package com.tianyu.jty.system.dao;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.system.entity.Config;
import org.springframework.stereotype.Repository;

/**
 * Created by xtao on 2015/12/4.
 */

@Repository
public class ConfigDao extends HibernateDao<Config, Integer> {
}
