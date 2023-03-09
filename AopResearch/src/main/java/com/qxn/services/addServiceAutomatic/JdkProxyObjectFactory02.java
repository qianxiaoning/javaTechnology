package com.qxn.services.addServiceAutomatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.stereotype.Component;
@Component
public class JdkProxyObjectFactory02{
	/*为目标对象创建代理对象
	 * */
	public Object newJdkProxy(Object target) {
		Object proxy = Proxy.newProxyInstance(
				target.getClass().getClassLoader(),//loader类加载器 
				target.getClass().getInterfaces(),//interfaces代理对象要实现的接口
				new JdkInvocationHandler(target));//h handler具体业务处理器
		return proxy;
	}	
	/*此类对象负责为代理对象执行扩展业务
	 * */
	class JdkInvocationHandler implements InvocationHandler{
		private Object target;
		public JdkInvocationHandler(Object target) {
			this.target = target;
		}
		/*使用jdk的动态代理对象执行业务方法时，此方法会自动执行，在这进行功能扩展
		 * */
		@Override
		public Object invoke(
				Object proxy,//代理对象 
				Method method,//目标方法对象	
				Object[] args//调用目标方法时传递的实际参数
				) throws Throwable {
			System.out.println("start:" + System.nanoTime());
			Object result = method.invoke(target, args);
			System.out.println("end:" + System.nanoTime());
			return result;
		}
	}
}
