package com.china.rescue.common;

/**
 * 自定义异常
 * @author xuzh
 */
public class CustException extends RuntimeException {

    private Integer code;
    private String msg;

    public CustException(String msg){
        this.msg = msg;
    }

    public CustException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public CustException(String msg, Throwable e){
        super(msg, e);
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
