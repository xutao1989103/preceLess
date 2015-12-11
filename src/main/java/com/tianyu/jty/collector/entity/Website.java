package com.tianyu.jty.collector.entity;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by xtao on 2015/11/25.
 */
@Entity
@Table(name = "website")
@DynamicUpdate
@DynamicInsert
public class Website  implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String site;
    private Integer type;
    private Integer siteType;
    private String apiUrl;
    private String rulestr;

    public Website() {

    }

    public Website(String name, String site, Integer type, String apiUrl, String rulestr) {
        this.name = name;
        this.site = site;
        this.type = type;
        this.apiUrl = apiUrl;
        this.rulestr = rulestr;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SITE")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "SITE_TYPE")
    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    @Column(name = "API_URL")
    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Column(name = "RULESTR")
    public String getRulestr() {
        return rulestr;
    }

    public void setRulestr(String rulestr) {
        this.rulestr = rulestr;
    }
}
