package com.chen.concurrency.lock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/6 下午11:17
 */
public class Account2 {

    private static final Logger LOGGER = Logger.getLogger(Account2.class.getName());
    private int id;
    private int deposit;

    private Account2() {}

    //create the account with a default deposit (Security : can not change deposit from outside)
    public static Account2 initAccount() {
        Account2 account =  new Account2();
        account.deposit = 160;
        return account;
    }

    public void doTransfer(Account2 target, int p) {

        LOGGER.info(this + " try do the transition of "+p);
        //this thread will wait if can not get the resource successfully!
        AccountResourceManager2.getInstance().tryGetResource(this,target);

        LOGGER.info(this+ " begin to do the transition of "+p);
        /*get the resource successfully
         Don't need the synchronized here , because with the AccountResourcesManager2,
         we have only one thread which can access the thread one time!
        */
        if(this.deposit >=p) {
            this.deposit -= p;
            target.deposit += p;
        } else {
            LOGGER.log(Level.WARNING,"Error Occurred, {} < {}", new Object[]{deposit,p});
        }

        //imagine that the operation will take some time.
        try {
            Thread.sleep(4000);
        } catch(Exception e) {

        } finally {
            //release resources and notify other thread
            AccountResourceManager2.getInstance().freeResource(this,target);
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

        Account2 account = (Account2) o;

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
        return "Account2{" +
                "id=" + id +
                ", deposit=" + deposit +
                '}';
    }
}
