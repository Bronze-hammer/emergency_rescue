package com.china.rescue.handler;

import com.china.rescue.common.ResponseCode;
import com.china.rescue.common.ServerResponse;
import com.china.rescue.component.CustException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = CustException.class)
    public ServerResponse CustExceptionHandler(CustException e){
        logger.error(e.getMessage(), e);
        return ServerResponse.createByCustExceptionCodeMessage(e);
    }

    @ExceptionHandler(value = Exception.class)
    public ServerResponse ExceptionHandler(Exception e){
        logger.error(exceptionMsgHandler(e), e);
        return ServerResponse.createByOtherExceptionCodeMessage(ResponseCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理异常信息，输出异常文本
     * @param e
     * @return
     */
    public static String exceptionMsgHandler(Exception e) {
        StringBuffer message = new StringBuffer();
        StackTraceElement [] exceptionStack=e.getStackTrace();
        message.append(e.toString()); // java.lang.NumberFormatException: For input string: "s"
        for(StackTraceElement ste : exceptionStack) {
            message.append("\n\t at " + ste); // at ***.***.**(**)
        }
        return message.toString();
    }

}
