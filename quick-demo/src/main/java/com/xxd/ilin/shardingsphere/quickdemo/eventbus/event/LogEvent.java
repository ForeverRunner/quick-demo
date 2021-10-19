package com.xxd.ilin.shardingsphere.quickdemo.eventbus.event;

import com.xxd.ilin.shardingsphere.quickdemo.eventbus.BaseEvent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogEvent extends BaseEvent {
    private String msg;
    private String traceId;
}
