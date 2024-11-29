package com.chen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

import static com.sun.javafx.fxml.expression.Expression.divide;

/**
 * @author CWP
 * @version 1.0
 * @Title: CallerRunsPolicyTest
 * @Package com.chen
 * @Description: CallerRunsPolicy 拒绝策略测试
 * @date 2023/9/6 11:16
 */
public class CallerRunsPolicyTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                4,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ScheduledExecutorService monitor = Executors.newScheduledThreadPool(1);
        monitor.scheduleAtFixedRate(() -> {
            System.out.println("======== ThreadPool Status ========");
            System.out.println("CorePoolSize: " + pool.getCorePoolSize());
            System.out.println("ActiveCount: " + pool.getActiveCount());
            System.out.println("PoolSize: " + pool.getPoolSize());
            System.out.println("LargestPoolSize: " + pool.getLargestPoolSize());
            System.out.println("TaskCount: " + pool.getTaskCount());
            System.out.println("CompletedTaskCount: " + pool.getCompletedTaskCount());
            System.out.println("QueueSize: " + pool.getQueue().size());
            System.out.println("====================================");
        }, 0, 5, TimeUnit.SECONDS);


        for (int i = 0; i <= 10; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " is begin executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Task " + taskId + " execution completed");
            });
        }

        pool.shutdown();
    }

}