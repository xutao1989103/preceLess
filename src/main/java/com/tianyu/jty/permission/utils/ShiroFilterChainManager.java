package com.tianyu.jty.permission.utils;

import com.tianyu.jty.permission.entity.LbsType;
import com.tianyu.jty.permission.entity.UrlFilter;
import com.tianyu.jty.permission.entity.UrlFilterType;
import com.tianyu.jty.permission.service.UrlFilterService;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/17.
 */
@Service
public class ShiroFilterChainManager {

    @Autowired
    private DefaultFilterChainManager filterChainManager;

    @Autowired
    private UrlFilterService urlFilterService;

    private Map<String, NamedFilterList> defaultFilterChains;
    private final static String REST_FILTER_NAME = "restful";
    private final static String LBS_FILTER_NAME ="lbs";
    private final static String ROLES_FILTER_NAME = "roles";
    private final static String PERMS_FILTER_NAME = "perms";
    private static boolean isInit = false;

    public void initDefaultFilterChains(List<UrlFilter> urlFilters) {
        if(!isInit){
            defaultFilterChains = new HashMap<>(filterChainManager.getFilterChains());
            isInit = true;
        }
    }

    public void initFilterChains(List<UrlFilter> urlFilters) {
        initDefaultFilterChains(urlFilters);
        filterChainManager.getFilterChains().clear();
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        for(UrlFilter urlFilter: urlFilters) {
            buildUrlFilter(urlFilter);
            buildLbsFilter(urlFilter);
        }
    }

    private void buildUrlFilter(UrlFilter urlFilter) {
        String url = urlFilter.getUrl();
        if(urlFilter.getType() == UrlFilterType.URL_METHOD.getCode()) {
            filterChainManager.addToChain(url, REST_FILTER_NAME);
        }else if(urlFilter.getType() == UrlFilterType.URL.getCode()){
            if(!StringUtils.isEmpty(urlFilter.getRoles())){
                filterChainManager.addToChain(url, ROLES_FILTER_NAME, urlFilter.getRoles());
            }
            if(!StringUtils.isEmpty(urlFilter.getPermissions())){
                filterChainManager.addToChain(url, PERMS_FILTER_NAME, urlFilter.getPermissions());
            }
        }
    }

    private void buildLbsFilter(UrlFilter urlFilter) {
        String url = urlFilter.getUrl();
        if(urlFilter.getLbstype() != LbsType.NONE.getCode()){
            filterChainManager.addToChain(url, LBS_FILTER_NAME);
        }
    }
}
