package com.qxn.lock;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import com.qxn.stream.LuaStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qxn.mapper.MysqlMapper;

import redis.clients.jedis.JedisCluster;
@Component("redisLock")
public class RedisLock implements Lock{
	
	@Autowired
	private JedisCluster jedisCluster;
	private ThreadLocal<String> thread = new ThreadLocal<>();
	private static final String key = "REDIS_LOCK";
	//表示尝试加锁
	@Override
	public boolean tryLock() {
		try {
			String value = UUID.randomUUID().toString();
			//NX不允许覆盖，XX可以覆盖
			//EX单位秒，PX单位毫秒
			String result = jedisCluster.set(key, value, "NX", "PX", 800);
			if(result.equalsIgnoreCase("ok")) {
				//加锁成功，把值存入ThreadLocal，给解锁用
				thread.set(value);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	//表示加锁机制，递归直到tryLock()成功
	@Override
	public void lock() {
		if(tryLock()) {
			//表示加锁成功
			return;
		}
		try {
			//不成功，就等10ms再次请求
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock();
	}

	@Override
	public void unlock() {
		String uuid = thread.get();
		//使用lua脚本，保证数据的原子性
		String script = LuaStream.getLuaFileString();
		//脚本,key,value
		//获取数据/判断一致/删除数据，一起执行，原子性
		jedisCluster.eval(script, Arrays.asList(key),Arrays.asList(uuid));
	}
	
	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}
