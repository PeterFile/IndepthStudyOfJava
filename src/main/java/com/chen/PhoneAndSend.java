package com.chen;

import java.util.concurrent.TimeUnit;

/**
 * @author CWP
 * @version 1.0
 * @Title: PhoneAndSend
 * @Package com.chen
 * @Description: TODO
 * @date 2023/8/4 7:47
 */
public class PhoneAndSend {
    public static void main(String[] args) {
        Test test = new Test();
        Test test2 = new Test();
        new Thread(()->test2.phone(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->test.send(), "B").start();
    }
}

class Test {
    public synchronized void phone() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("打电话");
    }

    public synchronized void send() {
        System.out.println("发短信");
    }
}