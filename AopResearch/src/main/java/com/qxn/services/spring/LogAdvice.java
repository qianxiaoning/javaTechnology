package com.qxn.services.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
/*此对象负责在目标业务对象基础之上添加日志扩展功能
 * */
public class LogAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("spring.aop.log.start:" + System.nanoTime());
		Object result = invocation.proceed();
		System.out.println("spring.aop.log.end:" + System.nanoTime());
		return result;
	}
}
