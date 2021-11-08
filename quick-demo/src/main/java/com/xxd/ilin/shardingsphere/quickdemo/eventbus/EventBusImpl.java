package com.xxd.ilin.shardingsphere.quickdemo.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.util.Assert;

import javax.xml.ws.WebServiceException;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class EventBusImpl implements BusOperation, Closeable {
    private List<EventListener> eventListeners = new ArrayList<>();
    private EventBus eventBus;
    private ExecutorService executorService;


    public static EventBusImpl now() {
        EventBusImpl eventBusTemplate = new EventBusImpl();
        eventBusTemplate.syncEventBus(new EventBus());
        return eventBusTemplate;
    }

    private void syncEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public EventBus asyncEventBus(ExecutorService executorService) {
        this.eventBus = new AsyncEventBus(executorService);
        this.executorService = executorService;
        return this.eventBus;
    }

    @Override
    public void register(EventListener eventListener) {
        eventBus.register(eventListener);
        if (!eventListeners.contains(eventListener)) {
            eventListeners.add(eventListener);
        }
    }

    @Override
    public BusOperation pushEvent(BaseEvent event) {
        eventBus.post(event);
        return this;
    }

    public void setEventListeners(List<EventListener> eventListeners) {
        Assert.notEmpty(eventListeners, "监听器列表不为空");
        Assert.noNullElements(eventListeners, "监听器列表中元素不能为空");
        if (this.eventListeners != eventListeners) {
            this.eventListeners.clear();
            this.eventListeners.addAll(eventListeners);
        }
        eventListeners.forEach(eventListener -> {
            this.register(eventListener);
        });
    }

    @Override
    public void close() throws WebServiceException {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.isShutdown();
        }
    }
}
