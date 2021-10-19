package com.xxd.ilin.shardingsphere.quickdemo.eventbus;


import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

public class BaseEvent {
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT+8");
    private String eventId;
    private LocalDateTime eventTime;
    private Object target;

    public BaseEvent() {
        this(UUID.randomUUID().toString(), LocalDateTime.now(DEFAULT_TIME_ZONE.toZoneId()), null);
    }

    public BaseEvent(String eventId, LocalDateTime eventTime, Object target) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.target = target;
    }

    public <R extends BaseEvent> R eventId(String eventId) {
        this.eventId = eventId;
        return (R) this;
    }

    public <R extends BaseEvent> R eventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
        return (R) this;
    }

    public <R extends BaseEvent> R eventTime(Object target) {
        this.target = target;
        return (R) this;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
