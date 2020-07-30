package com.qxn;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.qxn.thread.ThreadPool;
import org.junit.Test;

//模拟窗口卖票业务
public class LockTest implements Runnable{

	private int num = 100;
	private Lock lock = new ReentrantLock();
	
	//定义线程卖票操作
	@Override
	public void run() {
		while(true) {
			lock.lock();
			if(num>0) {
				System.out.println(Thread.currentThread().getName()+"卖出第"+(101-num)+"张票");
				num--;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}else {
				break;//跳出循环
			}
		}

	}

	@Test
	public void test01() {
		Runnable runnable = new LockTest();
		//定义线程池 初始化3个线程
		List<Thread> pools = ThreadPool.initPool(runnable, 3);
		for (Thread thread : pools) {
			thread.start();
		}
		for(;;); //防止线程提前结束
	}
}
