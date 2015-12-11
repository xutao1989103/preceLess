package com.tianyu.jty.collector.entity.douban;

import java.util.List;

/**
 * Created by xtao on 2015/12/7.
 */
public class MovieModel {
    private Rating rating;
    private List<String> genres;
    private String genresStr;
    private Integer collect_count;
    private List<Person> casts;
    private String castsStr;
    private String title;
    private String original_title;
    private String subtype;
    private List<Person> directors;
    private String directorsStr;
    private String year;
    private Image images;
    private String alt;
    private Integer id;
    private String summary;
    private String mobile_url;

    public MovieModel() {
    }

    public MovieModel(Subject subject) {
        this.rating = subject.getRating();
        this.genres = subject.getGenres();
        this.collect_count = subject.getCollect_count();
        this.casts = subject.getCasts();
        this.title = subject.getTitle();
        this.original_title = subject.getOriginal_title();
        this.subtype = subject.getSubtype();
        this.directors = subject.getDirectors();
        this.year = subject.getYear();
        this.images = subject.getImages();
        this.alt = subject.getAlt();
        this.id = subject.getId();
    }

    public MovieModel(MovieDetail detail) {
        this.genres = detail.getGenres();
        this.casts = detail.getCasts();
        this.title = detail.getTitle();
        this.original_title = detail.getOriginal_title();
        this.directors = detail.getDirectors();
        this.images = detail.getImages();
        this.alt = detail.getAlt();
        this.id = detail.getId();
        this.summary = detail.getSummary();
        this.mobile_url = detail.getMobile_url();

    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getGenresStr() {
        return genresStr;
    }

    public void setGenresStr(String genresStr) {
        this.genresStr = genresStr;
    }

    public Integer getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(Integer collect_count) {
        this.collect_count = collect_count;
    }

    public List<Person> getCasts() {
        return casts;
    }

    public void setCasts(List<Person> casts) {
        this.casts = casts;
    }

    public String getCastsStr() {
        return castsStr;
    }

    public void setCastsStr(String castsStr) {
        this.castsStr = castsStr;
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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public String getDirectorsStr() {
        return directorsStr;
    }

    public void setDirectorsStr(String directorsStr) {
        this.directorsStr = directorsStr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }
}
