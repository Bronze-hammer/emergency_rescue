package com.china.rescue.mapper;

import com.china.rescue.po.User;

public interface UserMapper {

    public User selectUserById(Long id);

    public User selectUserByLogin(String login);

}
