package com.chen.com.chen.concurrency.semaphore;

/**
 * Created by: ccong
 * Date: 19/5/12 上午12:08
 */
public class Device {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
