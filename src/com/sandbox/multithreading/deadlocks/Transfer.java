package com.sandbox.multithreading.deadlocks;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/19/14
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transfer implements Callable<Boolean> {

    private Account accountFrom;
    private Account accountTo;
    private int amount;

    public Transfer(Account accountFrom, Account accountTo, int amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        boolean result = false;
        if (accountFrom.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        System.out.println("Start moving funds.");
        if (accountFrom.getLock().tryLock()) {
            try {
                System.out.println("Lock " + accountFrom.getBillName() + " account.");
                Thread.sleep(3000);
                if (accountTo.getLock().tryLock(50, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("Lock " + accountFrom.getBillName() + " account.");
                        accountFrom.withdraw(amount);
                        accountTo.deposit(amount);
                        Thread.sleep(5000);
                        result = true;
                    } finally {
                        accountTo.getLock().unlock();
                    }
                }
            } finally {
                accountFrom.getLock().unlock();
            }
        }

        System.out.println("Funds has been transferred");
        return result;
    }
}
