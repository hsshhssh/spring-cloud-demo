package com.hssh.spring.cloud.service;


import com.hssh.spring.cloud.persistent.dao.UserMapper;
import com.hssh.spring.cloud.persistent.entity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hssh on 2017/2/19.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Transactional(readOnly = false)
    public int insert(User user) {
        return userMapper.insertSelective(user);
    }

    public User select(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
