package com.yangqihang.reflect;

/*
 * 要查询N张表的数据,但是不想写N多的方法,能否写一个方法完成所有表的查询工作
 * */

import com.yangqihang.entity.Emp;
import com.yangqihang.util.DBUtil;
import oracle.sql.DATE;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseDaoImpl {

    /**
     * 统一的查询表的方法
     *
     * @param sql    不同的sql语句
     * @param params sql语句的参数
     * @param clazz  sql语句查询返回的对象
     * @return
     */
    public List getRows(String sql, Object[] params, Class clazz) {
        List list = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //建立连接
            connection = DBUtil.getConnection();
            //创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给sql语句填充参数
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            //开始执行查询操作,resultSet中返回的结果,需要将返回的结果放置到不同的对象中
            resultSet = preparedStatement.executeQuery();
            //获取结果集合的元数据对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            //判断查询到的每一行记录中包含多少个列
            int columnCount = metaData.getColumnCount();
            //循环遍历resultSet
            while (resultSet.next()) {
                Object obj = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //从结果集合中获取单一列的值
                    Object objValue = resultSet.getObject(i + 1);
                    //获取列的名称
                    String columnName = metaData.getColumnName(i + 1).toLowerCase();
                    //获取类中的属性
                    Field declaredField = clazz.getDeclaredField(columnName);
                    //获取类中属性对应的set方法
                    Method method = clazz.getMethod(getSetName(columnName), declaredField.getType());
                    if (objValue instanceof Number) {
                        Number number = (Number) objValue;
                        String fname = declaredField.getType().getName();
                        if ("int".equals(fname) || "java.lang.Integer".equals(fname)) {
                            method.invoke(obj, number.intValue());
                        } else if ("byte".equals(fname) || "java.lang.Byte".equals(fname)) {
                            method.invoke(obj, number.byteValue());
                        } else if ("short".equals(fname) || "java.lang.Short".equals(fname)) {
                            method.invoke(obj, number.shortValue());
                        } else if ("long".equals(fname) || "java.lang.Long".equals(fname)) {
                            method.invoke(obj, number.longValue());
                        } else if ("float".equals(fname) || "java.lang.Float".equals(fname)) {
                            method.invoke(obj, number.floatValue());
                        } else if ("double".equals(fname) || "java.lang.Double".equals(fname)) {
                            method.invoke(obj, number.doubleValue());
                        }
                    } else {
                        method.invoke(obj, objValue);
                    }
                }
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement, resultSet);
        }

        return list;
    }

    public void crud(String sql, Object[] params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, preparedStatement);
        }
    }

    public String getSetName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static void main(String[] args) {

        BaseDaoImpl baseDao = new BaseDaoImpl();
//        List rows = baseDao.getRows("select empno,ename,deptno from emp where deptno =?", new Object[]{10}, Emp.class);
//        for (Iterator it = rows.iterator(); it.hasNext(); ) {
//            Emp emp = (Emp) it.next();
//            System.out.println(emp);
//        }


//        List rows = baseDao.getRows("select * from dept", new Object[]{}, Dept.class);
//        for (Iterator it = rows.iterator(); it.hasNext(); ) {
//            Dept dept = (Dept) it.next();
//            System.out.println(dept);
//        }

//        baseDao.crud("insert into emp(empno,ename,deptno) values(?,?,?)", new Object[]{7777, "zhangsan", 10});
//        baseDao.crud("delete from emp where empno =?", new Object[]{7777});
        baseDao.crud("update emp set ename='lisi' where empno = ?", new Object[]{7777});
    }
}
