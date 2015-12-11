package com.tianyu.jty.system.service;

import com.google.common.collect.Maps;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.system.dao.ConfigDao;
import com.tianyu.jty.system.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/12/4.
 */
@Service
@Transactional(readOnly=true)
public class ConfigService extends BaseService<Config,Integer> {

    private Map<String, String> configs;

    @Autowired
    private ConfigDao configDao;

    @Override
    public HibernateDao<Config, Integer> getEntityDao() {
        return configDao;
    }

    public Map<String, String> getConfigs(){
        if(configs == null || configs.size() == 0)  {
            configs = Maps.newHashMap();
            List<Config> configList  = this.getAll();
            if(!CollectionUtils.isEmpty(configList)){
                Iterator<Config> it = configList.iterator();
                while (it.hasNext()){
                    Config config = it.next();
                    if(config.getValidate() != 0) {
                        configs.put(config.getKey(), config.getValue());
                    }
                }
            }
        }
        return this.configs;
    }
}
