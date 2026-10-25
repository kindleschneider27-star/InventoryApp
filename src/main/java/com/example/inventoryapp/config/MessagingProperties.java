package com.example.inventoryapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "item.messaging")
public class MessagingProperties {
    private String itemStatusTopic;
    private String loggingGroup;
    private String emailGroup;
    private int partitions = 3;
    private int replicas = 1;

    public MessagingProperties() {}

    public String getItemStatusTopic() {
        return itemStatusTopic;
    }

    public void setItemStatusTopic(String itemStatusTopic) {
        this.itemStatusTopic = itemStatusTopic;
    }

    public String getLoggingGroup() {
        return loggingGroup;
    }

    public void setLoggingGroup(String loggingGroup) {
        this.loggingGroup = loggingGroup;
    }

    public String getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup;
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
