package com.chen;

/**
 * @author CWP
 * @version 1.0
 * @Title: SaleTicketDemo1
 * @Package com.chen
 * @Description: TODO
 * @date 2023/7/27 18:46
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{for (int i = 0; i < 40; i++) {ticket.sale();}},"A").start();
        new Thread(()->{for (int i = 0; i < 40; i++) {ticket.sale();}},"B").start();
        new Thread(()->{for (int i = 0; i < 80; i++) {ticket.sale();}},"C").start();
    }
}

class Ticket{
    private int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName()
                    + "卖出了第" + (number--) + "张票，剩余：" + number);
        }
    }
}
