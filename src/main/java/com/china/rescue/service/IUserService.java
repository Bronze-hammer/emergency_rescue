package com.china.rescue.service;

import com.china.rescue.po.User;


public interface IUserService {

    public User findUserById(Long id);

    public User findUserByLogin(String login);

}
