package com.qxn.services.spring;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.stereotype.Component;
@Component
public class LogAdvisor extends StaticMethodMatcherPointcutAdvisor {
	public LogAdvisor() {
		setAdvice(new LogAdvice());
	}
	/*此方法的返回值用于告诉Spring框架，是否要在这个方法执行时切入扩展功能
	 * */
	@Override
	public boolean matches(
			Method method, 
			Class<?> targetClass
			) {
		return method.getName().endsWith("search");//执行search方法
	}

}
