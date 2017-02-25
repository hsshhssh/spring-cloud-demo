package com.hssh.spring.cloud.service.rpc;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hssh on 2017/2/20.
 */
@Service
public class WorldService {

    public static final String SERVICE_NAME = "http://service-world";

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFail")
    public String hello(boolean flag) {
        return restTemplate.getForEntity(SERVICE_NAME+"/world?flag=" + flag, String.class).getBody();
    }


    public String helloFail(boolean flag) {
        return "say hello fail, param:" + flag;

    }

}
