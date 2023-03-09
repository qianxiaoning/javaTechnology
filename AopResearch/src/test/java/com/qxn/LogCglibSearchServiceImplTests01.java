package com.qxn;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.services.SearchServiceInterface;
import com.qxn.services.addServiceAutomatic.CglibProxyObjectFactory01;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogCglibSearchServiceImplTests01 {
	@Autowired
	private SearchServiceInterface defaultSearchServiceImpl;
	@Autowired
	@Qualifier("cglibProxyObjectFactory01")
	private CglibProxyObjectFactory01 proxyFactory;
	@Test
	public void testSearch() {
		defaultSearchServiceImpl.search("yellow sky");
	}	
	//代理方式，自动功能扩展
	@Test
	public void testCglibService() {
		SearchServiceInterface searchService = (SearchServiceInterface)proxyFactory.newCglibProxy(
				defaultSearchServiceImpl);
		searchService.search("blue sky");
	}
	@Test
	public void testCglibObjService() {
//		Object target = new Object();
//		Object proxy = proxyFactory.newCglibProxy(target);
//		System.out.println(proxy.hashCode());
		Date target = new Date();
		Date proxy = (Date)proxyFactory.newCglibProxy(target);
		proxy.getTime();
	}
}
