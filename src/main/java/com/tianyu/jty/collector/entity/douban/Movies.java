package com.tianyu.jty.collector.entity.douban;

import java.util.List;

/**
 * Created by xtao on 2015/12/7.
 */
public class Movies {
    private Integer count;
    private Integer start;
    private Integer total;
    private List<Subject> subjects;
    private String title;

    public Movies() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
