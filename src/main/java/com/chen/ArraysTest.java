package com.chen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author CWP
 * @version 1.0
 * @Title: ArraysTest
 * @Package com.chen
 * @Description: 多线程交替打印 0 - 100
 * @date 2023/8/4 8:13
 */
//public class ArraysTest {
//    private static final ReentrantLock LOCK = new ReentrantLock();
//    private static final Condition CONDITION = LOCK.newCondition();
//    private static final int THREAD_COUNT = 3;
//    private static volatile int start = 0;
//    private static final int END = 100;
//
//    private static class Print implements Runnable {
//        private final int index;
//
//        private Print(int index) {
//            this.index = index;
//        }
//
//        @Override
//        public void run() {
//            while (start < END) {
//                LOCK.lock();
//                try {
//                    while (start % THREAD_COUNT != index) {
//                        CONDITION.await();
//                    }
//
//                    if (start <= END) {
//                        System.out.println("Thread: " + index + "打印结果: " + start);
//                    }
//
//                    start++;
//                    CONDITION.signalAll();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    LOCK.unlock();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//
//        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
//
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            pool.execute(new Print(i));
//        }
//
//        pool.shutdown();
//
//    }
//}

public class ArraysTest {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final int THREAD_COUNT = 3;
    private static volatile int START = 0;
    private static final int END = 100;

    private static class Print implements Runnable {
        private final int index;
        private final List<Condition> conditions;

        private Print(int index, List<Condition> conditions) {
            this.index = index;
            this.conditions = conditions;
        }

        private void signalNext() {
            int nextIndex = (index + 1) % THREAD_COUNT;
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (START < END) {
                LOCK.lock();
                try {
                    while (START % THREAD_COUNT != index) {
                        conditions.get(index).await();
                    }

                    if (START <= END) {
                        System.out.println("Thread: " + index + "打印结果: " + START);
                    }

                    START++;
                    signalNext();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            conditions.add(LOCK.newCondition());
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(new Print(i, conditions));
        }

        executor.shutdown();

    }
}