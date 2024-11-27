package com.chen;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: OddAndEvenNumber
 * @Package com.chen
 * @Description: TODO
 * @date 2023/8/27 10:04
 */
public class OddAndEvenNumber {
    static volatile int number = 0;
    static Lock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    static class Task1 implements Runnable {

        @Override
        public void run() {
            while (number < 100) {
                lock.lock();
                try {
                    while (number % 2 == 0) {
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> " + number++);
                    condition2.signal();
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
                    while (number % 2 != 0) {
                        condition2.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " -> " + number++);
                    condition1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(task1);
        pool.execute(task2);

        pool.shutdown();
    }
}