package com.tianyu.jty.collector.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.douban.*;
import com.tianyu.jty.collector.entity.nuomi.NuomiMovie;
import com.tianyu.jty.collector.utils.DoubanUtils;
import com.tianyu.jty.collector.utils.HttpClientUtil;
import com.tianyu.jty.permission.entity.AddressResult;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import com.tianyu.jty.system.service.ConfigService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xtao on 2015/12/7.
 */
@Service
public class MovieService {

    private static final String DEFAULT_AREA_ID = "0-0";

    @Autowired
    private ConfigService configService;

    public List<NuomiMovie> getNearByCinemas(LocationForConvert location) {
        String areaId = getAreaId(location);
        List<NuomiMovie> nuomiMovies = getCinemasInfoFromWeb(buildUrl(areaId));
        enrich(nuomiMovies, location);
        Collections.sort(nuomiMovies, new Comparator<NuomiMovie>() {
            @Override
            public int compare(NuomiMovie o1, NuomiMovie o2) {
                if (o1.getDistance() - o2.getDistance() == 0.0) return 0;
                if (o1.getDistance() - o2.getDistance() > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return nuomiMovies;
    }

    public List<MoviePriceModel> getAllPrices(MovieModel movie, NuomiMovie cinema) {
        List<MoviePriceModel> priceModels = Lists.newArrayList();
        return priceModels;
    }

    private String getAreaId(LocationForConvert location) {
        AddressResult addressResult = BaiduMapUtils.locationToAddress(new Location(location.getLng(), location.getLat()));
        String area =  addressResult.getAddressComponent().getDistirct();
        if(StringUtils.isEmpty(area)){
            try {
                area = addressResult.getFormatted_address().substring(3,6);
            }catch (Exception e){
                e.printStackTrace();
                area = "";
            }
        }
        return configService.getConfigs().get(area);
    }

    private void enrich(List<NuomiMovie> nuomiMovies, LocationForConvert location){
        Location myPosition = new Location(location.getLng(), location.getLat());
        for(NuomiMovie nuomiMovie: nuomiMovies){
            Double distance = BaiduMapUtils.getDistance(myPosition, new Location(nuomiMovie.getBaidu_longitude(), nuomiMovie.getBaidu_latitude()));
            nuomiMovie.setDistance(distance);
        }
    }

    private String buildUrl(String areaId) {
        if(StringUtils.isEmpty(areaId)){
            areaId = DEFAULT_AREA_ID;
        }
        StringBuilder sb = new StringBuilder("http://www.nuomi.com/cinema/");
        sb.append(areaId).append("/sub0d0/cb0-d10000-s0-o-b1-f0-p1#cinema-nav");
        return sb.toString();
    }

    private List<NuomiMovie> getCinemasInfoFromWeb(String url){
        List<NuomiMovie> list = Lists.newArrayList();
        HttpClientUtil httpClientUtil = HttpClientUtil.getInctence();
        Document document = Jsoup.parse(httpClientUtil.doGet(url));
        Elements elements = document.select("div.cinema-info");
        Gson gson = new Gson();
        for(Element element: elements){
           String eleStr = element.attr("data-cinema");
            NuomiMovie movie = gson.fromJson(eleStr, NuomiMovie.class);
            list.add(movie);
        }
        return list;
    }

    public NuomiMovie getNearestCinema(LocationForConvert location){
        List<NuomiMovie> cinemaModels = getNearByCinemas(location);
        if(CollectionUtils.isEmpty(cinemaModels)) return new NuomiMovie();
        return getNearByCinemas(location).get(0);
    }

    public List<NuomiMovie> getTopKcinemas(LocationForConvert location, Integer k){
        List<NuomiMovie> result = Lists.newArrayList();
        List<NuomiMovie> cinemaModels = getNearByCinemas(location);
        if(CollectionUtils.isEmpty(cinemaModels)) return result;
        if(cinemaModels.size() <= k) return cinemaModels;
        for(int i=0; i<k; i++){
            result.add(cinemaModels.get(i));
        }
        return result;
    }

    public Movies getMovies(){
        return DoubanUtils.getInTheatersMovies();
    }

    public MovieDetail getMovieDeatilById(Integer id) {
        return DoubanUtils.getMovieDetailById(id);
    }

    public List<MovieModel> enrichSubjectList(List<Subject> subjects){
        List<MovieModel> movieModels = Lists.newArrayList();
        if(CollectionUtils.isEmpty(subjects)) return movieModels;
        for(Subject subject: subjects){
            MovieModel model = new MovieModel(subject);
            model.setGenresStr(enrichGenres(subject.getGenres()));
            model.setCastsStr(enrichNames(subject.getCasts()));
            model.setDirectorsStr(enrichNames(subject.getDirectors()));
            movieModels.add(model);
        }
        return movieModels;
    }

    public MovieModel convertDetailToModel(MovieDetail detail){
        MovieModel model = new MovieModel(detail);
        model.setGenresStr(enrichGenres(detail.getGenres()));
        model.setCastsStr(enrichNames(detail.getCasts()));
        model.setDirectorsStr(enrichNames(detail.getDirectors()));
        return model;
    }

    private String enrichNames(List<Person> persons){
        if(CollectionUtils.isEmpty(persons)) return "";
        List<String> names = Lists.newArrayList();
        for(Person p: persons){
            names.add(p.getName());
        }
        Joiner joiner = Joiner.on(", ").skipNulls();
        return joiner.join(names);
    }

    private String enrichGenres(List<String> strings){
        if(CollectionUtils.isEmpty(strings)) return "";
        Joiner joiner = Joiner.on(",").skipNulls();
        return joiner.join(strings);
    }

    public List<MovieDetail> getMovieDetalis(){
        List<MovieDetail> movieDetails = Lists.newArrayList();
        Movies movies = DoubanUtils.getInTheatersMovies();
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future> futures = Lists.newArrayList();
        for(Subject sub: movies.getSubjects()){
            DoubanUtils utils = new DoubanUtils(sub);
            Future<MovieDetail> future= executor.submit(utils);
            futures.add(future);
        }
        executor.shutdown();
        getFutures(movieDetails, futures);

        return movieDetails;
    }

    private void getFutures(List all, List<Future> futures){
        for (Future future: futures){
            try {
                all.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
