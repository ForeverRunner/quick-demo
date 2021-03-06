package com.xxd.ilin.shardingsphere.quickdemo.eventbus;

public interface BusOperation {
    /**
     * 注册监听
     * @param eventListener
     */
    void register(EventListener eventListener);

    /**
     * 发布事件
     * @param event
     * @return
     */
    BusOperation pushEvent(BaseEvent event);
}
