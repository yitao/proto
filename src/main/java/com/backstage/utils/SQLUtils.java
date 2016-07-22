package com.backstage.utils;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;
import java.util.*;

/**
 * Created by yitao on 2016/5/17.
 */
public class SQLUtils {

    public static void close(Connection x) {
        if(x != null) {
            try {
                x.close();
            } catch (Exception var2) {
                //LOG.debug("close connection error", var2);
            }

        }
    }

    public static void close(Statement x) {
        if(x != null) {
            try {
                x.close();
            } catch (Exception var2) {
                //LOG.debug("close statement error", var2);
            }

        }
    }

    public static void close(ResultSet x) {
        if(x != null) {
            try {
                x.close();
            } catch (Exception var2) {
                //LOG.debug("close result set error", var2);
            }

        }
    }

    public static void close(Closeable x) {
        if(x != null) {
            try {
                x.close();
            } catch (Exception var2) {
                //LOG.debug("close error", var2);
            }

        }
    }


    public static int executeUpdate(DataSource dataSource, String sql, List<Object> parameters) throws SQLException {
        Connection conn = null;

        int var4;
        try {
            conn = dataSource.getConnection();
            var4 = executeUpdate(conn, sql, parameters);
        } finally {
            close(conn);
        }

        return var4;
    }

    public static int executeUpdate(Connection conn, String sql, List<Object> parameters) throws SQLException {
        PreparedStatement stmt = null;

        int updateCount;
        try {
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, parameters);
            updateCount = stmt.executeUpdate();
        } finally {
            close((Statement)stmt);
        }

        return updateCount;
    }

    public static void execute(DataSource dataSource, String sql, Object... parameters) throws SQLException {
        execute(dataSource, sql, Arrays.asList(parameters));
    }

    public static void execute(DataSource dataSource, String sql, List<Object> parameters) throws SQLException {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            execute(conn, sql, parameters);
        } finally {
            close(conn);
        }

    }

    public static void execute(Connection conn, String sql, List<Object> parameters) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, parameters);
            stmt.executeUpdate();
        } finally {
            close((Statement)stmt);
        }

    }

    public static List<Map<String, Object>> executeQuery(DataSource dataSource, String sql, Object... parameters) throws SQLException {
        return executeQuery(dataSource, sql, Arrays.asList(parameters));
    }

    public static List<Map<String, Object>> executeQuery(DataSource dataSource, String sql, List<Object> parameters) throws SQLException {
        Connection conn = null;

        List var4;
        try {
            conn = dataSource.getConnection();
            var4 = executeQuery(conn, sql, parameters);
        } finally {
            close(conn);
        }

        return var4;
    }

    public static List<Map<String, Object>> executeQuery(Connection conn, String sql, List<Object> parameters) throws SQLException {
        ArrayList rows = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, parameters);
            rs = stmt.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();

            while(rs.next()) {
                LinkedHashMap row = new LinkedHashMap();
                int i = 0;

                for(int size = rsMeta.getColumnCount(); i < size; ++i) {
                    String columName = rsMeta.getColumnLabel(i + 1);
                    Object value = rs.getObject(i + 1);
                    row.put(columName, value);
                }

                rows.add(row);
            }
        } finally {
            close(rs);
            close((Statement)stmt);
        }

        return rows;
    }

    private static void setParameters(PreparedStatement stmt, List<Object> parameters) throws SQLException {
        int i = 0;

        for(int size = parameters.size(); i < size; ++i) {
            Object param = parameters.get(i);
            stmt.setObject(i + 1, param);
        }

    }
}
