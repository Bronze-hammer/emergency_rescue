package com.china.rescue.business.system.service;

import com.china.rescue.business.system.po.User;


public interface IUserService {

    public User findUserById(Long id);

    public User findUserByLogin(String login);

}
