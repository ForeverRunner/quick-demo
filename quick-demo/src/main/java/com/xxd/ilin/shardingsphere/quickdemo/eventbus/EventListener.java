package com.xxd.ilin.shardingsphere.quickdemo.eventbus;

public interface EventListener {
    void consumerEvent(BaseEvent event);
}
