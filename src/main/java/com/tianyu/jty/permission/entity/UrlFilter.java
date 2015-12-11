package com.tianyu.jty.permission.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by xtao on 2015/11/16.
 */

@Entity
@Table(name = "url_filter")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@DynamicInsert
public class UrlFilter implements Serializable {

    // Fields
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer type; //0 url 1 url+method
    private Integer lbstype; //0 none,1 location, 2 area,3 location and area
    private String name;
    private String url;
    private String method;
    private String roles;
    private String permissions;
    private String location;
    private Long distance;
    private String area;

    public UrlFilter() {

    }

    public UrlFilter(Integer type, String name, String url, String method, String roles, String permissions) {
        this.type = type;
        this.name = name;
        this.url = url;
        this.method = method;
        this.roles = roles;
        this.permissions = permissions;
    }

    public UrlFilter(Integer type, Integer lbstype, String name, String url, String method, String roles, String permissions, String location, String area) {
        this.type = type;
        this.lbstype = lbstype;
        this.name = name;
        this.url = url;
        this.method = method;
        this.roles = roles;
        this.permissions = permissions;
        this.location = location;
        this.area = area;
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

    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "LBSTYPE")
    public Integer getLbstype() {
        return lbstype;
    }

    public void setLbstype(Integer lbstype) {
        this.lbstype = lbstype;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "METHOD")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column(name = "ROLES")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Column(name = "PERMISSIONS")
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Column(name = "LOCATION")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "DISTANCE")
    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Column(name = "AREA")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
