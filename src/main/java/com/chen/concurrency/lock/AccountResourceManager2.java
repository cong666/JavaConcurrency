package com.chen.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/6 下午11:18
 */
public class AccountResourceManager2 {

    private static final Logger LOGGER = Logger.getLogger(AccountResourceManager2.class.getName());

    /*Use enum to implement the singleton */
    private enum ResourceManagerInstance {
        INSTANCE;

        private AccountResourceManager2 resourceManager;

        ResourceManagerInstance() {
            resourceManager = new AccountResourceManager2();
        }

        public AccountResourceManager2 getInstance() {
            return resourceManager;
        }
    }

    private final List<Account2> accounts = new ArrayList<>();

    private AccountResourceManager2() {
    }

    public static AccountResourceManager2 getInstance() {
        return ResourceManagerInstance.INSTANCE.getInstance();
    }

    public boolean tryGetResource(Account2 acc1, Account2 acc2) {
        synchronized (this) {
            //need use while here, once the thread is notified , the thread can continue to check the while condition
            while (accounts.contains(acc1) || accounts.contains(acc2)) {
                try {
                    LOGGER.info(this+" wait for the transition");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info(this+" can do the transition");
            accounts.add(acc1);
            accounts.add(acc2);
        }
        return true;
    }

    public void freeResource(Account2 acc1, Account2 acc2) {
        synchronized (this) {
            accounts.remove(acc1);
            accounts.remove(acc2);
            LOGGER.info(acc1 + " finished the transition to " + acc2);
            notifyAll();
        }
    }
}
