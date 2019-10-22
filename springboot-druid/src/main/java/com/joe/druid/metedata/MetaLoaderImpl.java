package com.joe.druid.metedata;

import com.joe.druid.hikari.HikariConfigConnect;
import com.joe.druid.pojo.TransferDataSource;
import com.joe.druid.utils.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class MetaLoaderImpl implements MetaLoader {

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
            map.put("URL",dbMetaData.getURL());
            map.put("UserName",dbMetaData.getUserName());
            map.put("isReadOnly" , dbMetaData.isReadOnly());
            map.put("DatabaseProductName" , dbMetaData.getDatabaseProductName());
            map.put("DatabaseProductVersion" , dbMetaData.getDatabaseProductVersion());
            map.put("DriverName" , dbMetaData.getDriverName());
            map.put("DriverVersion" , dbMetaData.getDriverVersion());
        } catch (SQLException e) {
            logger.error("getDataBaseInformations failure", e);
        } catch (Exception e) {
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
            String[] types = { "TABLE" };
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables(null, transferDataSource.getSchemaName(), "%", types);
            Map<String,Object> map = null;
            while (rs.next()){
                map = new HashMap<>();
                map.put("tableName",rs.getString("TABLE_NAME"));
                // table type. Typical types are "TABLE", "VIEW", "SYSTEM
                // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
                // "SYNONYM".
                map.put("tableType",rs.getString("TABLE_TYPE"));
                // explanatory comment on the table
                map.put("remarks",rs.getString("REMARKS"));

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
            String[] types = { "VIEW" };
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables(null, transferDataSource.getSchemaName(), "%", types);
            Map<String,Object> map = null;
            while (rs.next()){
                map = new HashMap<>();
                map.put("viewName",rs.getString("TABLE_NAME"));
                // table type. Typical types are "TABLE", "VIEW", "SYSTEM
                // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
                // "SYNONYM".
                map.put("viewType",rs.getString("TABLE_TYPE"));
                // explanatory comment on the table
                map.put("remarks",rs.getString("REMARKS"));

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
        List<String > schemasList = new ArrayList<>();
        ResultSet rs = null;
        try{
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getSchemas();
            while (rs.next())
            {
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
        try{
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getColumns(null, transferDataSource.getSchemaName(), tableName, "%");
            Map<String,Object> map = null;
            while (rs.next()){
                map = new HashMap<>();
                // table catalog (may be null)
                map.put("tableCat",rs.getString("TABLE_CAT"));
                // table schema (may be null)
                map.put("tableSchemaName",rs.getString("TABLE_SCHEM"));
                // table name
                map.put("tableName",rs.getString("TABLE_NAME"));
                // column name
                map.put("columnName",rs.getString("COLUMN_NAME"));
                // SQL type from java.sql.Types
                map.put("dataType",rs.getInt("DATA_TYPE"));
                // Data source dependent type name, for a UDT the type name is
                // fully qualified
                map.put("dataTypeName",rs.getString("TYPE_NAME"));
                // table schema (may be null)
                map.put("columnSize",rs.getInt("COLUMN_SIZE"));
                // the number of fractional digits. Null is returned for data
                // types where DECIMAL_DIGITS is not applicable.
                map.put("decimalDigits",rs.getInt("DECIMAL_DIGITS"));
                // Radix (typically either 10 or 2)
                map.put("numPrecRadix",rs.getInt("NUM_PREC_RADIX"));
                // is NULL allowed.
                map.put("nullAble",rs.getInt("NULLABLE"));
                // comment describing column (may be null)
                map.put("remarks",rs.getString("REMARKS"));
                // default value for the column, which should be interpreted as
                // a string when the value is enclosed in single quotes (may be
                // null)
                map.put("columnDef",rs.getString("COLUMN_DEF"));
                //
                map.put("sqlDataType",rs.getInt("SQL_DATA_TYPE"));
                //
                map.put("sqlDatetimeSub",rs.getInt("SQL_DATETIME_SUB"));
                // for char types the maximum number of bytes in the column
                map.put("charOctetLength",rs.getInt("CHAR_OCTET_LENGTH"));
                // index of column in table (starting at 1)
                map.put("ordinalPosition",rs.getInt("ORDINAL_POSITION"));
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
        try{
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getIndexInfo(null, transferDataSource.getSchemaName(), tableName, true, true);
            Map<String,Object> map = null;
            while (rs.next()){
                // Can index values be non-unique. false when TYPE is
                // tableIndexStatistic
                map.put("nonUnique",rs.getBoolean("NON_UNIQUE"));
                // index catalog (may be null); null when TYPE is
                // tableIndexStatistic
                map.put("indexQualifier",rs.getString("INDEX_QUALIFIER"));
                // index name; null when TYPE is tableIndexStatistic
                map.put("indexName",rs.getString("INDEX_NAME"));
                // index type:
                // tableIndexStatistic - this identifies table statistics that
                // are returned in conjuction with a table's index descriptions
                // tableIndexClustered - this is a clustered index
                // tableIndexHashed - this is a hashed index
                // tableIndexOther - this is some other style of index
                map.put("type",rs.getShort("TYPE"));
                // column sequence number within index; zero when TYPE is
                // tableIndexStatistic
                map.put("ordinalPosition",rs.getShort("ORDINAL_POSITION"));
                // column name; null when TYPE is tableIndexStatistic
                map.put("columnName",rs.getString("COLUMN_NAME"));
                // column sort sequence, "A" => ascending, "D" => descending,
                // may be null if sort sequence is not supported; null when TYPE
                // is tableIndexStatistic
                map.put("ascOrDesc",rs.getString("ASC_OR_DESC"));
                // When TYPE is tableIndexStatistic, then this is the number of
                // rows in the table; otherwise, it is the number of unique
                // values in the index.
                map.put("cardinality",rs.getInt("CARDINALITY"));

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
    public List<Map<String, Object>> getAllPrimaryKeys(String tableName) {
        List<Map<String, Object>> allPrimaryKeysList = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            rs = dbMetaData.getPrimaryKeys(null, transferDataSource.getSchemaName(), tableName);
            Map<String,Object> map = null;
            while (rs.next()) {
                // column name
                map.put("columnName",rs.getString("COLUMN_NAME"));
                // sequence number within primary key( a value of 1 represents
                // the first column of the primary key, a value of 2 would
                // represent the second column within the primary key).
                map.put("keySeq",rs.getShort("KEY_SEQ"));
                // primary key name (may be null)
                map.put("pkName",rs.getString("PK_NAME"));

                allPrimaryKeysList.add(map);
            }
        } catch (SQLException e) {
            logger.error("getAllPrimaryKeys failure", e);
        } catch (Exception e) {
            logger.error("getAllPrimaryKeys failure", e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return allPrimaryKeysList;
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
            Map<String,Object> map = null;
            while (rs.next())
            {
                // primary key table catalog (may be null)
                map.put("pkTableCat",rs.getString("PKTABLE_CAT"));
                // primary key table schema (may be null)
                map.put("pkTableSchem",rs.getString("PKTABLE_SCHEM"));
                // primary key table name
                map.put("pkTableName",rs.getString("PKTABLE_NAME"));
                // primary key column name
                map.put("pkColumnName",rs.getString("PKCOLUMN_NAME"));
                // foreign key table catalog (may be null) being exported (may
                // be null)
                map.put("fkTableCat",rs.getString("FKTABLE_CAT"));
                // foreign key table schema (may be null) being exported (may be
                // null)
                map.put("fkTableSchem",rs.getString("FKTABLE_SCHEM"));
                // foreign key table name being exported
                map.put("fkTableName",rs.getString("FKTABLE_NAME"));
                // foreign key column name being exported
                map.put("fkColumnName",rs.getString("FKCOLUMN_NAME"));
                // sequence number within foreign key( a value of 1 represents
                // the first column of the foreign key, a value of 2 would
                // represent the second column within the foreign key).
                map.put("keySeq",rs.getShort("KEY_SEQ"));
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
                map.put("updateRule",rs.getShort("UPDATE_RULE"));

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
                map.put("delRule",rs.getShort("DELETE_RULE"));
                // foreign key name (may be null)
                map.put("fkName",rs.getString("FK_NAME"));
                // primary key name (may be null)
                map.put("pkName",rs.getString("PK_NAME"));
                // can the evaluation of foreign key constraints be deferred
                // until commit
                // importedKeyInitiallyDeferred - see SQL92 for definition
                // importedKeyInitiallyImmediate - see SQL92 for definition
                // importedKeyNotDeferrable - see SQL92 for definition
                map.put("deferRability",rs.getShort("DEFERRABILITY"));

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
            while (rs.next()&&rs.getRow()<=100) {
                Map<String, Object> rowData = new LinkedHashMap<String, Object>();
                for (int i = 1; i < numberOfColumns + 1; i++) {
                    //主要处理map映射的是数据库里的类型，比如oracle，oracle.sql.TIMESTAMP,json中使用的是java.sql.Timestamp
                    if("TIMESTAMP".equals(rsmd.getColumnTypeName(i))){
                        rowData.put(rsmd.getColumnName(i), rs.getTimestamp(i));
                    }else{
                        rowData.put(rsmd.getColumnName(i), rs.getObject(i));
                    }
                }
                rsList.add(rowData);
            }
        } catch (SQLException e) {
            logger.debug("getQuerySQLByList failure",e.getMessage());
        }finally{
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return rsList;
    }

    /**
     * 查询sql属性
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
            ResultSetMetaData data=rs.getMetaData();
            Map<String,Object> map = null;
            if(rs.next()){
                for(int i = 1 ; i<= data.getColumnCount() ; i++) {
                    map = new HashMap<>();
                    map.put("tablename",data.getTableName(i));
                    map.put("columnName",data.getColumnName(i));
                    map.put("comment",data.getColumnName(i));
                    map.put("typeName",data.getColumnTypeName(i));
                    Class c=Class.forName(data.getColumnClassName(i));
                    //首字母大写，其它小写
                    map.put("classTypeName",c.getSimpleName().substring(0,1).toUpperCase().concat(c.getSimpleName().substring(1).toLowerCase()));
                    //默认第一列为该表的主键
                    if(1==i){
                        map.put("primarykey",data.getColumnName(i));
                    }
                    list.add(map);
                }
            }
        }catch (SQLException e){
            logger.debug("getListColumn failure",e.getMessage());
        }catch (Exception e){
            logger.debug("getListColumn failure",e.getMessage());
        }finally{
            JDBCUtils.closePreparedStatement(stmt);
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public <T> T query(String sql, String exceptionMessage, ResultSetExtractor<T> rsExtractor, Object... args) {
        ResultSet rs=null;
        PreparedStatement st=null;
        try{
            st = conn.prepareStatement(sql);
            for(int i=1;i<=args.length;++i){
                st.setObject(i, args[i-1]);
            }
            rs=st.executeQuery();
            return rsExtractor.extractData(rs);
        }catch(SQLException e){
            throw new RuntimeException(exceptionMessage, e);
        }finally{
            JDBCUtils.closePreparedStatement(st);
            JDBCUtils.closeResultSet(rs);
        }
    }
}
