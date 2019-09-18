/**
 * written by: CHIA-JO LIN & HAIYING LIU
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	static Connection con = null;
	public JDBCUtil() throws SQLException, ClassNotFoundException{
		String databaseName = "StockTestDatabase";
		String userName = "root";
		String password = "1119";
		Class.forName("com.mysql.jdbc.Driver");
  
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
		if (con != null) {
			con.isClosed();
		}
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{		
		return con;
	}
}
