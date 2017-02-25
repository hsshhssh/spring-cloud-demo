package com.hssh.spring.cloud.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hssh on 2017/2/20.
 */
@RestController
public class DemoController {

    @RequestMapping("/world")
    public String hello(@RequestParam("flag") boolean flag) {
        if(flag) {
            throw new RuntimeException();
        }
        return "Hello, I'm world";
    }

}
