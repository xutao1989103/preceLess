package com.tianyu.jty.collector.entity.douban;

import java.util.List;

/**
 * Created by xtao on 2015/12/7.
 */
public class MovieDetail {
    private Integer id;
    private String title;
    private String original_title;
    private List<String> aka;
    private String alt;
    private String mobile_url;
    private Integer ratings_count;
    private Image images;
    private List<Person> casts;
    private List<Person> directors;
    private List<String> genres;
    private String summary;

    public MovieDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public Integer getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(Integer ratings_count) {
        this.ratings_count = ratings_count;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public List<Person> getCasts() {
        return casts;
    }

    public void setCasts(List<Person> casts) {
        this.casts = casts;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
