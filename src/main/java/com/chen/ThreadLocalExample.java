package com.chen;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author CWP
 * @version 1.0
 * @Title: ThreadLocalExample
 * @Package com.chen
 * @Description: TODO
 * @date 2023/8/15 11:09
 */
public class ThreadLocalExample implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> Formatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample threadLocalExample = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(threadLocalExample, String.valueOf(i));
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("ThreadName: " + Thread.currentThread().getName() +
                " default Formatter: " + Formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Formatter.set(new SimpleDateFormat());
        System.out.println("ThreadName: " + Thread.currentThread().getName() +
                " formatter: " + Formatter.get().toPattern());
    }
}