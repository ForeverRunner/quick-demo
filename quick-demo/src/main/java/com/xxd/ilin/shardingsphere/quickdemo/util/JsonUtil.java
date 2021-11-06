package com.xxd.ilin.shardingsphere.quickdemo.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class JsonUtil {
    private static final JsonUtil INSTANCE = new JsonUtil();
    private ObjectMapper objectMapper;


    public JsonUtil() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public static JsonUtil getInstance() {
        return INSTANCE;
    }

    public String objectToStr(Object value) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error(String.format("objectToStr class:%s", value.getClass().getName()), e);
        }

        return result;
    }

    public <T> T strToObject(String json, Class<T> valueType) {
        T value = null;
        try {
            value = objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("string convert to Object failed json:{},err:{}", json, e);
        }
        return value;
    }

}
