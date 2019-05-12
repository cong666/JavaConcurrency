package com.chen.com.chen.concurrency.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * Created by: ccong
 * Date: 19/5/12 上午12:08
 */
public class ObjectPool<T,R> {

    private List<T> objectList;
    private Semaphore semaphore;
    private ObjectPool() {}
    //create some objects and store them in the list
    public ObjectPool(int size,Class<T> clazz) {
        objectList = new Vector<>(size);
        for(int i=0;i<size;i++) {
            try {
                objectList.add(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        semaphore = new Semaphore(size);
    }

    R requireDeviceFromPool(Function<T,R> function) {
        T t = null;
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t = objectList.remove(0);
            //image that the operation take 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return function.apply(t);//use device
        } finally {
            objectList.add(t);
            semaphore.release();
        }

    }
}
