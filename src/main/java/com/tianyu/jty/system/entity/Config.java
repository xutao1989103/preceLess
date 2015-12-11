package com.tianyu.jty.system.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by xtao on 2015/12/4.
 */

@Entity
@Table(name = "configuration")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@DynamicInsert
public class Config {
    private Integer id;
    private String key;
    private String value;
    private Integer type;
    private Integer validate;

    public Config() {
    }

    public Config(String key, String value, Integer type, Integer validate) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.validate = validate;
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
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "VALIDATE")
    public Integer getValidate() {
        return validate;
    }

    public void setValidate(Integer validate) {
        this.validate = validate;
    }
}
