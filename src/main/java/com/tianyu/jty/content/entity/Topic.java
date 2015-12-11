package com.tianyu.jty.content.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by xtao on 2015/11/2.
 */

@Entity
@Table(name = "topic")
@DynamicUpdate
@DynamicInsert
public class Topic implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pid;
    private String name;
    private String img;
    private String description;
    private String delFlag;

    public Topic() {
    }

    public Topic(int pid, String name, String img, String description, String delFlag) {
        this.pid = pid;
        this.name = name;
        this.img = img;
        this.description = description;
        this.delFlag = delFlag;
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

    @Column(name = "PID")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IMG")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DEL_FLAG")
    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
