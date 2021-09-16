package edu.demian.wp.model.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    public static DataSource getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    private static class DataSourceHolder {
        private static final DataSource INSTANCE;

        static {
            MysqlDataSource instance = new MysqlDataSource();

            instance.setUrl("jdbc:mysql://localhost:3306/hoteldb?useUnicode=yes&characterEncoding=UTF-8");
            instance.setUser("dbuser");
            instance.setPassword("pass");

            try (Connection connection = instance.getConnection()) {
                if (connection == null) {
                    throw new RuntimeException("Can't create test connection");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Can't create test connection", e);
            }

            INSTANCE = instance;
        }
    }
}
