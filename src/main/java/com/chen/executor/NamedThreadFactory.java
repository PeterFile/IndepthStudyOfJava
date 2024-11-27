package com.chen.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CWP
 * @version 1.0
 * @Title: NamedThreadFactory
 * @Package com.chen.executor
 * @Description: TODO
 * @date 2023/8/15 16:53
 */
public final class NamedThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNum = new AtomicInteger();
    private final String name;

    public NamedThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, name + "-Thread-" + threadNum.getAndIncrement());
    }
}