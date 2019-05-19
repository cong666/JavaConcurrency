package com.chen.concurrency.lock;

/**
 * Created by: ccong
 * Date: 19/5/9 下午8:33
 */
public class Account3Test {
    public static void main(String args[]) {
        Account3 a = Account3.initAccount();
        a.setId(1);

        Account3 b = Account3.initAccount();
        b.setId(2);

        Account3 c = Account3.initAccount();
        c.setId(3);

        Account3 d = Account3.initAccount();
        d.setId(4);

        Account3 e = Account3.initAccount();
        e.setId(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(b,10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doTransfer(c,50);
            }
        }).start();

    }
}
