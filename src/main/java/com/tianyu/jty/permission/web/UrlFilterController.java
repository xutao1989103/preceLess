package com.tianyu.jty.permission.web;

import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.permission.entity.UrlFilter;
import com.tianyu.jty.permission.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/16.
 */

@Controller
@RequestMapping("priceLess/urlPerms")
public class UrlFilterController extends BaseController {

    @Autowired
    private UrlFilterService urlFilterService;

    /**
     * 默认页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        urlFilterService.initFilterChain();
        return "urlperm/urlpermList";
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllUrlFilters(HttpServletRequest request){
        Page<UrlFilter> page = getPage(request);
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        page = urlFilterService.search(page, filters);
        return getEasyUIData(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UrlFilter getUrlFilterById(@PathVariable("id") Integer id){
        return (UrlFilter)urlFilterService.get(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model){
        model.addAttribute("filter", new UrlFilter());
        model.addAttribute("action", "create");
        return "urlperm/urlpermForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid UrlFilter filter, Model model){
        urlFilterService.saveAndRefresh(filter);
        return "success";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("filter", urlFilterService.get(id));
        model.addAttribute("action", "update");
        return "urlperm/urlpermForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@Valid @ModelAttribute @RequestBody UrlFilter filter, Model model){
        urlFilterService.updateAndRefresh(filter);
        return "success";
    }

    @RequestMapping(value = "delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id){
        urlFilterService.deleteAndRefresh(id);
        return "success";
    }

    @ModelAttribute
    public void urlFilsters(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
        if (id != -1) {
            model.addAttribute("filter", urlFilterService.get(id));
        }
    }

    @PostConstruct
    public void init(){
        urlFilterService.initFilterChain();
    }
}
