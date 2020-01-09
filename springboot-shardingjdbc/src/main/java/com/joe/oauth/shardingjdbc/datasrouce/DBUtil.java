package com.joe.oauth.shardingjdbc.datasrouce;

import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;
import com.joe.oauth.shardingjdbc.entity.NewDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.function.Function;

/**
 * @ClassName DBUtil
 * @Description 为了方便建立数据库链接 只为查询封装
 * @Author asus
 * @Date Created by asus on 2018/11/1917:22
 * @Version 1.0
 **/
public class DBUtil {
    public static Page getPage(List<NewDataSource> dataSourceList, String sql, PageRequest pageRequest){
        ResultSet rs=null;
        Connection conn=null;
        List<Map<String, Object>> list = new ArrayList<>();
        Integer pageNo=pageRequest.getPageNumber();//第几页
        Integer pageSize=pageRequest.getPageSize();//一页几条
        Integer count=0;
        try {
            Map<String, DataSource> dataSourceMap = createDataSourceMap(dataSourceList);
            DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
            TableRule[] tableRules=new TableRule[dataSourceList.size()];
            for (int i = 0; i <dataSourceList.size() ; i++) {
                TableRule tableRule = TableRule.builder("device_log").actualTables(Arrays.asList("device_log")).dataSourceRule(dataSourceRule).build();
                tableRules[i]=tableRule;
            }
            List<TableRule> newTableRule=Arrays.asList(tableRules);
            //TableRule orderTableRule = TableRule.builder("device_log").actualTables(Arrays.asList("device_log")).dataSourceRule(dataSourceRule).build();
            ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(newTableRule)
                    .databaseShardingStrategy(new DatabaseShardingStrategy("create_time", new ModuloDatabaseShardingAlgorithm()))
                    .tableShardingStrategy(new TableShardingStrategy("create_time", new ModuloTableShardingAlgorithm())).build();
            DataSource dataSource = new ShardingDataSource(shardingRule);
            //2.获得数据库的连接
            conn=dataSource.getConnection();
            //构造一个statement对象来执行sql语句：主要有Statement，PreparedStatement，CallableStatement三种实例来实现
//            stmt=conn.createStatement();
            //获得总个数
            String countSql = "select count(*) totalCount from (" + sql + " ) cout";
            PreparedStatement pstmt = conn.prepareStatement(countSql);
            rs=pstmt.executeQuery();
            count=rs.getInt("totalCount");
            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            sb.append("( ");
            sb.append(sql);
            sb.append(") A limit " + (pageNo-1)*pageSize + "," +  pageSize + "");
            PreparedStatement pstmts = conn.prepareStatement(sb.toString());
            rs=pstmts.executeQuery();
            //PreparedStatement pstmt = conn.prepareStatement(sql);
            //执行sql并返还结束 ；

            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            if(rs !=null){//11.关闭记录集
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn !=null){//13.关闭连接 （记住一定要先关闭前面的11.12.然后在关闭连接，就像关门一样，先关里面的，最后关最外面的）
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MyPage(pageRequest, list, count);
    }
    //将获得的数据库与java的链接返回（返回的类型为Connection）
    public static List<Map<String,Object>> getConnection(List<NewDataSource> dataSourceList, String sql){
        ResultSet rs=null;
        Connection conn=null;
        List<Map<String, Object>> list = new ArrayList<>();
        Integer count=0;
        try {
            Map<String, DataSource> dataSourceMap = createDataSourceMap(dataSourceList);
            DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
            TableRule[] tableRules=new TableRule[dataSourceList.size()];
            for (int i = 0; i <dataSourceList.size() ; i++) {
                TableRule tableRule = TableRule.builder("device_log").actualTables(Arrays.asList("device_log")).dataSourceRule(dataSourceRule).build();
                tableRules[i]=tableRule;
            }
            List<TableRule> newTableRule=Arrays.asList(tableRules);
            //TableRule orderTableRule = TableRule.builder("device_log").actualTables(Arrays.asList("device_log")).dataSourceRule(dataSourceRule).build();
            ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(newTableRule)
                    .databaseShardingStrategy(new DatabaseShardingStrategy("create_time", new ModuloDatabaseShardingAlgorithm()))
                    .tableShardingStrategy(new TableShardingStrategy("create_time", new ModuloTableShardingAlgorithm())).build();
            DataSource dataSource = new ShardingDataSource(shardingRule);
            //2.获得数据库的连接
            conn=dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            if(rs !=null){//11.关闭记录集
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn !=null){//13.关闭连接 （记住一定要先关闭前面的11.12.然后在关闭连接，就像关门一样，先关里面的，最后关最外面的）
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    private static DataSource createDataSource(NewDataSource dataSource) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName("com.mysql.cj.jdbc.Driver");
        String URL="jdbc:mysql://"+dataSource.getDataIp()+":"+dataSource.getDataCode()+"/"+dataSource.getDataDbName()+"?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
        result.setUrl(URL);
        result.setUsername(dataSource.getDataUserName());
        result.setPassword(dataSource.getDataUserPassword());
        return result;
    }
    private static Map<String, DataSource> createDataSourceMap(List<NewDataSource> dataSourceList) {
        Map<String, DataSource> mapList = new HashMap<>();
        for (int i = 0; i < dataSourceList.size(); i++) {
            NewDataSource dataSource=dataSourceList.get(i);
            DataSource dataSource1=createDataSource(dataSource);
            mapList.put(dataSource.getDataDbName(),dataSource1);
        }
        return mapList;
    }
    static class MyPage implements Page {
        private PageRequest pageRequest;
        private List<Map<String, Object>> objectList;

        private int total;

        public MyPage(PageRequest pageRequest, List<Map<String,Object>> objectList, int total) {
            this.pageRequest = pageRequest;
            this.objectList = objectList;
            this.total = total;
        }

        @Override
        public int getNumber() {
            return pageRequest.getPageNumber();
        }

        @Override
        public int getSize() {
            return pageRequest.getPageSize();
        }

        @Override
        public int getTotalPages() {

            if (total % pageRequest.getPageSize() == 0) {
                return total/pageRequest.getPageSize();
            } else {
                return total/pageRequest.getPageSize()+1;
            }
        }

        @Override
        public int getNumberOfElements() {
            return 0;
        }

        @Override
        public long getTotalElements() {
            return this.total;
        }

        @Override
        public Page map(Function function) {
            return null;
        }

        @Override
        public Iterator<Map<String,Object>> iterator() {
            return objectList.iterator();
        }

        @Override
        public List<Map<String,Object>> getContent() {
            return objectList;
        }

        @Override
        public boolean hasContent() {
            return objectList!=null&&objectList.size()>0;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public boolean isFirst() {
            return pageRequest.getPageNumber() == 0;
        }

        @Override
        public boolean isLast() {
            return pageRequest.getPageNumber()==getTotalPages()-1;
        }

        @Override
        public boolean hasNext() {
            return pageRequest.getPageNumber()<getTotalPages()-1;
        }

        @Override
        public boolean hasPrevious() {
            return pageRequest.getPageNumber() >0;
        }

        @Override
        public Pageable nextPageable() {
            return null;
        }

        @Override
        public Pageable previousPageable() {
            return null;
        }
    }

}