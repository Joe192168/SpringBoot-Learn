package com.joe.oauth.shardingjdbc.util;

import com.joe.oauth.shardingjdbc.bean.NewDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataSourceFactory {

    /**
     * 配置数据源映射
     */
    private static Map<String, DataSource> createDataSourceMap(List<NewDataSource> dataSourceList) {
        Map<String, DataSource> result = new HashMap<>();
        for(NewDataSource dataSource:dataSourceList){
            result.put(dataSource.getDataName(), DataSourceUtils.createDataSource(dataSource));
        }
        return result;
    }

    public static DataSource getDataSource(List<NewDataSource> dataSourceList) throws SQLException {
        // 配置数据源映射
        Map<String, DataSource> dataSourceMap = createDataSourceMap(dataSourceList);
        // 配置表规则
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_order");
        tableRuleConfiguration.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
        // 配置默认分库策略
        shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}")
        );
        // 获取数据源对象
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
    }
}