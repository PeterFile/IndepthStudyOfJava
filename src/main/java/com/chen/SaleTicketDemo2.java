package com.chen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: SaleTicketDemo2
 * @Package com.chen
 * @Description: TODO
 * @date 2023/7/27 19:27
 */
public class SaleTicketDemo2 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 80; i++) ticket.sale();
        }, "C").start();
    }
}

class Ticket2 {
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {

                System.out.println(Thread.currentThread().getName()
                        + "卖出了第" + (number--) + "张票，剩余：" + number);
            }
        } finally {
            lock.unlock();
        }

    }
}