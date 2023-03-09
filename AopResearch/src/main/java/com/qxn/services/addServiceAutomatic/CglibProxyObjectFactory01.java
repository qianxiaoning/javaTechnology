package com.qxn.services.addServiceAutomatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
@Component
public class CglibProxyObjectFactory01 implements InvocationHandler{
	private Object target;
	/*为目标对象创建代理对象
	 * */
	public Object newJdkProxy(Object target) {
//		target
		this.target = target;
		Object proxy = Proxy.newProxyInstance(
				target.getClass().getClassLoader(),//loader类加载器 
				target.getClass().getInterfaces(),//interfaces代理对象要实现的接口
				this);//h handler具体业务处理器
		return proxy;
	}
	/*为目标对象创建cglib代理对象
	 * */
	public Object newCglibProxy(Object target) {
		//构建Enhancer对象，此对象负责创建代理对象
		Enhancer enhancer = new Enhancer();
		//设置代理对象要继承的目标对象类型
		enhancer.setSuperclass(target.getClass());
		//设置具体业务处理对象
		enhancer.setCallback(new CglibMethodInterceptor());
		//创建代理对象并返回
		return enhancer.create();
	}
	/*cglib代理对象的处理器
	 * */
	class CglibMethodInterceptor implements MethodInterceptor{
		/*执行代理对象的业务方法时，会自动调用此方法
		 * */
		@Override
		public Object intercept(
				Object proxy, 
				Method method,//目标方法对象 
				Object[] args, //调用目标方法时传入的实际参数
				MethodProxy methodProxy//
				) throws Throwable {
			System.out.println("cglib.log.start:"+System.nanoTime());
			//调用目标方法
			Object result = methodProxy.invokeSuper(proxy, args);
			System.out.println("cglib.log.end:"+System.nanoTime());
			return result;
		}
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
		//通过反射执行目标对象method方法
		Object result = method.invoke(target, args);
		System.out.println("end:" + System.nanoTime());
		return result;
	}
}
