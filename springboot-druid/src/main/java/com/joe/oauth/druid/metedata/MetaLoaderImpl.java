package com.joe.oauth.druid.metedata;

import com.joe.oauth.druid.domain.TransferDataSource;
import com.joe.oauth.druid.hikari.HikariConfigConnect;
import com.joe.oauth.druid.utils.JDBCUtils;
import com.joe.oauth.druid.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class MetaLoaderImpl implements IMetaLoader {

    private static final Logger logger = LoggerFactory.getLogger(MetaLoaderImpl.class);

    private TransferDataSource transferDataSource;

    private Connection conn;

    /**
     * @Program: 构造方法加载数据资源实例
     * @Description: TODO
     * @Author: Joe
     * @Create: 2019/10/22 15:27
     * @Version: 1.0.0
     */
    public MetaLoaderImpl(TransferDataSource transferDataSource) throws Exception {
        this.transferDataSource = transferDataSource;
        this.conn = HikariConfigConnect.GetConnect(transferDataSource);
    }

    /**
     * 获得数据库的一些相关信息
     */
    @Override
    public Map<String, Object> getDataBaseInformations() {
        Map<String, Object> map = new HashMap<>();
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            map.put("URL", dbMetaData.getURL());
            map.put("UserName", dbMetaData.getUserName());
            map.put("isReadOnly", dbMetaData.isReadOnly());
            map.put("DatabaseProductName", dbMetaData.getDatabaseProductName());
            map.put("DatabaseProductVersion", dbMetaData.getDatabaseProductVersion());
            map.put("DriverName", dbMetaData.getDriverName());
            map.put("DriverVersion", dbMetaData.getDriverVersion());
            map.put("falg", true);
        } catch (SQLException e) {
            map.put("falg", false);
            map.put("msg", e.getMessage());
            logger.error("getDataBaseInformations failure", e);
        } catch (Exception e) {
            map.put("falg", false);
            map.put("msg", e.getMessage());
            logger.error("getDataBaseInformations failure", e);
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return map;
    }

    /**
     * 获得该用户下面的所有表
     */
    @Override
    public List<Map<String, Object>> getAllTableList() {
        List<Map<String, Object>> allTableList = new ArrayList<>();
        ResultSet rs = null;
        try {
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM
            // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
            // "SYNONYM".
            String[] types = {"TABLE"};
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables(null, transferDataSource.getSchemaName(), "%", types);
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<>();
                map.put("tableName", rs.getString("TABLE_NAME"));
                // table type. Typical types are "TABLE", "VIEW", "SYSTEM
                // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
                // "SYNONYM".
                map.put("tableType", rs.getString("TABLE_TYPE"));
                // explanatory comment on the table
                map.put("remarks", rs.getString("REMARKS"));

                allTableList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getAllTableList failure", e);
        } catch (Exception e) {
            logger.error("getAllTableList failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return allTableList;
    }

    /**
     * 获得该用户下面的所有视图
     */
    @Override
    public List<Map<String, Object>> getAllViewList() {
        List<Map<String, Object>> allViewList = new ArrayList<>();
        ResultSet rs = null;
        try {
            String[] types = {"VIEW"};
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables(null, transferDataSource.getSchemaName(), "%", types);
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<>();
                map.put("viewName", rs.getString("TABLE_NAME"));
                // table type. Typical types are "TABLE", "VIEW", "SYSTEM
                // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
                // "SYNONYM".
                map.put("viewType", rs.getString("TABLE_TYPE"));
                // explanatory comment on the table
                map.put("remarks", rs.getString("REMARKS"));

                allViewList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getAllViewList failure", e);
        } catch (Exception e) {
            logger.error("getAllViewList failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return allViewList;
    }

    /**
     * 获得数据库中所有方案名称
     */
    @Override
    public List<String> getAllSchemas() {
        List<String> schemasList = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getSchemas();
            while (rs.next()) {
                schemasList.add(rs.getString("TABLE_SCHEM"));
            }
        } catch (SQLException e) {
            logger.error("getAllSchemas failure", e);
        } catch (Exception e) {
            logger.error("getAllSchemas failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return schemasList;
    }

    /**
     * 获得表或视图中的所有列信息
     */
    @Override
    public List<Map<String, Object>> getTableColumns(String tableName) {
        List<Map<String, Object>> tableColumnsList = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getColumns(null, transferDataSource.getSchemaName(), tableName, "%");
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<>();
                // table catalog (may be null)
                map.put("tableCat", rs.getString("TABLE_CAT"));
                // table schema (may be null)
                map.put("tableSchemaName", rs.getString("TABLE_SCHEM"));
                // table name
                map.put("tableName", rs.getString("TABLE_NAME"));
                // column name
                map.put("columnName", rs.getString("COLUMN_NAME"));
                // SQL type from java.sql.Types
                map.put("dataType", rs.getInt("DATA_TYPE"));
                // Data source dependent type name, for a UDT the type name is
                // fully qualified
                map.put("dataTypeName", rs.getString("TYPE_NAME"));
                // table schema (may be null)
                map.put("columnSize", rs.getInt("COLUMN_SIZE"));
                // the number of fractional digits. Null is returned for data
                // types where DECIMAL_DIGITS is not applicable.
                map.put("decimalDigits", rs.getInt("DECIMAL_DIGITS"));
                // Radix (typically either 10 or 2)
                map.put("numPrecRadix", rs.getInt("NUM_PREC_RADIX"));
                // is NULL allowed.
                map.put("nullAble", rs.getInt("NULLABLE"));
                // comment describing column (may be null)
                map.put("remarks", rs.getString("REMARKS"));
                // default value for the column, which should be interpreted as
                // a string when the value is enclosed in single quotes (may be
                // null)
                map.put("columnDef", rs.getString("COLUMN_DEF"));
                //
                map.put("sqlDataType", rs.getInt("SQL_DATA_TYPE"));
                //
                map.put("sqlDatetimeSub", rs.getInt("SQL_DATETIME_SUB"));
                // for char types the maximum number of bytes in the column
                map.put("charOctetLength", rs.getInt("CHAR_OCTET_LENGTH"));
                // index of column in table (starting at 1)
                map.put("ordinalPosition", rs.getInt("ORDINAL_POSITION"));
                // ISO rules are used to determine the nullability for a column.
                // YES --- if the parameter can include NULLs;
                // NO --- if the parameter cannot include NULLs
                // empty string --- if the nullability for the parameter is
                // unknown

                tableColumnsList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getTableColumns failure", e);
        } catch (Exception e) {
            logger.error("getTableColumns failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return tableColumnsList;
    }

    /**
     * 获得一个表的索引信息
     */
    @Override
    public List<Map<String, Object>> getIndexInfo(String tableName) {
        List<Map<String, Object>> indexInfoList = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getIndexInfo(null, transferDataSource.getSchemaName(), tableName, true, true);
            Map<String, Object> map = null;
            while (rs.next()) {
                // Can index values be non-unique. false when TYPE is
                // tableIndexStatistic
                map.put("nonUnique", rs.getBoolean("NON_UNIQUE"));
                // index catalog (may be null); null when TYPE is
                // tableIndexStatistic
                map.put("indexQualifier", rs.getString("INDEX_QUALIFIER"));
                // index name; null when TYPE is tableIndexStatistic
                map.put("indexName", rs.getString("INDEX_NAME"));
                // index type:
                // tableIndexStatistic - this identifies table statistics that
                // are returned in conjuction with a table's index descriptions
                // tableIndexClustered - this is a clustered index
                // tableIndexHashed - this is a hashed index
                // tableIndexOther - this is some other style of index
                map.put("type", rs.getShort("TYPE"));
                // column sequence number within index; zero when TYPE is
                // tableIndexStatistic
                map.put("ordinalPosition", rs.getShort("ORDINAL_POSITION"));
                // column name; null when TYPE is tableIndexStatistic
                map.put("columnName", rs.getString("COLUMN_NAME"));
                // column sort sequence, "A" => ascending, "D" => descending,
                // may be null if sort sequence is not supported; null when TYPE
                // is tableIndexStatistic
                map.put("ascOrDesc", rs.getString("ASC_OR_DESC"));
                // When TYPE is tableIndexStatistic, then this is the number of
                // rows in the table; otherwise, it is the number of unique
                // values in the index.
                map.put("cardinality", rs.getInt("CARDINALITY"));

                indexInfoList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getIndexInfo failure", e);
        } catch (Exception e) {
            logger.error("getIndexInfo failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return indexInfoList;
    }

    /**
     * 获得一个表的主键信息
     */
    @Override
    public Map<String, Object> getAllPrimaryKeys(String tableName) {
        Map<String, Object> map = new HashMap<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getPrimaryKeys(null, transferDataSource.getSchemaName(), tableName);
            while (rs.next()) {
                // column name
                map.put("columnName", rs.getString("COLUMN_NAME"));
                // sequence number within primary key( a value of 1 represents
                // the first column of the primary key, a value of 2 would
                // represent the second column within the primary key).
                map.put("keySeq", rs.getShort("KEY_SEQ"));
                // primary key name (may be null)
                map.put("pkName", rs.getString("PK_NAME"));
            }
        } catch (SQLException e) {
            logger.error("getAllPrimaryKeys failure", e);
        } catch (Exception e) {
            logger.error("getAllPrimaryKeys failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return map;
    }

    /**
     * 获得一个表的外键信息
     */
    @Override
    public List<Map<String, Object>> getAllExportedKeys(String tableName) {
        List<Map<String, Object>> allExportedKeysList = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getExportedKeys(null, transferDataSource.getSchemaName(), tableName);
            Map<String, Object> map = null;
            while (rs.next()) {
                // primary key table catalog (may be null)
                map.put("pkTableCat", rs.getString("PKTABLE_CAT"));
                // primary key table schema (may be null)
                map.put("pkTableSchem", rs.getString("PKTABLE_SCHEM"));
                // primary key table name
                map.put("pkTableName", rs.getString("PKTABLE_NAME"));
                // primary key column name
                map.put("pkColumnName", rs.getString("PKCOLUMN_NAME"));
                // foreign key table catalog (may be null) being exported (may
                // be null)
                map.put("fkTableCat", rs.getString("FKTABLE_CAT"));
                // foreign key table schema (may be null) being exported (may be
                // null)
                map.put("fkTableSchem", rs.getString("FKTABLE_SCHEM"));
                // foreign key table name being exported
                map.put("fkTableName", rs.getString("FKTABLE_NAME"));
                // foreign key column name being exported
                map.put("fkColumnName", rs.getString("FKCOLUMN_NAME"));
                // sequence number within foreign key( a value of 1 represents
                // the first column of the foreign key, a value of 2 would
                // represent the second column within the foreign key).
                map.put("keySeq", rs.getShort("KEY_SEQ"));
                // What happens to foreign key when primary is updated:
                // importedNoAction - do not allow update of primary key if it
                // has been imported
                // importedKeyCascade - change imported key to agree with
                // primary key update
                // importedKeySetNull - change imported key to NULL if its
                // primary key has been updated
                // importedKeySetDefault - change imported key to default values
                // if its primary key has been updated
                // importedKeyRestrict - same as importedKeyNoAction (for ODBC
                // 2.x compatibility)
                map.put("updateRule", rs.getShort("UPDATE_RULE"));

                // What happens to the foreign key when primary is deleted.
                // importedKeyNoAction - do not allow delete of primary key if
                // it has been imported
                // importedKeyCascade - delete rows that import a deleted key
                // importedKeySetNull - change imported key to NULL if its
                // primary key has been deleted
                // importedKeyRestrict - same as importedKeyNoAction (for ODBC
                // 2.x compatibility)
                // importedKeySetDefault - change imported key to default if its
                // primary key has been deleted
                map.put("delRule", rs.getShort("DELETE_RULE"));
                // foreign key name (may be null)
                map.put("fkName", rs.getString("FK_NAME"));
                // primary key name (may be null)
                map.put("pkName", rs.getString("PK_NAME"));
                // can the evaluation of foreign key constraints be deferred
                // until commit
                // importedKeyInitiallyDeferred - see SQL92 for definition
                // importedKeyInitiallyImmediate - see SQL92 for definition
                // importedKeyNotDeferrable - see SQL92 for definition
                map.put("deferRability", rs.getShort("DEFERRABILITY"));

                allExportedKeysList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getAllExportedKeys failure", e);
        } catch (Exception e) {
            logger.error("getAllExportedKeys failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return allExportedKeysList;
    }

    /**
     * 执行sql并返回list集合
     *
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> getQuerySQLByList(String sql) {
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();// 取得数据库的列名
            int numberOfColumns = rsmd.getColumnCount();
            //限制行数为100条
            while (rs.next() && rs.getRow() <= 100) {
                Map<String, Object> rowData = new LinkedHashMap<String, Object>();
                for (int i = 1; i < numberOfColumns + 1; i++) {
                    //主要处理map映射的是数据库里的类型，比如oracle，oracle.sql.TIMESTAMP,json中使用的是java.sql.Timestamp
                    if ("TIMESTAMP".equals(rsmd.getColumnTypeName(i))) {
                        rowData.put(rsmd.getColumnName(i), rs.getTimestamp(i));
                    } else {
                        rowData.put(rsmd.getColumnName(i), rs.getObject(i));
                    }
                }
                rsList.add(rowData);
            }
        } catch (SQLException e) {
            logger.error("getQuerySQLByList failure", e.getMessage());
        } finally {
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return rsList;
    }

    /**
     * 查询sql属性
     *
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> getListColumn(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            Map<String, Object> map = null;
            if (rs.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    map = new HashMap<>();
                    map.put("tablename", data.getTableName(i));
                    map.put("columnName", data.getColumnName(i));
                    map.put("comment", data.getColumnName(i));
                    map.put("typeName", data.getColumnTypeName(i));
                    Class c = Class.forName(data.getColumnClassName(i));
                    //首字母大写，其它小写
                    map.put("classTypeName", c.getSimpleName().substring(0, 1).toUpperCase().concat(c.getSimpleName().substring(1).toLowerCase()));
                    //默认第一列为该表的主键
                    if (1 == i) {
                        map.put("primarykey", data.getColumnName(i));
                    }
                    list.add(map);
                }
            }
        } catch (SQLException e) {
            logger.error("getListColumn failure", e.getMessage());
        } catch (Exception e) {
            logger.error("getListColumn failure", e.getMessage());
        } finally {
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return list;
    }

    /**
     * @Description TODO增删改的通用方法
     * @Author joe
     * @Date 2019/10/23 9:40
     * @Param [sql 要执行的sql, args 对象类型的数组  里面存放着 sql执行的占位符参数]
     * @Return boolean
     * @Exception
     */
    @Override
    public boolean executeUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            //有参数
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            //执行sql语句
            int i = stmt.executeUpdate();
            //返回  true
            return i > 0;
        } catch (SQLException e) {
            logger.error("executeUpdate failure", e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeConnection(conn);
        }
        return false;
    }

    /**
     * @Program: geometry-bi
     * @Description: TODO通用带参sql查询
     * @Author: xiaoqiaohui
     * @Create: 2019/10/25 10:46
     * @Version: 1.0.0
     */
    @Override
    public List<Map<String, Object>> executeQuery(String sql, Object... args) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            //有可能有参数
            for (int i = 0; args!=null&&i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            //执行sql语句
            rs = stmt.executeQuery();
            //创建List集合
            List<Map<String, Object>> list = new ArrayList<>();
            //获取本次查询结果集有多少列
            int count = rs.getMetaData().getColumnCount();
            //while循环
            while (rs.next()) {
                //创建Map集合   获取一个数据封装成一个Map集合
                Map<String, Object> map = new HashMap<>();
                //for循环  遍历所有的列
                for (int i = 0; i < count; i++) {
                    //获取本次查询结果集的列名
                    String name = rs.getMetaData().getColumnLabel(i + 1);
                    map.put(name, rs.getObject(name));
                }
                //把所有的map集合添加到List集合中
                list.add(map);
            }
            //返回值
            return list;
        } catch (SQLException e) {
            logger.error("executeQuery failure", e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return null;
    }

    /**
     * 通用添加数据
     * @param tabName 表名
     * @param fields 参数字段
     * @param data 参数字段数据
     */
    public boolean insert(String tabName,String[] fields,String[] data) {
        PreparedStatement stmt = null;
        try {
            String sql = "insert into "+tabName+"(";
            int length = fields.length;
            for(int i=0;i<length;i++){
                sql+=fields[i];
                //防止最后一个,
                if(i<length-1){
                    sql+=",";
                }
            }
            sql+=") values(";
            for(int i=0;i<length;i++){
                sql+="?";
                //防止最后一个,
                if(i<length-1){
                    sql+=",";
                }
            }
            sql+=");";
            logger.error("添加数据的sql:"+sql);
            //预处理SQL 防止注入
            stmt = conn.prepareStatement(sql);
            //注入参数
            for(int i=0;i<length;i++){
                stmt.setString(i+1,data[i]);
            }
            //执行
            int i =  stmt.executeUpdate();
            //返回  true
            return i > 0;
        } catch (SQLException e) {
            logger.error("添加数据失败:",e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeConnection(conn);
        }
        return false;
    }

    /**
     * 删除表数据
     * @param fields 参数字段
     * @param data 参数字段数据
     * @param tabName 表名称
     */
    public boolean delete(String tabName,String[] fields,String[] data){
        PreparedStatement stmt = null;
        try {
            String sql = "delete from "+tabName+" where ";
            logger.error("删除数据的sql:",sql);
            int length = fields.length;
            for(int i=0;i<length;i++){
                sql+=fields[i]+" = ? ";
                //防止最后一个,
                if(i<length-1){
                    sql+=" and ";
                }
            }
            sql+=";";
            logger.error("查询sql:",sql);
            //预处理SQL 防止注入
            stmt = conn.prepareStatement(sql);
            //注入参数
            for(int i=0;i<length;i++){
                stmt.setString(i+1,data[i]);
            }
            //执行
            int i = stmt.executeUpdate();
            //返回  true
            return i > 0;
        } catch (SQLException e) {
            logger.error("删除数据失败", e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean update(String tabName, String[] fields, String[] data,String[] conditions,String[] conditions_param) {
        PreparedStatement stmt = null;
        try {
            String sql = "update "+tabName+" set ";
            logger.error("修改数据的sql:",sql);
            int length = fields.length;
            for(int i=0;i<length;i++){
                sql+=fields[i]+" = '"+data[i]+"' ";
                //防止最后一个,
                if(i<length-1){
                    sql+=" and ";
                }
            }
            sql+=" where ";
            int length2 = conditions.length;
            for(int i=0;i<length2;i++){
                sql+=conditions[i]+" = ? ";
                //防止最后一个,
                if(i<length2-1){
                    sql+=" and ";
                }
            }
            logger.error("查询sql:",sql);
            //预处理SQL 防止注入
            stmt = conn.prepareStatement(sql);
            //注入参数
            for(int i=0;i<length2;i++){
                stmt.setString(i+1,conditions_param[i]);
            }
            //执行
            int i = stmt.executeUpdate();
            //返回  true
            return i > 0;
        } catch (SQLException e) {
            logger.error("修改数据失败", e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeConnection(conn);
        }
        return false;
    }

    @Override
    public int queryCount(String tabName, String[] fields, String[] data) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] result = null;
        int count = 0;
        try {
            String sql = "select count(*) from "+tabName+" where 1=1 ";
            int length = fields.length;
            for(int i=0;i<length;i++){
                sql+= " and " + fields[i]+" = ? ";
                //防止最后一个,
                if(i<length-1){
                    sql+=" and ";
                }
            }
            sql+=" ";
            logger.error("查询sql:",sql);
            //预处理SQL 防止注入
            stmt = conn.prepareStatement(sql);
            //注入参数
            for(int i=0;i<length;i++){
                stmt.setString(i+1,data[i]);
            }
            //查询结果集
            rs = stmt.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("查询失败" , e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            //JDBCUtils.closeConnection(conn);
        }
        return count;
    }

    /**
     * 查询表 【查询结果的顺序要和数据库字段的顺序一致】
     * @param tabName 表名
     * @param fields 参数字段
     * @param data 参数字段数据
     * @param tab_fields 数据库的字段
     * @param page 页数
     */
    public List<Map<String,Object>> query(String tabName, String[] fields, String[] data, String[] tab_fields, PageUtils page){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] result = null;
        List<Map<String, Object>> dataList = null;
        try {
            dataList = new ArrayList<Map<String,Object>>();
            String sql = "select * from "+tabName+" where 1=1 ";
            int length = fields.length;
            for(int i=0;i<length;i++){
                sql+= " and " + fields[i]+" = ? ";
                //防止最后一个,
                if(i<length-1){
                    sql+=" and ";
                }
            }
            sql+=" ";
            //预处理SQL 防止注入
            //游标类型：
            //ResultSet.TYPE_FORWORD_ONLY:只进游标
            //ResultSet.TYPE_SCROLL_INSENSITIVE:可滚动。但是不受其他用户对数据库更改的影响。
            //ResultSet.TYPE_SCROLL_SENSITIVE:可滚动。当其他用户更改数据库时这个记录也会改变。
            //能否更新记录：
            //ResultSet.CONCUR_READ_ONLY,只读
            //ResultSet.CONCUR_UPDATABLE,可更新
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //注入参数
            for(int i=0;i<length;i++){
                stmt.setString(i+1,data[i]);
            }
            stmt.setMaxRows(page.getEndIndex());//关键代码，设置最大记录数为当前页记录的截止下标
            logger.error("查询sql:",sql);
            //查询结果集
            rs = stmt.executeQuery();
            if (page.getBeginIndex() > 0) {
                rs.absolute(page.getBeginIndex());//关键代码，直接移动游标为当前页起始记录处
            }
            while(rs.next()){
                Map<String, Object> map = new LinkedHashMap();
                for (int i = 0; i < tab_fields.length; i++) {
                    map.put(tab_fields[i],rs.getString(tab_fields[i]));
                }
                dataList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("查询失败" , e.getMessage());
        } finally {
            //关闭资源
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return dataList;
    }

}
