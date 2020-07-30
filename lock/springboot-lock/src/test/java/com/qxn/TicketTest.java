package com.qxn;

import com.qxn.thread.ThreadPool;
import org.junit.Test;

import java.util.List;

//模拟窗口卖票业务
public class TicketTest implements Runnable{
	private int num = 100;
	
	//定义线程卖票操作
	@Override
	public void run() {
		while(true) {
			if(num>0) {
				System.out.println(Thread.currentThread().getName()+"卖出第"+(101-num)+"张票");
				num--;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				break;//跳出循环
			}
		}
		
	}
	
	@Test
	public void test01() {
		Runnable runnable = new TicketTest();
		//定义线程池 初始化3个线程
		List<Thread> pools = ThreadPool.initPool(runnable, 3);
		for (Thread thread : pools) {
			thread.start();
		}
		for(;;); //防止线程提前结束
	}
	
}
