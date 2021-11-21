package com.xxd.seckillgateway.filter;

import brave.Tracer;
import com.xxd.seckillgateway.context.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;


@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
@Slf4j
public class TraceIdFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        Tracer tracer = SpringContextUtil.getBean(Tracer.class);
        String traceId = "";
        if (tracer != null) {
            traceId = tracer.currentSpan().context().traceIdString();
        }
        if (rpcContext.isConsumerSide()) {
//            if (StringUtils.isBlank(TraceIdUtil.getTraceId())) {
//                // 根调用，生成TraceId
//                traceId = TraceIdGenerator.createTraceId();
//            } else {
//                // 后续调用，从Rpc上下文取出并设置到线程上下文
//                traceId = TraceIdUtil.getTraceId();
//            }
//            TraceIdUtil.setTraceId(traceId);
//            RpcContext.getContext().setAttachment(TraceIdConst.TRACE_ID, TraceIdUtil.getTraceId());
            RpcContext.getContext().setAttachment(TraceIdConst.TRACE_ID, traceId);
        }
        if (rpcContext.isProviderSide()) {
            // 服务提供方，从Rpc上下文获取traceId
            traceId = RpcContext.getContext().getAttachment(TraceIdConst.TRACE_ID);
//            TraceIdUtil.setTraceId(traceId);
        }

        log.info("traceId:{}", traceId);
        Result result = invoker.invoke(invocation);
        return result;
    }
}
