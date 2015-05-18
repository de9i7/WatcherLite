package com.sandbox.multithreading.deadlocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/19/14
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountsTransfer {

    public static void main(String[] args) throws InsufficientFundsException, InterruptedException {
        final Account a = new Account(1000, "Mark");
        final Account b = new Account(1400, "Spenser");

        // How to change fixed thread pool size
        ExecutorService service = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        executor.setCorePoolSize(8);

        Thread accA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a, b, 500);
                } catch (InsufficientFundsException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        accA.start();

        Thread accB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(b, a, 500);
                } catch (InsufficientFundsException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        accB.start();

    }

    private static void transferLock(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {
        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        System.out.println("Start moving funds.");
        if (acc1.getLock().tryLock()) {
            try {
                System.out.println("Lock " + acc1.getBillName() + " account.");
                Thread.sleep(3000);
                if (acc2.getLock().tryLock(50, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("Lock " + acc1.getBillName() + " account.");
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                    } finally {
                        acc2.getLock().unlock();
                    }
                }
            } finally {
                acc1.getLock().unlock();
            }
        }

        System.out.println("Funds has been transferred");
    }

    private static void transfer(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {
        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        System.out.println("Start moving funds.");
        synchronized (acc1) {
            System.out.println("Lock first account.");
            Thread.sleep(1000);
            synchronized (acc2) {
                System.out.println("Lock second account.");
                acc1.withdraw(amount);
                acc2.deposit(amount);
            }
        }

        System.out.println("Funds has been transferred");
    }
}


