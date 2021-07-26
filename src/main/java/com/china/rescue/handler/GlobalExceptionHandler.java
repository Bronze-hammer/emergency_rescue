package com.china.rescue.handler;

import com.china.rescue.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ServerResponse exceptionHandler(Exception e){
        logger.error(e.getMessage(), e);
        return ServerResponse.createByErrorCodeMessage(500, e.getMessage());
    }

}
