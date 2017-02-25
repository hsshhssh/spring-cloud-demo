package com.hssh.spring.cloud.web;

import com.hssh.spring.cloud.component.zkconf.Value;
import com.hssh.spring.cloud.component.zkdbcp.DataSourceName;
import com.hssh.spring.cloud.persistent.entity.dto.UserDto;
import com.hssh.spring.cloud.persistent.entity.model.User;
import com.hssh.spring.cloud.persistent.entity.vo.UserVo;
import com.hssh.spring.cloud.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hssh on 2017/2/25.
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper mapper;

    @Value(path = "/config/deleteFlag", key = "allow")
    private String deleteFlag;

    @DataSourceName(DataSourceName.master)
    @RequestMapping(value = "/user", method = RequestMethod.PUT )
    public int add(@RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        userService.insert(user);
        return user.getId();
    }

    @DataSourceName(DataSourceName.salve)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserVo get(@PathVariable int id) {
        User user = userService.select(id);
        return mapper.map(user, UserVo.class);
    }

    @DataSourceName(DataSourceName.master)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public int update(@PathVariable int id, @RequestBody UserDto userDto) {
        // 设置开关是否允许修改数据
        if("y".equals(deleteFlag) || "Y".equals(deleteFlag)) {
            User user = mapper.map(userDto, User.class);
            user.setId(id);
            return userService.update(user);
        }

        return 0;
    }

}
