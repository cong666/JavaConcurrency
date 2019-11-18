package com.chen.concurrency.threadpool2;

import java.util.concurrent.*;

/**
 * Created by: ccong
 * Date: 19/5/21 下午8:25
 */
public class ThreadPoolTest {
    public static void main(String args[]) {
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
        3, 10, TimeUnit.SECONDS,
        workQueue,
        new MyThreadFactory(),
        new MyRejectedExecutionHandler());

        //This thread pool can launch 3 thread max one time
        executor.submit(new WorkerThread());
        executor.submit(new WorkerThread());
        executor.submit(new WorkerThread());
        executor.submit(new WorkerThread());
        executor.submit(new WorkerThread());
        //如果任务过多的时候 任务会被reject
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        //executor.submit(new WorkerThread());
        executor.shutdown();
    }
}
