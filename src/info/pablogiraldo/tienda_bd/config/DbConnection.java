package info.pablogiraldo.tienda_bd.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	// Dev

	private static String url = "jdbc:mysql://localhost:3306/tienda_bd?serverTimezone=UTC";
	private static String user = "root";
	private static String pass = "";

	// Prod

//	private static String url = "jdbc:mysql://gi6kn64hu98hy0b6.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/x4y287x0mhb2xxtd";
//	private static String user = "s1o3v3rkbdhm1bc7";
//	private static String pass = "jb6i5e6gv772zidi";

	public static Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, pass);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
