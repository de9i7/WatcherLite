package com.sandbox.multithreading.deadlocks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/19/14
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account {

    private int balance;
    private String billName;
    private Lock m_lock;
    private AtomicInteger failCounter;

    public Account(int balance, String name) {
        this.balance = balance;
        m_lock = new ReentrantLock();
        billName = name;
    }

    public String getBillName() {
        return billName;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Lock getLock() {
        return m_lock;
    }

    public void incFailCounter(){
//        failCounter++;
    }
}
