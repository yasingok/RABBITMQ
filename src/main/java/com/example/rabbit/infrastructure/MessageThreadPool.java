package com.example.rabbit.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageThreadPool {
    private final ThreadPoolExecutor workerThread;
    Logger logger = LoggerFactory.getLogger(MessageThreadPool.class);

    private static class LAZY_HOLDER{
        private static final MessageThreadPool INSTANCE = new MessageThreadPool();
    }

    public static MessageThreadPool getInstance(){
        return LAZY_HOLDER.INSTANCE;
    }

    private MessageThreadPool(){
        logger.debug("message handling pool initialize.");

        this.workerThread = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new MessageThreadFactory());
    }

    public void handleMessage(final Runnable task) {
        try {
            this.workerThread.execute(task);
        } catch (Exception exception) {
            logger.error("Task is rejected. Worker pool is full. Pool size:{}", this.workerThread.getPoolSize());
        }
    }

    static class MessageThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MessageThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "rabbitmqWorker-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread rabbitmqTaskWorker = new Thread(group, runnable,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (rabbitmqTaskWorker.isDaemon())
                rabbitmqTaskWorker.setDaemon(false);
            if (rabbitmqTaskWorker.getPriority() != Thread.NORM_PRIORITY)
                rabbitmqTaskWorker.setPriority(Thread.NORM_PRIORITY);
            return rabbitmqTaskWorker;
        }
    }
}
