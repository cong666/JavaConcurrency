package com.chen.concurrency.lock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/6 下午11:17
 */
public class Account {

    private static final Logger LOGGER = Logger.getLogger(Account.class.getName());
    private int id;
    private int deposit;

    private Account() {}

    //create the account with a default deposit (Security : can not change deposit from outside)
    public static Account initAccount() {
        Account account =  new Account();
        account.deposit = 160;
        return account;
    }

    public void doTransfer(Account target, int p) {

        LOGGER.info(this + " try do the transition of "+p);
        //try to get the resource in the while loop
        //may add a timeout here to avoid long waiting time
        while(!AccountResourceManager.getInstance().tryGetResource(this,target)) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info(this+ " begin to do the transition of "+p);
        //get the resource successfully
        try {
            synchronized (this) {
                if(this.deposit < p) {
                    LOGGER.log(Level.WARNING,"Error Occurred, {} < {}", new Object[]{deposit,p});
                    return;
                }
                this.deposit -= p;
                synchronized (target) {
                    target.deposit += p;
                }
            }
            //image that the operation may take some time
            Thread.sleep(4000);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            AccountResourceManager.getInstance().freeResource(this,target);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        return deposit == account.deposit;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deposit;
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "deposit=" + deposit +
                '}';
    }
}
