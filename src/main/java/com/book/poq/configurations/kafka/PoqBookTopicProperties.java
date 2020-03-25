package com.book.poq.configurations.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("poq.book-topic")
public class PoqBookTopicProperties {

    private String name;
    private int partitions;
    private int replicas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }
}
