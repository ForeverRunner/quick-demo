package com.xxd.ilin.shardingsphere.quickdemo.eventbus.config;

import com.xxd.ilin.shardingsphere.quickdemo.eventbus.EventBusTemplate;
import com.xxd.ilin.shardingsphere.quickdemo.eventbus.EventListener;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class EventBusConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${eventbus.thread.corepoolsize:20}")
    private int corePoolSize;

    private static final String EVENT_BUS_THREAD_NAME = "EventBusT";

    @Bean
    public EventBusTemplate eventBusTemplate() {
        EventBusTemplate eventBusTemplate = EventBusTemplate.now();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.join("_", EVENT_BUS_THREAD_NAME, String.valueOf(this.threadIndex.incrementAndGet())));
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
        eventBusTemplate.asyncEventBus(threadPoolExecutor);

        Map<String, EventListener> matchEventListener = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, EventListener.class, true, false);
        if (!matchEventListener.isEmpty()) {
            eventBusTemplate.setEventListeners(Lists.newArrayList(matchEventListener.values()));
        }
        return eventBusTemplate;
    }

}
