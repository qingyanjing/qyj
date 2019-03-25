package org.wisdom.pms.bp.api.aoc;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpRequestAspect {
	private static final Logger log = LoggerFactory.getLogger(HttpRequestAspect.class);
	public static long startTime;
	public static long endTime;
	
	/*@PointCut注解表示表示横切点，哪些方法需要被横切*/
    /*切点表达式*/
	@Pointcut("execution(public * org.wisdom.pms.bp.api.service.outer.*.*(..))")
	/*切点签名*/
	public void print()
	{
		
	}
	
	/*@Before注解表示在具体的方法之前执行*/
	@Before("print()")
	public void before(JoinPoint joinPoint )
	{
		log.info("前置切面before……");
		startTime = System.currentTimeMillis();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String requestURI = request.getRequestURI();
		String remoteHost = request.getRemoteHost();
		String remoteAddr = request.getRemoteAddr();
		String remoteUser = request.getRemoteUser();
		String requestMethod = request.getMethod();
		String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		log.info("请求url=" + requestURI + ",请求host=" + remoteHost + ",请求用户=" + remoteUser + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
	}
	
	/*@After注解表示在方法执行之后执行*/
	@After("print()")
	public void after()
	{
		endTime = System.currentTimeMillis() - startTime;
		log.info("后置切面after……");
	}
	
	/*@AfterReturning注解用于获取方法的返回值*/
	@AfterReturning(pointcut = "print()", returning = "object")
	public void getAfterReturn(Object object)
	{
		log.info("本次接口耗时={}ms", endTime);
        log.info("afterReturning={}", object.toString());
	}
}
