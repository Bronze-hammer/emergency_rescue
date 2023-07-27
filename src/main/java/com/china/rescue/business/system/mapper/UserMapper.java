package com.china.rescue.business.system.mapper;

import com.china.rescue.business.system.po.User;

public interface UserMapper {

    public User selectUserById(Long id);

    public User selectUserByLogin(String login);

}
