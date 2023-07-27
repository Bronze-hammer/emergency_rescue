package com.china.rescue.annotations;

import java.lang.annotation.*;

/**
 * @author xbronze
 * @date 2023-7-27 16:38:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatorLog {

    /**
     * 操作
     * @return
     */
    String operate();

    /**
     * 模块
     * @return
     */
    String module();

}
