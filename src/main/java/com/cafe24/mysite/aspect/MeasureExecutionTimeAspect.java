package com.cafe24.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureExecutionTimeAspect {
	
//	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		
		// method 실행
		Object result = pjp.proceed();
		
		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		// around 하고 있는 클래스의 이름 (point cut 당한 메소드)
		String className = pjp.getTarget().getClass().getName();
		// 메소드의 이름
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time]["+ taskName +"] : " + totalTime + "milisecond");
		
		return result;
	}
}
