package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //aop로 동작하게 함
@Component //Spring bean으로 관리
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //controller 하위에 지정한다는 문구
    private void cut() {}

    @Before("cut()") //cut() 메소드가 실행되기 이전에 실행할 메서드에다 이 코드를 작성한다.
    public void before(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs(); //메서드에 들어가는 매개변수 배열
        for(Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}
