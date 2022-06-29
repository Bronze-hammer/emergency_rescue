package com.china.rescue.service.impl;

import com.china.rescue.mapper.UserMapper;
import com.china.rescue.po.User;
import com.china.rescue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return userMapper.selectUserByLogin(login);
    }

}
