package com.joe.oauth.druid.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.joe.oauth.druid.domain.TransferDataSource;
import java.sql.Connection;
import java.util.Properties;

public class DruidConfigConnect {

    public Connection GetDbConnect(TransferDataSource transferDataSource) throws Exception {
        DruidDataSource dataSource  =new DruidDataSource();
        //设置连接参数 (***自己定义传递的参数***)
        dataSource.setUrl(transferDataSource.getAddress());
        dataSource.setDriverClassName(transferDataSource.getDriver());
        dataSource.setUsername(transferDataSource.getUserName());
        dataSource.setPassword(transferDataSource.getPassWord());
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        //连接泄漏监测
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(20000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(20000);
        //防止过期
        //dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(true);
        //解决oracle读取不到REMARKS表注解设置
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setConnectProperties(properties);
        // 返回连接
        return dataSource.getConnection();
    }
}
