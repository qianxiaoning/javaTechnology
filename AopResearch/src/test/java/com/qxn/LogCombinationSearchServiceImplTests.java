package com.qxn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qxn.services.SearchServiceInterface;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogCombinationSearchServiceImplTests {
	@Autowired	
	private SearchServiceInterface logCombinationSearchServiceImpl;
	@Test
	public void testSearch() {
		logCombinationSearchServiceImpl.search("yellow sky");
	}
}
