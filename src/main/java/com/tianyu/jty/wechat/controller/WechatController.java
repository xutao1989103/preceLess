package com.tianyu.jty.wechat.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.TopicType;
import com.tianyu.jty.collector.entity.douban.MovieDetail;
import com.tianyu.jty.collector.entity.douban.Movies;
import com.tianyu.jty.collector.service.ExtractorService;
import com.tianyu.jty.collector.service.MessageService;
import com.tianyu.jty.collector.service.MovieService;
import com.tianyu.jty.collector.utils.DoubanUtils;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.wechat.entity.LocalModel;
import com.tianyu.jty.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/12/2.
 */

@Controller
@RequestMapping("priceless")
public class WechatController extends BaseController {

    @Autowired
    private WechatService wechatService;
    @Autowired
    private ExtractorService extractorService;
    @Autowired
    private MessageService messageService;
    /**
     * 默认页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String main(HttpServletRequest request,Model model) {
        Map<String, String> map = wechatService.getParams(request);
        model.addAttribute("appid",map.get("appid"));
        model.addAttribute("timestamp",map.get("timestamp"));
        model.addAttribute("nonceStr",map.get("nonceStr"));
        model.addAttribute("signature",map.get("signature"));
        return "wechat/main";
    }

    @RequestMapping(value = "/local", method = RequestMethod.GET)
    public String getPage(HttpServletRequest request, Model model){
        String lng = request.getParameter("lng");
        String lat = request.getParameter("lat");
        LocationForConvert location = new LocationForConvert(lng, lat);
        Map params = Maps.newHashMap();
        params.put("name",messageService.getAddressByLocation(location));
        params.put("lng", location.getLng());
        params.put("lat", location.getLat());
        System.out.println("+++++++++++++++++++getpage:" + params);

        List<LocalModel> models;
        List<Map<String, Object>> list = extractorService.doExtract(TopicType.GROUP, params);
        models = messageService.convertListToLocals(list,location, 5);

        model.addAttribute("models",models);
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        return "wechat/search";
    }

    @RequestMapping(value = "/local", method = RequestMethod.POST)
    public String search(HttpServletRequest request, Model model){
        Map params = Maps.newHashMap();
        String keyword = request.getParameter("keyword");
        String lng = request.getParameter("lng");
        String lat = request.getParameter("lat");
        params.put("name",keyword);
        params.put("lng", lng);
        params.put("lat", lat);
        System.out.println("+++++++++++++++++++postpage:" + params);

        List<LocalModel> models;
        List<Map<String, Object>> list = extractorService.doExtract(TopicType.GROUP, params);
        LocationForConvert location = new LocationForConvert(lng, lat);
        models = messageService.convertListToLocals(list,location, 2);

        model.addAttribute("models",models);
        model.addAttribute("keyword", keyword);
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        return "wechat/search";
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public String getTicketPage(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public String doTicketSearch(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getCarPage(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public String doCarSearch(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/trip", method = RequestMethod.GET)
    public String getTripPage(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/trip", method = RequestMethod.POST)
    public String doTripSearch(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String doHomeSearch(HttpServletRequest request, Model model){
        return "wechat/doing";
    }

}
