package com.pixivx.www.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UrlController {
    @RequestMapping("/add_topic")
    public String add_topic(){
        return "add_topic";
    }
}
