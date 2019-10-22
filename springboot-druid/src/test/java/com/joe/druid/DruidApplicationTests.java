package com.joe.druid;

import com.joe.druid.metedata.MetaLoader;
import com.joe.druid.metedata.MetaLoaderImpl;
import com.joe.druid.pojo.TransferDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DruidApplicationTests {

    @Autowired
    private MetaLoader metaLoader;

    @Test
    public void contextLoads()throws Exception {
        TransferDataSource transferDataSource = new TransferDataSource();
        transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        transferDataSource.setUserName("root");
        transferDataSource.setPassWord("root");
        MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
        Map<String,Object> map = metaLoader1.getDataBaseInformations();
        System.out.println("URL:" + map.get("URL"));
        System.out.println("UserName:" + map.get("UserName"));
        System.out.println("isReadOnly:" + map.get("isReadOnly"));
        System.out.println("DatabaseProductName:" + map.get("DatabaseProductName"));
        System.out.println("DatabaseProductVersion:" + map.get("DatabaseProductVersion"));
        System.out.println("DriverName:" + map.get("DriverName"));
        System.out.println("DriverVersion:" + map.get("DriverVersion"));
    }

    @Test
    public void contextLoads2()throws Exception {
        long startTime=System.currentTimeMillis();
        TransferDataSource transferDataSource = new TransferDataSource();
//        transferDataSource.setAddress("jdbc:oracle:thin:@10.0.0.100:1521:edw");
//        transferDataSource.setDriver("oracle.jdbc.driver.OracleDriver");
//        transferDataSource.setUserName("inmon");
//        transferDataSource.setPassWord("jhsz0603");
//        transferDataSource.setSchemaName("INMON");

//        transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
//        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
//        transferDataSource.setUserName("root");
//        transferDataSource.setPassWord("root");

        transferDataSource.setAddress("jdbc:postgresql://10.0.0.166:5432/edw");
        transferDataSource.setDriver("org.postgresql.Driver");
        transferDataSource.setUserName("gpadmin");
        transferDataSource.setPassWord("gpadmin123");
        MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
        List<Map<String,Object>> tableList = metaLoader1.getAllTableList();
        for (Map<String,Object> map:tableList){
            System.out.println("表名称："+map.get("tableName"));
            System.out.println("注释："+map.get("remarks"));
        }
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
    }

    @Test
    public void contextLoads3()throws Exception {
        long startTime=System.currentTimeMillis();
        TransferDataSource transferDataSource = new TransferDataSource();
        transferDataSource.setAddress("jdbc:oracle:thin:@10.0.0.100:1521:edw");
        transferDataSource.setDriver("oracle.jdbc.driver.OracleDriver");
        transferDataSource.setUserName("inmon");
        transferDataSource.setPassWord("jhsz0603");
        transferDataSource.setSchemaName("INMON");

//        transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
//        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
//        transferDataSource.setUserName("root");
//        transferDataSource.setPassWord("root");
//        transferDataSource.setSchema("");

//        transferDataSource.setAddress("jdbc:postgresql://10.0.0.166:5432/edw");
//        transferDataSource.setDriver("org.postgresql.Driver");
//        transferDataSource.setUserName("gpadmin");
//        transferDataSource.setPassWord("gpadmin123");
        MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
        List<Map<String,Object>> viewList = metaLoader1.getAllViewList();
        for (Map<String,Object> map:viewList){
            System.out.println("表名称："+map.get("tableName"));
            System.out.println("注释："+map.get("remarks"));
        }
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
    }

    @Test
    public void contextLoads4()throws Exception {
        TransferDataSource transferDataSource = new TransferDataSource();
//        transferDataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
//        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
//        transferDataSource.setUserName("root");
//        transferDataSource.setPassWord("root");

//        transferDataSource.setAddress("jdbc:oracle:thin:@10.0.0.100:1521:edw");
//        transferDataSource.setDriver("oracle.jdbc.driver.OracleDriver");
//        transferDataSource.setUserName("inmon");
//        transferDataSource.setPassWord("jhsz0603");
//        transferDataSource.setSchemaName("INMON");

        transferDataSource.setAddress("jdbc:postgresql://10.0.0.166:5432/edw");
        transferDataSource.setDriver("org.postgresql.Driver");
        transferDataSource.setUserName("gpadmin");
        transferDataSource.setPassWord("gpadmin123");
        MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
        List<Map<String,Object>> tableColumns = metaLoader1.getTableColumns("t_office_property");
        for (Map<String,Object> col:tableColumns){
            System.out.println("列名称："+col.get("columnName"));
            System.out.println("注释："+col.get("remarks"));
        }
    }


}
