package com.qxn.services;

import org.springframework.stereotype.Service;

@Service
public class DefaultSearchServiceImpl implements SearchServiceInterface{

	@Override
	public Object search(String key) {
		System.out.println("search by " + key);
		return null;
	}

}
