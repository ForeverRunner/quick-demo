package com.xxd.ilin.shardingsphere.quickdemo.eventbus;

import com.xxd.ilin.shardingsphere.quickdemo.eventbus.event.LogEvent;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class EventBusTest {

    @Autowired
    private EventBusImpl eventBusTemplate;

    @Test
    public void testPush() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            LogEvent logEvent = LogEvent.builder().traceId(UUID.randomUUID().toString())
                    .msg("test\t" + i).build();
            eventBusTemplate.pushEvent(logEvent);
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
