package com.chen.concurrency.threadpool2;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/21 下午8:36
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    private static final Logger LOGGER = Logger.getLogger(MyRejectedExecutionHandler.class.getName());
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        LOGGER.info(r.toString()+" is rejected");
    }
}
