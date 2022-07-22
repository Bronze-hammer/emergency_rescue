package com.china.rescue.common;

/**
 * @creator xuzihui
 * @date 2021-7-26 14:49:18
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    UNAUTHORIZED(401, "认证失败，拒绝访问！"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请联系管理员！");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}
