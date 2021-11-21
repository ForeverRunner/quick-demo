package com.xxd.seckill.support.launcher.filter;

public class TraceIdUtil {
    private static final ThreadLocal<String> TRACE_ID=new InheritableThreadLocal<String>();

    public TraceIdUtil() {
    }
    public static String getTraceId(){
        return TRACE_ID.get();
    }
    public static void setTraceId(String traceId){
        TRACE_ID.set(traceId);
    }
    public static void removeTraceId(){
        TRACE_ID.remove();
    }
}
