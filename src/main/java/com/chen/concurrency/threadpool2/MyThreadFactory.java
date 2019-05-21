package com.chen.concurrency.threadpool2;

import java.util.concurrent.ThreadFactory;

/**
 * Created by: ccong
 * Date: 19/5/21 下午8:32
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("MyThread");
        return thread;
    }
}
