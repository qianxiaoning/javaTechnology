package com.qxn.services.addServiceManual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qxn.services.SearchServiceInterface;
//组合方式，扩展功能
@Service
public class LogCombinationSearchServiceImpl implements SearchServiceInterface{
	@Autowired
	@Qualifier("defaultSearchServiceImpl")
	private SearchServiceInterface searchServiceInterface;
	@Override
	public Object search(String key) {
		System.out.println("start:" + System.nanoTime());
		Object result = searchServiceInterface.search(key);
		System.out.println("end:" + System.nanoTime());
		return result;
	}

}
