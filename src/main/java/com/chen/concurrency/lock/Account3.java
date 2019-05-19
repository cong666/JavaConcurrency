package com.chen.concurrency.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/19 下午7:23
 */
public class Account3 {
    private static Logger LOGGER = Logger.getLogger(Account3.class.getName());
    private int id;
    private int deposit;

    private final Lock lock = new ReentrantLock();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Account3() {}

    public static Account3 initAccount() {
        Account3 account = new Account3();
        account.deposit = 160;
        return account;
    }

    //use tryLock(the thread will not be blocked if it can not get the lock (synchronized thread will be blocked))
    public void doTransfer(Account3 target, int total) {
        boolean flag = true;
        while(flag) {
            LOGGER.info(this + " try do the transition of "+total +" to "+target);
            try {
                //wait a random time to avoid the live lock(different with the dead lock)
                if(this.lock.tryLock(ThreadLocalRandom.current().nextInt(1, 50), TimeUnit.NANOSECONDS)) {
                    try {
                        if(target.lock.tryLock(ThreadLocalRandom.current().nextInt(1, 50), TimeUnit.NANOSECONDS)) {
                            try {
                                LOGGER.info(this+ " begin to do the transition of "+total +" to "+target);
                                this.deposit -= total;
                                target.deposit += total;
                                flag = false;
                                //imagine that the operation will take some time.
                                try {
                                    Thread.sleep(100);
                                } catch(Exception e) {

                                }
                            } finally {
                                target.lock.unlock();
                            }
                        }
                    } catch(InterruptedException e) {
                        LOGGER.log(Level.WARNING,"",e);
                    } finally {
                        this.lock.unlock();
                    }
                }
            } catch(InterruptedException e) {
                LOGGER.log(Level.WARNING,"",e);
            }
            LOGGER.info(this + " finished the transition of "+total +" to "+target);
        }//while
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account3 account3 = (Account3) o;

        if (id != account3.id) return false;
        return deposit == account3.deposit;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deposit;
        return result;
    }

    @Override
    public String toString() {
        return "Account3{" +
                "id=" + id +
                ", deposit=" + deposit +
                '}';
    }
}
