package com.chen.concurrency.threadpool;

/**
 * Created by: ccong
 * Date: 19/5/20 下午10:56
 */
public class Result {
    private String result;
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                '}';
    }
}
