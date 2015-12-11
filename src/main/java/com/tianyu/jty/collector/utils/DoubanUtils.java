package com.tianyu.jty.collector.utils;

import com.google.gson.Gson;
import com.tianyu.jty.collector.entity.douban.MovieDetail;
import com.tianyu.jty.collector.entity.douban.Movies;
import com.tianyu.jty.collector.entity.douban.Subject;

import java.util.concurrent.Callable;

/**
 * Created by xtao on 2015/12/7.
 */
public class DoubanUtils implements Callable<MovieDetail>{

    private static final String DOUBAN_API_BASE = "https://api.douban.com";
    private static final String IN_THEATERS = "/v2/movie/in_theaters";
    private static final String MOVIE_DETAIL = "/v2/movie/subject/:ID";
    private static Gson gson = new Gson();
    private Subject subject;

    public DoubanUtils(Subject subject) {
        this.subject = subject;
    }

    public static Movies getInTheatersMovies(){
        String url = DOUBAN_API_BASE + IN_THEATERS;
        String moviesStr = HttpClientUtil.getInctence().doGet(url);
        Movies movies = gson.fromJson(moviesStr, Movies.class);
        return movies;
    }

    public static MovieDetail getMovieDetailById(Integer id) {
        String url = DOUBAN_API_BASE + MOVIE_DETAIL;
        url = url.replace(":ID", id.toString());
        String movieDetailStr = HttpClientUtil.getInctence().doGet(url);
        MovieDetail detail = gson.fromJson(movieDetailStr, MovieDetail.class);
        return detail;
    }

    public MovieDetail enrichMovie(Subject subject){
        String url = DOUBAN_API_BASE + MOVIE_DETAIL;
        url = url.replace(":ID",subject.getId().toString());
        String movieDetailStr = HttpClientUtil.getInctence().doGet(url);
        MovieDetail detail = gson.fromJson(movieDetailStr, MovieDetail.class);
        return detail;
    }


    @Override
    public MovieDetail call() throws Exception {
        return enrichMovie(subject);
    }
}
