package com.chen.concurrency.threadpool2;

import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/21 下午8:24
 */
public class WorkerThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(WorkerThread.class.getName());
    @Override
    public void run() {

        LOGGER.info("work running");
        try {
            Thread.sleep(5000);
        } catch(Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("work finished");
    }
}
