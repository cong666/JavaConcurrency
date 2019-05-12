package com.chen.com.chen.concurrency.semaphore;

/**
 * Created by: ccong
 * Date: 19/5/12 上午12:24
 */
public class ObjectPoolTest {
    public static void main(String args[]) {
        ObjectPool<Device,String> devicePool = new ObjectPool<>(10,Device.class);

        for(int i =0;i<20;i++) {
            new Thread(()->{
                System.out.println(devicePool.requireDeviceFromPool(param->{
                    return "use the device : "+ param.toString();
                }));
            }).start();
        }
    }
}
