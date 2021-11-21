package com.xxd.seckillgateway.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TraceIdGenerator {
    public static String createTraceId() {
        String traceId = getTraceId();
        TraceIdUtil.setTraceId(traceId);
        return traceId;
    }

    private static String getTraceId() {
        String result = "";
        String ip = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            return result;
        }
        String[] ipAddressInArray = ip.split("\\.");
        for (String ipBit : ipAddressInArray) {
            int id = Integer.parseInt(ipBit);
            result = result + String.format("%02x", id);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        result = result + simpleDateFormat.format(new Date()) + UUID.randomUUID().toString().substring(0, 7);
        return result;
    }
}
