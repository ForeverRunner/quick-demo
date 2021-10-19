package com.xxd.ilin.shardingsphere.quickdemo.shard;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

@Slf4j
public class CidShardDatasourcePreciseAlg implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long value = preciseShardingValue.getValue();
        Long shard = value >>2;
        Long key = shard &3;
        log.info("value: {},shard: {},key: {},", value, shard, key);
        String db = "m" + key;
        if (collection.contains(db)) {
            log.info("匹配路由" + db);
            return db;
        } else {
            log.error("暂未路由到数据源");
        }
        return null;
    }
}
