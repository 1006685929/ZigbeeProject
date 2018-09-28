package com.nuc.zigbee.utils;

import com.nuc.zigbee.entity.ErrorSensor;
import com.nuc.zigbee.entity.Sensor;

import java.sql.*;

public class DBUtils {
    public final static String DRIVERNAME = "com.mysql.jdbc.Driver"; // 加载JDBC驱动
    public final static String DBURL = "jdbc:mysql://localhost:3306/sensor?useSSL=false";
    public final static String USERNAME = "root"; // 默认用户名
    public final static String USERPASSWORD = "root"; // 密码

    public Statement stmt = null; // 声明Statement对象的实例
    public ResultSet rs = null; // 声明ResultSet对象的实例

    private static Connection getConn() {
        Connection conn = null; // 声明Connection对象的实例
        try {
            Class.forName(DRIVERNAME);
            conn = DriverManager.getConnection(DBURL, USERNAME, USERPASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insert(Sensor sensor) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into sensor (time,temp,humi,light) values(?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sensor.getDate());
            pstmt.setString(2,sensor.getTemp());
            pstmt.setString(3,sensor.getHumi());
            pstmt.setString(4,sensor.getLight());

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int insertError(ErrorSensor errorSensor) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into error(error_time,error_temp,error_light,error_info) values(?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,errorSensor.getErrorDate());
            pstmt.setString(2,errorSensor.getErrorTemp());
            pstmt.setString(3,errorSensor.getErrorLight());
            pstmt.setString(4,errorSensor.getErrorInfo());

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


}
