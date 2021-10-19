package com.xxd.ilin.shardingsphere.quickdemo.shard;

import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CidShardDatasourceComplexAlg implements ComplexKeysShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {

        Range<Long> cidRange = shardingValue.getColumnNameAndRangeValuesMap().get("cid");
        Collection<Long> userIdCols = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");
        Collection<String> res = new ArrayList<>();
        for (Long userIdCol : userIdCols) {
            BigInteger bigInteger = BigInteger.valueOf(userIdCol);
            BigInteger target = bigInteger.mod(new BigInteger("2"));
            res.add("m" + target);
        }
        System.out.println(res);
        return res;
    }
}
