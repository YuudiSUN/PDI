package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	private static String host = "localhost";
	private static String base = "GDI";
	private static String user = "GDI";
	private static String password = "123456";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	/**
	 * Singleton instance.
	 */
	private static Connection connection;

	public static Connection getConnection() {		
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());			
			}
		}
		return connection;
	}
}
