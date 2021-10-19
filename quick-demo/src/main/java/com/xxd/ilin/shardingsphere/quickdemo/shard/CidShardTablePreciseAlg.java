package com.xxd.ilin.shardingsphere.quickdemo.shard;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

@Slf4j
public class CidShardTablePreciseAlg implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long value = preciseShardingValue.getValue();
        Long shard = value & 1;
        Long key = shard + 1;
        String logicTable = preciseShardingValue.getLogicTableName() + "_" + key;
        if (collection.contains(logicTable)) {
            return logicTable;
        } else {
            log.error("暂未路由到数据源");
        }
        return null;
    }
}
