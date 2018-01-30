package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class sqliteConnection {
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
//			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C://Login//Employee.db");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/employeedata","admin","mdantsane");
			conn.setAutoCommit(true);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	}
