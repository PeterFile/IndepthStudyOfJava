package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: ProducerAndConsumer2
 * @Package com.pc
 * @Description: ReentrantLock 实现生产者与消费者
 * @date 2023/7/27 20:17
 */
public class ProducerAndConsumer2 {
    public static void main(String[] args) {
        Clerk2 clerk2 = new Clerk2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clerk2.increment();
            }
        }, "生产者11").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clerk2.decrement();
            }
        }, "消费者22").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clerk2.increment();
            }
        }, "生产者33").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clerk2.decrement();
            }
        }, "消费者44").start();
    }
}

class Clerk2 {

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while(number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while(number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}