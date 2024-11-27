package com.chen.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: ReplacePrint
 * @Package com.chen.executor
 * @Description: TODO
 * @date 2023/8/21 12:38
 */
public class ReplacePrint {

    static volatile int number = 0;
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Runnable task1 = new Task1();
        Runnable task2 = new Task2();

        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(task1);
        pool.execute(task2);
    }

    static class Task1 implements Runnable {

        @Override
        public void run() {
            while (number < 100) {
                lock.lock();
                try {
                    while (number % 2 == 0) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            while (number < 100) {
                lock.lock();
                try {
                    while (number % 2 == 1) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}

