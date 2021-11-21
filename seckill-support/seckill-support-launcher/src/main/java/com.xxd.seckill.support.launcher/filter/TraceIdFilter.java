package com.xxd.seckill.support.launcher.filter;

import brave.Span;
import brave.Tracer;
import com.xxd.seckill.support.launcher.context.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;


@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
@Slf4j
public class TraceIdFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        String traceId = "";
        if (rpcContext.isConsumerSide()) {
            if (StringUtils.isBlank(TraceIdUtil.getTraceId())) {
                // 根调用，生成TraceId
                traceId = TraceIdGenerator.createTraceId();
            } else {
                // 后续调用，从Rpc上下文取出并设置到线程上下文
                traceId = TraceIdUtil.getTraceId();
            }
            TraceIdUtil.setTraceId(traceId);
            RpcContext.getContext().setAttachment(TraceIdConst.TRACE_ID, TraceIdUtil.getTraceId());
        }

        if (rpcContext.isProviderSide()) {
            // 服务提供方，从Rpc上下文获取traceId
            traceId = RpcContext.getContext().getAttachment(TraceIdConst.TRACE_ID);
            Tracer tracer = SpringContextUtil.getBean(Tracer.class);
            if (tracer!=null){
                log.info("{}",tracer);
                Span span = tracer.currentSpan();

                log.info("hahha:{}",span);
            }
            TraceIdUtil.setTraceId(traceId);
            MDC.put("X-B3-TraceId",traceId);
            MDC.put("X-B3-SpanId",traceId);

        }
        log.info("traceId:{}", traceId);
        Result result = invoker.invoke(invocation);
        return result;
    }
}
