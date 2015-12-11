package com.tianyu.jty.content.web;

import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.content.entity.ListData;
import com.tianyu.jty.content.entity.Topic;
import com.tianyu.jty.content.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 网页内容Controller
 * @author xtao
 * @date 2015-11-2
 */

@Controller
@RequestMapping("priceLess/content")
public class ContentController extends BaseController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    @ResponseBody
    public ListData<Topic> getAllNodes(){
        return new ListData<Topic>(topicService.getAll(true));
    }
}
