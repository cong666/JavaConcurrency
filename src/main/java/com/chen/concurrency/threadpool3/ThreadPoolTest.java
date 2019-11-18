package com.chen.concurrency.threadpool3;

import java.util.concurrent.*;

/**
 * Created by: ccong
 * Date: 19/11/18 下午9:42
 */
public class ThreadPoolTest {
    public static void main(String args[]) {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(()-> {
            System.out.println("run1");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        executor.execute(()->{
            System.out.println("run2");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");
        //关闭threadpool 否则会一直运行下去
        awaitTerminationAfterShutdown(executor);
    }

    public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
