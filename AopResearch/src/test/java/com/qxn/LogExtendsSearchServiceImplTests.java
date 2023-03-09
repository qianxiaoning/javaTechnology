package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.services.SearchServiceInterface;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogExtendsSearchServiceImplTests {
	@Autowired
	/*多个bean
	 * 默认按照属性名调用bean，logSearchServiceImpl
	 * 或者指定@Qualifier("logSearchServiceImpl")
	 * */
	private SearchServiceInterface logExtendsSearchServiceImpl;
	@Test
	public void testSearch() {
		logExtendsSearchServiceImpl.search("yellow sky");
	}
}
