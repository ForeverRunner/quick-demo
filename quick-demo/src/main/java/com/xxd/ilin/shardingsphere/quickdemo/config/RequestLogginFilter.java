package com.xxd.ilin.shardingsphere.quickdemo.config;

import com.xxd.ilin.shardingsphere.quickdemo.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RequestLogginFilter extends AbstractRequestLoggingFilter {
    private boolean shouldLog = true;
    public void setShouldLog(boolean shouldLog) {
        this.shouldLog = shouldLog;
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        MDC.put("X-URL", request.getRequestURI());
        MDC.put("X-IP", request.getRemoteAddr());
        return shouldLog;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info("{}, param:{}", message, JsonUtil.getInstance().objectToStr(request.getParameterMap()));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        log.info(message);
    }
}
