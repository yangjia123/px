package com.pixivx.www.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class TestJSPController {
    @RequestMapping("/index")
    public String index()
    {
        return "login_main";
    }

    @RequestMapping("/test")
    public String test()
    {
        return "index";
    }
}
