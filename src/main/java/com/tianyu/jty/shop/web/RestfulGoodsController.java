package com.tianyu.jty.shop.web;

import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.shop.entity.Goods;
import com.tianyu.jty.shop.entity.RestResult;
import com.tianyu.jty.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by xtao on 2015/11/22.
 */

@Controller
@RequestMapping("api/goods")
public class RestfulGoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RestResult goodsList(HttpServletRequest request) {
        Page<Goods> page = getPage(request);
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        page = goodsService.search(page, filters);
        return RestResult.success().withContent(page);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult create(@RequestBody Goods goods) {
        goodsService.save(goods);
        return RestResult.success();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public RestResult getGoodsById(@PathVariable Integer id, HttpServletRequest request){
        return RestResult.success().withContent(goodsService.get(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public RestResult update(@RequestBody Goods goods) {
        goodsService.save(goods);
        return RestResult.success();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult delete(@PathVariable Integer id, HttpServletRequest request) {
        goodsService.delete(id);
        return RestResult.success();
    }
}
