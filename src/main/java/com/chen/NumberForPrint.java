package com.chen;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: NumberForPrint
 * @Package com.chen
 * @Description: 交替打印1-100数
 * @date 2023/7/28 7:20
 */
public class NumberForPrint {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();

        new Thread(()->{for (int i = 0; i < 50; i++) printNumber.odd();}, "A").start();
        new Thread(()->{for (int i = 0; i < 50; i++) printNumber.even();}, "B").start();
    }
}

class PrintNumber {
    private Integer number = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition odd = lock.newCondition();
    private final Condition even = lock.newCondition();

    public void odd() {
        lock.lock();
        try {
            while(number%2 == 0) {
                odd.await();
            }
            System.out.println(Thread.currentThread().getName() + "  交=>" + number);
            number++;
            even.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public void even() {
        lock.lock();
        try {
            while(number%2 != 0) {
                even.await();
            }
            System.out.println(Thread.currentThread().getName() + "  替=>" + number);
            number++;
            odd.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}