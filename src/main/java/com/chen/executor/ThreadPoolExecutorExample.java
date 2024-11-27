package com.chen.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CWP
 * @version 1.0
 * @Title: ThreadPoolExecutorExample
 * @Package com.chen
 * @Description: TODO
 * @date 2023/8/15 16:40
 */
public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    /**
     * 自定义线程池
     *
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(2,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new NamedThreadFactory("cwp多线程"));
    }

    /**
     * 先提交任务，然后再改变线程池参数
     *
     * @throws InterruptedException
     */
    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor, "改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor, "改变之后");
        Thread.currentThread().join();
    }

    /**
     * 打印线程池状态
     *
     * @param executor
     * @param name
     */
    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String strDate = sdf.format(date);
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(strDate + ": " + Thread.currentThread().getName() + "-" + name + "-:" +
                "核心线程数：" + executor.getCorePoolSize() +
                " 活动线程数：" + executor.getActiveCount() +
                " 最大线程数：" + executor.getMaximumPoolSize() +
                " | 线程池活跃度：" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                " 任务完成数：" + executor.getCompletedTaskCount() +
                " | 队列大小：" + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数：" + queue.size() +
                " 队列剩余大小：" + queue.remainingCapacity() +
                " 队列使用度：" + divide(queue.size(), queue.size() + queue.remainingCapacity()));
    }

    /**
     * 保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%",
                Double.parseDouble(String.valueOf(num1)) / Double.parseDouble(String.valueOf(num2)) * 100);
    }
}