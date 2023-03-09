package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.services.SearchServiceInterface;
import com.qxn.services.addServiceAutomatic.JdkProxyObjectFactory01;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogJdkSearchServiceImplTests01 {
	@Autowired
	private SearchServiceInterface defaultSearchServiceImpl;
	@Autowired
	private JdkProxyObjectFactory01 jdkProxyObjectFactory01;
	@Test
	public void testSearch() {
		defaultSearchServiceImpl.search("yellow sky");
	}
	//代理方式，自动功能扩展
	@Test
	public void testJdkService() {
		SearchServiceInterface searchService = (SearchServiceInterface)jdkProxyObjectFactory01.newJdkProxy(
				defaultSearchServiceImpl);
		searchService.search("blue sky");
	}
}
