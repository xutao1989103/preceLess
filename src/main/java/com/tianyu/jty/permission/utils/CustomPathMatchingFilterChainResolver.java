package com.tianyu.jty.permission.utils;

import com.google.common.collect.Lists;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by xtao on 2015/11/17.
 */
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    private CustomDefaultFilterChainManager customDefaultFilterChainManager;

    public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        this.customDefaultFilterChainManager = customDefaultFilterChainManager;
        setFilterChainManager(customDefaultFilterChainManager);
    }

    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if(!filterChainManager.hasChains()){
            return null;
        }
        String requestURI = getPathWithinApplication(request);
        List<String> chainNames = Lists.newArrayList();
        for(String pathPattern: filterChainManager.getChainNames()){
            if(pathMatches(pathPattern, requestURI)){
                chainNames.add(pathPattern);
            }
        }
        if(chainNames.size() == 0){
            return null;
        }
        return customDefaultFilterChainManager.proxy(originalChain, chainNames);
    }
}
