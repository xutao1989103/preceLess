package com.tianyu.jty.content.web;

import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.content.entity.Topic;
import com.tianyu.jty.content.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/2.
 */

@Controller
@RequestMapping("priceLess/topics")
public class TopicController extends BaseController{
    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        return "topic/topicList";
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllTopics(HttpServletRequest request){
        Page<Topic> page = getPage(request);
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        page = topicService.search(page, filters);
        return getEasyUIData(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Topic getTopicById(@PathVariable("id") Integer id){
        return (Topic)topicService.get(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model){
        model.addAttribute("topic", new Topic());
        model.addAttribute("action", "create");
        return "topic/topicForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid Topic topic, Model model){
        topicService.save(topic);
        return "success";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("topic", topicService.get(id));
        model.addAttribute("action", "update");
        return "topic/topicForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@Valid @ModelAttribute @RequestBody Topic topic, Model model){
        topicService.update(topic);
        return "success";
    }

    @RequestMapping(value = "delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id){
        topicService.delete(id);
        return "success";
    }

    @ModelAttribute
    public void topics(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
        if (id != -1) {
            model.addAttribute("topic", topicService.get(id));
        }
    }

}
