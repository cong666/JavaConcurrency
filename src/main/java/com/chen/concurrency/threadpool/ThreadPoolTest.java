package com.chen.concurrency.threadpool;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/20 下午9:35
 */
public class ThreadPoolTest {

    private static final Logger LOGGER = Logger.getLogger(ThreadPoolTest.class.getName());

    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Result result=new Result();
        Future<Result> future = executor.submit(new WorkerThread(result),result);
        try {
            Result re = future.get();
            LOGGER.info("result : "+re);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LOGGER.info("end");
    }
}
