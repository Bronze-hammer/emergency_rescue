package com.china.rescue.business.system.po;

import lombok.Data;

/**
 * @Author: xbronze
 * @CreateTime: 2022-07-21  10:10
 * @Description: TODO
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
