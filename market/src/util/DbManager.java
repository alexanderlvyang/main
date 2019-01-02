package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbManager {
	public static Connection getConnection(){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/market?useUnicode=true&characterEncoding=UTF-8";
		String uid="root";
		String pswd="toor";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,uid,pswd);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return conn;
}
	public static void closePreparedStatement(PreparedStatement prep){
		try {
			if(prep!=null)
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResultSet(ResultSet result){
		try {
			if(result!=null)
				result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null&&!conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}