package com.tianyu.jty.permission.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.entity.RestRequest;
import com.tianyu.jty.permission.entity.UrlFilter;
import com.tianyu.jty.permission.entity.UrlFilterType;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by xtao on 2015/11/23.
 */
public abstract class AbstractFilterService implements FilterService {

    @Autowired
    private UrlFilterService urlFilterService;
    protected Subject subject;
    protected RestRequest restRequest;
    protected Location location;
    protected List<UrlFilter> urlFilters;

    @Override
    public void initFilter(ServletRequest servletRequest, Subject subject){
        this.subject = subject;
        this.restRequest = RestRequest.newRestRequest(servletRequest);
        this.location = Location.newLocation(servletRequest);
        this.urlFilters = this.getMatchedUrlFilter();
    }

    @Override
    public List<UrlFilter> getMatchedUrlFilter() {
        List<UrlFilter> allFilters = urlFilterService.findAllBymethodAndType(restRequest.getMethod(), UrlFilterType.URL_METHOD.getCode());;
        List<UrlFilter> result = Lists.newArrayList();
        AntPathMatcher matcher = new AntPathMatcher();
        for(UrlFilter filter: allFilters){
            if(matcher.match(filter.getUrl(), restRequest.getPath())){
                result.add(filter);
            }
        }
        return result;
    }

    protected List<String> getAllRoles(List<UrlFilter> urlFilters) {
        Set<String> set = Sets.newHashSet();
        for(UrlFilter urlFilter: urlFilters){
            if(!StringUtils.isEmpty(urlFilter.getRoles())) set.addAll(Arrays.asList(urlFilter.getRoles().split(",")));
        }
        return Lists.newArrayList(set);
    }

    protected String getAllPermissions(List<UrlFilter> urlFilters) {
        Set<String> set = Sets.newHashSet();
        for(UrlFilter urlFilter: urlFilters){
            if(!StringUtils.isEmpty(urlFilter.getPermissions())) set.addAll(Arrays.asList(urlFilter.getPermissions().split(",")));
        }
        Joiner joiner = Joiner.on(",").skipNulls();
        return joiner.join(set);
    }

    protected boolean hasAnyRole(Subject subject, List<String> roles){
        boolean hasAnyRole = false;
        if(CollectionUtils.isEmpty(roles)) return hasAnyRole;
        boolean[] bs =  subject.hasRoles(roles);
        for(boolean b: bs){
            if(b) {
                hasAnyRole = true;
                break;
            }
        }
        return hasAnyRole;
    }
}
