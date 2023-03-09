package com.qxn;

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
public class LogSpringAopServiceImplTests {
	@Autowired
	@Qualifier("defaultSearchServiceImpl")
	private SearchServiceInterface searchService;
	@Autowired
	@Qualifier("cglibProxyObjectFactory01")
	private CglibProxyObjectFactory01 proxyFactory;
	@Test
	public void testSearch() {
		searchService.search("yellow sky");
	}	
}
