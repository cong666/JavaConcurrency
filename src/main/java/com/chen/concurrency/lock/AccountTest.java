package com.chen.concurrency.lock;

/**
 * Created by: ccong
 * Date: 19/5/9 下午8:33
 */
public class AccountTest {
    public static void main(String args[]) {
        Account a = Account.initAccount();
        a.setId(1);

        Account b = Account.initAccount();
        b.setId(2);

        Account c = Account.initAccount();
        c.setId(3);

        Account d = Account.initAccount();
        d.setId(4);

        Account e = Account.initAccount();
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
