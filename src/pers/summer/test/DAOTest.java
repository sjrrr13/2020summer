package pers.summer.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import pers.summer.dao.DAO;
import pers.summer.entity.Image;
import pers.summer.entity.User;
import pers.summer.util.JDBCUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class DAOTest {
    private QueryRunner queryRunner = new QueryRunner();

    @Test
    void query() {

        String sql = "SELECT GeoNameID FROM geocities WHERE AsciiName = ?";
        Connection connection = null;
//Calgary
        try {
            connection = JDBCUtils.getConnection();
            Integer i = (Integer) queryRunner.query(connection, sql, new ScalarHandler(), "Calgary");
            System.out.println(i.longValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

    }

    @Test
    void queryForList() {
        String sql = "SELECT FriendID FROM friend WHERE UID = ?";
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            System.out.println(queryRunner.query(connection, sql, new ArrayListHandler(), 1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }
    }

    @Test
    void queryForValue() {
        Connection connection = null;
        String sql = "SELECT ImageID FROM travelimagefavor WHERE UID = ?";

        try{
            connection = JDBCUtils.getConnection();
            List<Object[]> list = queryRunner.query(connection, sql, new ArrayListHandler(), 1);
            for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i)[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

    }
}