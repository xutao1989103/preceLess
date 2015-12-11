package com.tianyu.jty.permission.dao;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.permission.entity.UrlFilter;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xtao on 2015/11/16.
 */
@Repository
public class UrlFilterDao extends HibernateDao<UrlFilter, Integer> {

    /**
     * 获取所有过滤器
     * @param
     * @return 所有filter列表
     */
    @SuppressWarnings("unchecked")
    public List<UrlFilter> findAll() {
        StringBuffer sb = new StringBuffer();
        sb.append("select u.* from url_filter u ");
        SQLQuery sqlQuery = createSQLQuery(sb.toString());
        sqlQuery.addEntity(UrlFilter.class);
        return sqlQuery.list();
    }

    /**
     * 获取过滤器
     * @param url
     * @return urlFilter
     */
    @SuppressWarnings("unchecked")
    public UrlFilter findOneByUrl(String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from url_filter where url=?0 LIMIT 1");
        SQLQuery sqlQuery = createSQLQuery(sb.toString(),url);
        sqlQuery.addEntity(UrlFilter.class);
        return (UrlFilter)sqlQuery.uniqueResult();
    }

    /**
     * 获取过滤器
     * @param url
     * @return urlFilterList
     */
    @SuppressWarnings("unchecked")
    public List<UrlFilter> findAllByUrlAndType(String url, String method, Integer type) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from url_filter where url like '%" + url + "%' ");
        sb.append("and method like '%" + method +"%'");
        if(type != null){
            sb.append("and type = ?0");
        }
        SQLQuery sqlQuery = createSQLQuery(sb.toString(), type);
        sqlQuery.addEntity(UrlFilter.class);
        return sqlQuery.list();
    }

    /**
     * 获取过滤器
     * @param method,type
     * @return urlFilterList
     */
    @SuppressWarnings("unchecked")
    public List<UrlFilter> findAllByMethodAndType(String method, Integer type) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from url_filter where method like '%" + method +"%'");
        if(type != null){
            sb.append("and type = ?0");
        }
        SQLQuery sqlQuery = createSQLQuery(sb.toString(), type);
        sqlQuery.addEntity(UrlFilter.class);
        return sqlQuery.list();
    }
}
