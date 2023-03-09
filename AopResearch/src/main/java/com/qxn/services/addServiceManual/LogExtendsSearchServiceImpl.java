package com.qxn.services.addServiceManual;

import org.springframework.stereotype.Service;

import com.qxn.services.DefaultSearchServiceImpl;
//用继承进行功能扩展
@Service
public class LogExtendsSearchServiceImpl extends DefaultSearchServiceImpl {
	@Override
	public Object search(String key) {
		System.out.println("start:" + System.nanoTime());
		Object result = super.search(key);
		System.out.println("end:" + System.nanoTime());
		return result;
	}
}
