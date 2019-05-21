package com.chen.concurrency.future;

import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/21 下午9:17
 */
public class MakeTeaProcess {

    private static final Logger LOGGER = Logger.getLogger(MakeTeaProcess.class.getName());

    public static void main(String args[]) {
        new MakeTeaProcess().makeTea();
    }

    public void makeTea() {
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2, 10,
                TimeUnit.SECONDS,
                workQueue);

        FutureTask<String> taskOfProcess2 = new FutureTask<>(new Process2());
        FutureTask<String> taskOfProcess1 = new FutureTask<>(new Process1(taskOfProcess2));
        executor.submit(taskOfProcess1);
        executor.submit(taskOfProcess2);

        try {
            String result = taskOfProcess1.get();
            LOGGER.info(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //clean the pot, boil water , make tea
    class Process1 implements Callable<String> {

        private FutureTask<String> process2;
        public Process1(FutureTask<String> process2) {
            this.process2 = process2;
        }
        @Override
        public String call() {
            //clean the pot
            LOGGER.info("clean the pot, 5 seconds");
            try {
                Thread.sleep(5000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            //clean the pot
            LOGGER.info("boil the water,10 seconds");
            try {
                Thread.sleep(10000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            LOGGER.info("wait for Process2 taking some tea");
            //wait for process2 taking some tea
            try {
                String tea = process2.get();
                LOGGER.info("Process2 got some tea :"+tea);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //clean the pot
            LOGGER.info("begin to make tea, 5 seconds");
            try {
                Thread.sleep(5000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return "Tea is made successful";
        }
    }

    //clean the tea pot, clean cup , go take some tea
    class Process2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            //clean the pot
            LOGGER.info("clean the tea pot, 5 seconds");
            try {
                Thread.sleep(5000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            //clean the pot
            LOGGER.info("clean cup ,5 seconds");
            try {
                Thread.sleep(5000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            //clean the pot
            LOGGER.info("go take some tea, 30 seconds");
            try {
                Thread.sleep(30000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return "Black Tea";
        }
    }
}
