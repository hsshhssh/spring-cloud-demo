package com.hssh.spring.cloud.web;

import com.hssh.spring.cloud.service.rpc.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hssh on 2017/2/25.
 */
@RestController
public class RpcTestController {

    @Autowired
    private WorldService worldService;

    @RequestMapping("/rpc")
    public String rpc(@RequestParam("flag") boolean flag) {
        return worldService.hello(flag);
    }

}
