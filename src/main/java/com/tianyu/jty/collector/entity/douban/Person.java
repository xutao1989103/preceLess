package com.tianyu.jty.collector.entity.douban;

import java.util.List;

/**
 * Created by xtao on 2015/12/7.
 */
public class Person {
    private Integer id;
    private String name;
    private Image avatars;
    private String alt;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getAvatars() {
        return avatars;
    }

    public void setAvatars(Image avatars) {
        this.avatars = avatars;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
