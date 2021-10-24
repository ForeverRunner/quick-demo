package com.xxd.ilin.shardingsphere.quickdemo.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.xxd.ilin.shardingsphere.quickdemo.eventbus.BaseEvent;
import com.xxd.ilin.shardingsphere.quickdemo.eventbus.EventListener;
import com.xxd.ilin.shardingsphere.quickdemo.eventbus.event.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogEventListener implements EventListener {

    @Subscribe
    @Override
    public void consumerEvent(BaseEvent event) {
        log.info("LogEventListener: {}", event);
    }
}
