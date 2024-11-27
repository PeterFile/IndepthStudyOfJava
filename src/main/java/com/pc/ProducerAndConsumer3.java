package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: ProducerAndConsumer3
 * @Package com.pc
 * @Description: TODO
 * @date 2023/7/27 20:35
 */
public class ProducerAndConsumer3 {
    public static void main(String[] args) {
        PrintData printData = new PrintData();

        new Thread(()->{for (int i = 0; i < 10; i++) printData.printA();}).start();
        new Thread(()->{for (int i = 0; i < 10; i++) printData.printB();}).start();
        new Thread(()->{for (int i = 0; i < 10; i++) printData.printC();}).start();
    }
}

class PrintData {
    private Lock lock = new ReentrantLock();
    private Condition A = lock.newCondition();
    private Condition B = lock.newCondition();
    private Condition C = lock.newCondition();

    private Integer number = 1;
    public void printA() {
        lock.lock();
        try{
            while(number != 1) {
                A.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAAAAAAAA");
            number=2;
            B.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try{
            while(number != 2) {
                B.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBBBBBBBB");
            number=3;
            C.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try{
            while(number != 3) {
                C.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCCCCCCC");
            number=1;
            A.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}