package com.qxn.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    //创建线程池
    public static List<Thread> initPool(Runnable target,int poolSize){
        List<Thread> threads = new ArrayList<>(poolSize);
        for (int i = 1; i <= poolSize; i++) {
            Thread thread = new Thread(target, "[" + i + "]号售票口");
            threads.add(thread);
        }
        return threads;
    }
}
