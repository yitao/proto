package com.backstage.datasource;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;
import java.util.*;

/**
 * Created by yitao on 2016/5/16.
 */
public class SqlSessionFactory {
    private DataSource dataSource;

    public SqlSessionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
