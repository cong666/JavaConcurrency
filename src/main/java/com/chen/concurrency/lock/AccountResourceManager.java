package com.chen.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by: ccong
 * Date: 19/5/6 下午11:18
 */
public class AccountResourceManager {

    private static final Logger LOGGER = Logger.getLogger(AccountResourceManager.class.getName());

    /*Use enum to implement the singleton */
    private enum ResourceManagerInstance {
        INSTANCE;

        private AccountResourceManager resourceManager;

        ResourceManagerInstance() {
            resourceManager = new AccountResourceManager();
        }

        public AccountResourceManager getInstance() {
            return resourceManager;
        }
    }

    private final List<Account> accounts = new ArrayList<>();

    private AccountResourceManager() {
    }

    public static AccountResourceManager getInstance() {
        return ResourceManagerInstance.INSTANCE.getInstance();
    }

    public boolean tryGetResource(Account acc1, Account acc2) {
        LOGGER.info(acc1 + " try to get resource for the transition of "+acc2);
        synchronized (this) {
            if (accounts.contains(acc1) || accounts.contains(acc2)) {
                LOGGER.info(acc1+ " wait for the resource for the transition of "+acc2);
                return false;
            } else {
                accounts.add(acc1);
                accounts.add(acc2);
                LOGGER.info(acc1 + " got the resource for the transition of "+acc2 + " successfully");
            }
        }
        return true;
    }

    public void freeResource(Account acc1, Account acc2) {
        synchronized (this) {
            accounts.remove(acc1);
            accounts.remove(acc2);
            LOGGER.info(acc1 + " finished the transition to " + acc2);
        }
    }
}
