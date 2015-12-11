package com.tianyu.jty.wechat.controller;

import com.google.common.collect.Lists;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.douban.*;
import com.tianyu.jty.collector.entity.nuomi.NuomiMovie;
import com.tianyu.jty.collector.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xtao on 2015/12/7.
 */
@Controller
@RequestMapping("priceless")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String getMoviePage(HttpServletRequest request, Model model){
        String lng = request.getParameter("lng");
        String lat = request.getParameter("lat");
        LocationForConvert location = new LocationForConvert(lng,lat);

        Movies movies = movieService.getMovies();
        List<MovieModel> models = movieService.enrichSubjectList(movies.getSubjects());
        model.addAttribute("movies",models);
        model.addAttribute("lng", location.getLng());
        model.addAttribute("lat", location.getLat());
        return "wechat/priceless-movies-list";
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String getMovieDetailPage(HttpServletRequest request, Model model,@PathVariable Integer id){
        String lng = request.getParameter("lng");
        String lat = request.getParameter("lat");
        LocationForConvert location = new LocationForConvert(lng, lat);

        MovieDetail movie = movieService.getMovieDeatilById(id);
        MovieModel movieModel = movieService.convertDetailToModel(movie);
        NuomiMovie cinemaModel = movieService.getNearestCinema(location);
        List<NuomiMovie> cinemas = movieService.getTopKcinemas(location, 3);
        List<MoviePriceModel> prices = movieService.getAllPrices(movieModel, cinemaModel);

        model.addAttribute("movie", movieModel);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("prices", prices);
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        return "wechat/priceless-movies-detail";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String doMovieSearch(HttpServletRequest request, Model model){
        return "wechat/doing";
    }
}
