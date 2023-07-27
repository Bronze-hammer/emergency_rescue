package com.china.rescue.aop;

import com.china.rescue.annotations.OperatorLog;
import com.china.rescue.business.system.po.User;
import com.china.rescue.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: xbronze
 * @date: 2023-07-27 16:41
 * @description: TODO
 */
@Aspect
@Component
@Slf4j
public class OperatorLogApsect {

    /*
     * 实例："execution(public * com.xhx.springboot.controller.*.*(..))";
     * 切点表达式中   ..两个点表明多个     *代表一个，
     * 上面表达式代表切入com.xhx.springboot.controller包下的所有类的所有方法，方法参数不限，返回类型不限。
     * 其中访问修饰符可以不写，不能用*，第一个*代表返回类型不限，第二个*表示所有类，第三个*表示所有方法，..两个点表示方法里的参数不限。
     * 然后用@Pointcut切点注解，写在一个空方法上面，一会儿在Advice通知中，直接调用这个空方法就行了；
     * 也可以把切点表达式写在Advice通知中的，单独定义出来主要是为了好管理。
     */


    @Pointcut("execution(public * com.china.rescue.business.system.controller.UserController.loginWithId(..))")
    public void pointCut() {}

    @Pointcut("@annotation(com.china.rescue.annotations.OperatorLog)")
    public void operatorLog() {}

    /**
     * AOP切面实现接口日志记录
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void doAfterReturningAdvice(JoinPoint joinPoint, Object result) {
        ServerResponse response = (ServerResponse) result;
        User data = (User) response.getData();
        log.info("target:{}, result:{}", joinPoint.getTarget(), data.toString());
    }

    /**
     * AOP切面 + 自定义注解实现接口日志记录
     */
    @AfterReturning(value = "operatorLog()&&@annotation(operatorLog)", returning = "result")
    public void doAfterReturningAdviceAnnotation(JoinPoint joinPoint, Object result, OperatorLog operatorLog) {
        log.info("operator:{}, module:{}, target:{}, result:{}",
                operatorLog.operate(),
                operatorLog.module(),
                joinPoint.getTarget(),
                result);
    }
}
