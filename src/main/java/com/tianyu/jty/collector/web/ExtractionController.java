package com.tianyu.jty.collector.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.TopicType;
import com.tianyu.jty.collector.service.ExtractorService;
import com.tianyu.jty.collector.service.MessageService;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.shop.entity.RestResult;
import com.tianyu.jty.wechat.entity.LocalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/8.
 */

@Controller
@RequestMapping("priceless/extraction")
public class ExtractionController extends BaseController {

    @Autowired
    private ExtractorService extractorService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/local", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getPriceResultByType(HttpServletRequest request){
        Map map = Maps.newHashMap();
        String name = request.getParameter("name");
        try {
             map.put("name",new String(name.getBytes("iso8859-1"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("lng", request.getParameter("lng"));
        map.put("lat", request.getParameter("lat"));
        List<Map<String, Object>> list = extractorService.doExtract(TopicType.GROUP,map);
        String result = messageService.convertListToString(list,new LocationForConvert(map.get("lng").toString(), map.get("lat").toString()));
        List<LocalModel> models = Lists.newArrayList();
        LocalModel model = new LocalModel();
        model.setAddress("cao an gong lu");
        model.setDiscount("80%");
        model.setDistance(100);
        model.setOriginPrice("100");
        model.setPrice("80");
        model.setPic("http://i2.s2.dpfile.com/pc/cdc1c356de9c86beaafbcf5550da9774(249x249)/thumb.jpg");
        model.setShopName("qiao jiang nan");
        model.setWebsiteName("dianping");
        model.setStars(4);
        model.setWebsiteUrl("www.dianping.com");
        models.add(model);
        return RestResult.success().withContent(models);
    }
}
