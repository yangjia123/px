package com.pixivx.www.Controller;

import com.pixivx.www.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController

@RequestMapping(value = "/topic")
public class Topic_Contoller {
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/Add_topic")
    public String say(@RequestParam(value = "topic_name")String name,
                      @RequestParam(value = "description")String desc,
                      @RequestParam(value = "user_id")String sid,
                      HttpServletResponse response
                      )throws IOException
    {
        int id = Integer.parseInt(sid);
    if(topicService.AddTopic(name,desc,id)) {
        response.sendRedirect("/index");
        return "创建成功";

    }
    else {
        response.sendRedirect("/add_topic");
        return "创建失败";
    }
    }
}
