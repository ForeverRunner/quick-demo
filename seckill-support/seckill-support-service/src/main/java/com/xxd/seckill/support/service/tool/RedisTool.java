package com.xxd.seckill.support.service.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RedisTool {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * lua逻辑：首先判断活动库存是否存在，以及库存余量是否够本次购买数量，如果不够，则返回0，如果够则完成扣减并返回1
     * 两个入参，KEYS[1] : 活动库存的key
     * KEYS[2] : 活动库存的扣减数量
     */
    private String STORE_DEDUCTION_SCRIPT_LUA =
            "local c_s = redis.call('get', KEYS[1])\n" +
                    "if not c_s or tonumber(c_s) < tonumber(ARGV[1]) then\n" +
                    "return 0\n" +
                    "end\n" +
                    "redis.call('decrby',KEYS[1], ARGV[2])\n" +
                    "return 1";


    //    /**
//     * 在系统启动时，将脚本预加载到Redis中，并返回一个加密的字符串，下次只要传该加密窜，即可执行对应脚本，减少了Redis的预编译
//     */
//    private String STORE_DEDUCTION_SCRIPT_SHA1 = "";
//
//    @PostConstruct
//    public void init() {
//        STORE_DEDUCTION_SCRIPT_SHA1 = redisTemplate.execute(new RedisCallback<String>() {
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                DefaultStringRedisConnection newConnection = new DefaultStringRedisConnection(connection);
//                return newConnection.scriptLoad(STORE_DEDUCTION_SCRIPT_LUA);
//            }
//        });
//    }
    public long eval(String key, String buyNum) {
        return redisTemplate.execute(new DefaultRedisScript<Long>(STORE_DEDUCTION_SCRIPT_LUA, Long.class),
                Collections.singletonList(key), buyNum);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value, long expireSec) {
        redisTemplate.opsForValue().set(key, value, expireSec, TimeUnit.SECONDS);
    }

}
