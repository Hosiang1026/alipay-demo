package com.zjs.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	@Pointcut("execution(* com.zjs.api.*.*(..))")
	public void serviceDealTime() {

	}

	@Around(value = "serviceDealTime()")
	public Object around(ProceedingJoinPoint pj) throws Throwable {
		String name = pj.getSignature().getName();
		String clazz = pj.getTarget().getClass().toString();
		long startTime = System.currentTimeMillis();
		Object obj = pj.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("耗时统计：【" + clazz + "." + name + "() -> " + (endTime - startTime) + "ms】");
		return obj;
	}
}
