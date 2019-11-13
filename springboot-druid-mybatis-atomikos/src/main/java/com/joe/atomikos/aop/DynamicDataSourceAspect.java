package com.joe.atomikos.aop;

import com.joe.atomikos.config.DataSourceContextHolder;
import com.joe.atomikos.constant.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : joe
 * @description : 动态方式去切换数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(* com.joe.atomikos.mapper.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before(value = "dataSourcePointCut()")
    public void beforeSwitchDS(JoinPoint point) {

        Object[] args = point.getArgs();

        if (args == null || args.length < 1 || !Data.DATASOURCE2.equals(args[0])) {
            DataSourceContextHolder.setDataSourceKey(Data.DATASOURCE1);
        } else {
            DataSourceContextHolder.setDataSourceKey(Data.DATASOURCE2);
        }
    }

    @After(value = "dataSourcePointCut()")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDataSourceKey();
    }
}