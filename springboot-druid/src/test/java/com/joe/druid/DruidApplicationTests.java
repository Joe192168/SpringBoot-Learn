package com.joe.druid;

import com.joe.druid.metedata.IMetaLoader;
import com.joe.druid.metedata.MetaLoaderImpl;
import com.joe.druid.domain.TransferDataSource;
import com.joe.druid.utils.PageUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DruidApplicationTests {

    @Autowired
    private IMetaLoader metaLoader;

    //测试连接数据
    @Test
    public void contextLoads() {
        TransferDataSource transferDataSource = new TransferDataSource();
        transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        transferDataSource.setUserName("root");
        transferDataSource.setPassWord("root");
        try {
            MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
            Map<String,Object> map = metaLoader1.getDataBaseInformations();
            System.out.println("URL:" + map.get("URL"));
            System.out.println("UserName:" + map.get("UserName"));
            System.out.println("isReadOnly:" + map.get("isReadOnly"));
            System.out.println("DatabaseProductName:" + map.get("DatabaseProductName"));
            System.out.println("DatabaseProductVersion:" + map.get("DatabaseProductVersion"));
            System.out.println("DriverName:" + map.get("DriverName"));
            System.out.println("DriverVersion:" + map.get("DriverVersion"));

            if ((Boolean) map.get("falg")){
                System.out.println("连接成功！");
            }else{
                System.out.println("连接失败异常信息:"+map.get("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库表列信息
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

    //获取数据库视图信息
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

    //根据表名称获取列信息
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
            System.out.println("tableName："+col.get("tableName"));
            System.out.println("columnName："+col.get("columnName"));
        }
    }

    @Test
    public void contextLoads5()throws Exception {
        TransferDataSource transferDataSource = new TransferDataSource();
        transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        transferDataSource.setUserName("root");
        transferDataSource.setPassWord("root");

//        transferDataSource.setAddress("jdbc:oracle:thin:@10.0.0.100:1521:edw");
//        transferDataSource.setDriver("oracle.jdbc.driver.OracleDriver");
//        transferDataSource.setUserName("inmon");
//        transferDataSource.setPassWord("jhsz0603");
//        transferDataSource.setSchemaName("INMON");

//        transferDataSource.setAddress("jdbc:postgresql://10.0.0.166:5432/edw");
//        transferDataSource.setDriver("org.postgresql.Driver");
//        transferDataSource.setUserName("gpadmin");
//        transferDataSource.setPassWord("gpadmin123");
        MetaLoaderImpl metaLoader1 = new MetaLoaderImpl(transferDataSource);
        String[] fields = {"username"};
        String[] data = {"1442"};
        String[] conditions = {"id"};
        String[] conditions_param = {"14"};
        boolean falg = metaLoader1.update("t_users",fields,data,conditions,conditions_param);
        System.out.println("更新结果："+falg);
    }

    //测试分页
    @Test
    public void contextLoads6()throws Exception {
        TransferDataSource transferDataSource = new TransferDataSource();
        /*transferDataSource.setAddress("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        transferDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        transferDataSource.setUserName("root");
        transferDataSource.setPassWord("root");*/

        /*transferDataSource.setAddress("jdbc:oracle:thin:@10.0.0.100:1521:edw");
        transferDataSource.setDriver("oracle.jdbc.driver.OracleDriver");
        transferDataSource.setUserName("inmon");
        transferDataSource.setPassWord("jhsz0603");
        transferDataSource.setSchemaName("INMON");*/

        transferDataSource.setAddress("jdbc:postgresql://10.0.0.166:5432/edw");
        transferDataSource.setDriver("org.postgresql.Driver");
        transferDataSource.setUserName("gpadmin");
        transferDataSource.setPassWord("gpadmin123");

        IMetaLoader metaLoader1 = new MetaLoaderImpl(transferDataSource);

        //mysql
        /*String[] fields = {"tag"};
        String[] data = {"Transaction"};
        String[] tab_fields = {"create_time"};

        int pageCount = 1;//第几页
        int pageSize = 5;//每页数，默认10
        //取得总记录数，创建Page对象
        int totalRow = metaLoader1.queryCount("t_logger",fields,data);//通过select count 取得总记录数
        Page page = new Page(totalRow,pageCount,pageSize);

        List<Map<String,Object>> pageInfo = metaLoader1.query("t_logger",fields,data,tab_fields,page);*/

        //oracle
        /*String[] fields = {"PHONETIC_CODE"};
        String[] data = {"DSY"};
        String[] tab_fields = {"USER_NAME"};
        int pageCount = 1;//第几页
        int pageSize = 5;//每页数，默认10
        //取得总记录数，创建Page对象
        int totalRow = metaLoader1.queryCount("T_USERS",fields,data);//通过select count 取得总记录数
        Page page = new Page(totalRow,pageCount,pageSize);
        List<Map<String,Object>> pageInfo = metaLoader1.query("T_USERS",fields,data,tab_fields,page);*/

        //postgresql
        String[] fields = {"PHONETIC_CODE"};
        String[] data = {"DSY"};
        String[] tab_fields = {"USER_NAME"};
        int pageCount = 1;//第几页
        int pageSize = 5;//每页数，默认10
        //取得总记录数，创建Page对象
        int totalRow = metaLoader1.queryCount("T_USERS",fields,data);//通过select count 取得总记录数
        PageUtils page = new PageUtils(totalRow,pageCount,pageSize);
        List<Map<String,Object>> pageInfo = metaLoader1.query("T_USERS",fields,data,tab_fields,page);

        for (Map<String,Object> map:pageInfo){
            System.out.println(map.get("USER_NAME"));
        }

    }


}
