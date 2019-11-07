package com.joe.shardingjdbc.util;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceFactory {

    /**
     * 配置数据源映射
     */
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_0", DataSourceUtils.createDataSource("ds_0"));
        result.put("ds_1", DataSourceUtils.createDataSource("ds_1"));
        return result;
    }

    public static DataSource getDataSource() throws SQLException {
        // 配置数据源映射
        Map<String, DataSource> dataSourceMap = createDataSourceMap();
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