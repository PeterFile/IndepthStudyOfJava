package com.chen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sun.javafx.fxml.expression.Expression.divide;

/**
 * @author CWP
 * @version 1.0
 * @Title: CallerRunsPolicyTest
 * @Package com.chen
 * @Description: TODO
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