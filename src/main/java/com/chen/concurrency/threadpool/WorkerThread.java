package com.chen.concurrency.threadpool;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/20 下午10:16
 */
public class WorkerThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(WorkerThread.class.getName());

    private Result result;

    public WorkerThread(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public void run() {
        LOGGER.info("work running");
        try {
            Thread.sleep(5000);
        } catch(Exception e) {
            e.printStackTrace();
        }
        result.setResult("success");
        LOGGER.info("work finished");
    }
}
