package pers.summer.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.summer.util.JDBCUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

public class DAO<T> {

    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;

    public DAO() {
        Type superClass = getClass().getGenericSuperclass();

        if(superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            Type[] typeArgs = parameterizedType.getActualTypeArguments();

            if(typeArgs != null && typeArgs.length > 0) {
                if(typeArgs[0] instanceof Class) {
                    clazz = (Class<T>) typeArgs[0];
                }
            }
        }
    }

    public int update(String sql, Object ... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.update(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

        return 0;
    }

    public T query(String sql, Object ... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

        return null;
    }

    public List<T> queryForList(String sql, Object ... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

        return null;
    }

    public <E> E queryForValue(String sql, Object ... args) {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

        return null;
    }

    public List<Object[]> queryForValueList(String sql, Object ... args) {
        Connection connection = null;

        try{
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new ArrayListHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection);
        }

        return null;
    }
}
