package com.qxn;

import java.util.List;
import java.util.concurrent.locks.Lock;

import com.qxn.thread.ThreadPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

//模拟窗口卖票业务
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedislLockTest{
	private int num = 100;
	@Autowired
	@Qualifier("redisLock")
	private Lock lock;
	@Autowired
	private JedisCluster jedisCluster;

	class window implements Runnable {
		//定义线程卖票操作
		@Override
		public void run() {
			while(true) {
				lock.lock();
				if(num>0) {
					System.out.println(Thread.currentThread().getName()+"卖出第"+(101-num)+"张票");
					String ticket = jedisCluster.hget("windows",Thread.currentThread().getName());
					jedisCluster.hset("windows",Thread.currentThread().getName(), ticket+","+(101-num));
					num--;
				}else {
					break;//跳出循环
				}
				lock.unlock();
			}
		}
	}

	@Test
	public void test01() {
		Runnable window = new window();
		//定义线程池 初始化3个线程
		List<Thread> pools = ThreadPool.initPool(window, 3);
		for (Thread thread : pools) {
			thread.start();
		}
		for(;;); //防止线程提前结束
	}
}
