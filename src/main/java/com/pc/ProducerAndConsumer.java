package com.pc;

/**
 * @author CWP
 * @version 1.0
 * @Title: ProducerAndConsumer
 * @Package com.pc
 * @Description: synchronized 线程模仿生产者和消费者
 * @date 2023/7/27 19:58
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    clerk.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "生产者11").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    clerk.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "消费者22").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    clerk.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "生产者33").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    clerk.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "消费者44").start();
    }
}

class Clerk {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }
}