package com.china.rescue.business.system.service.impl;

import com.china.rescue.business.system.mapper.UserMapper;
import com.china.rescue.business.system.po.User;
import com.china.rescue.business.system.service.IUserService;
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
