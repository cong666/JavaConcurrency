package com.chen.concurrency.lock;

/**
 * Created by: ccong
 * Date: 19/5/9 下午8:33
 */
public class Account2Test {
    public static void main(String args[]) {
        Account2 a = Account2.initAccount();
        a.setId(1);

        Account2 b = Account2.initAccount();
        b.setId(2);

        Account2 c = Account2.initAccount();
        c.setId(3);

        Account2 d = Account2.initAccount();
        d.setId(4);

        Account2 e = Account2.initAccount();
        e.setId(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(b,50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(c,50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(d,50);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(e,50);
            }
        }).start();
    }
}
