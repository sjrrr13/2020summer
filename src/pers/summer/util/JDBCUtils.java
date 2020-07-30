package pers.summer.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class JDBCUtils {

    private static DataSource dataSource = null;

    static {
        dataSource = new ComboPooledDataSource("project");
    }

    //获取连接
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    //释放内存
    public static void release(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
