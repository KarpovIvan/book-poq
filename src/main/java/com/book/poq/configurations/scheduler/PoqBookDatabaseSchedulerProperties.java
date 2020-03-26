package com.book.poq.configurations.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("poq.book.database.scheduler")
public class PoqBookDatabaseSchedulerProperties {
    private int threads = 5;
    private int queue = 10000;
    private String name = "database";

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
