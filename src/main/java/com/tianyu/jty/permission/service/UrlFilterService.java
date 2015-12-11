package com.tianyu.jty.permission.service;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.permission.dao.UrlFilterDao;
import com.tianyu.jty.permission.entity.UrlFilter;
import com.tianyu.jty.permission.utils.ShiroFilterChainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by xtao on 2015/11/16.
 */
@Service
public class UrlFilterService extends BaseService<UrlFilter, Integer> {

    @Autowired
    private UrlFilterDao urlFilterDao;

    @Autowired
    private ShiroFilterChainManager shiroFilterChainManager;

    @Override
    public HibernateDao<UrlFilter, Integer> getEntityDao() {
        return urlFilterDao;
    }

    public void saveAndRefresh(final UrlFilter urlFilter){
        super.save(urlFilter);
        initFilterChain();
    }

    public void updateAndRefresh(final UrlFilter urlFilter) {
        UrlFilter origin = this.get(urlFilter.getId());
        origin.setName(urlFilter.getName());
        origin.setType(urlFilter.getType());
        origin.setUrl(urlFilter.getUrl());
        origin.setMethod(urlFilter.getMethod());
        origin.setRoles(urlFilter.getRoles());
        origin.setPermissions(urlFilter.getPermissions());
        origin.setLbstype(urlFilter.getLbstype());
        origin.setLocation(urlFilter.getLocation());
        origin.setDistance(urlFilter.getDistance());
        origin.setArea(urlFilter.getArea());
        super.update(origin);
        initFilterChain();
    }

    public void deleteAndRefresh(final UrlFilter urlFilter) {
        super.delete(urlFilter);
        initFilterChain();
    }

    public void deleteAndRefresh(final Integer id) {
        super.delete(id);
        initFilterChain();
    }

    public UrlFilter findOneByUrl(String url) {
        return urlFilterDao.findOneByUrl(url);
    }

    public List<UrlFilter> findAllByUrlAndType(String url, String method, Integer type) {
        return urlFilterDao.findAllByUrlAndType(url, method, type);
    }

    public List<UrlFilter> findAllBymethodAndType(String method, Integer type){
        return urlFilterDao.findAllByMethodAndType(method, type);
    }

    @Transactional(readOnly = false)
    public void initFilterChain() {
        shiroFilterChainManager.initFilterChains(super.getAll());
    }
}
