package info.pablogiraldo.tienda_bd.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConn {

	public static Properties envProperties;

	private static Connection conn = null;

	private String driver;
	private String url;
	private String user;
	private String pass;

	private DbConn() {

		driver = "com.mysql.cj.jdbc.Driver";

		// Dev

//		url = "jdbc:mysql://localhost:3306/tienda_bd?serverTimezone=UTC";
//		usuario = "root";
//		password = "";

		// Prod

//		url = "jdbc:mysql://ryfqldzbliwmq6g5.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/vfyv7vwy5u7165ss";
//		usuario = "m9rbuvbdcexo6zye";
//		password = "r86jezkjgdc8mb7z";

//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, usuario, password);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}

		url = envProperties.getProperty("db.url");
		user = envProperties.getProperty("db.user");
		pass = envProperties.getProperty("db.pass");

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, pass);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		if (conn == null) {
			new DbConn();
		}

		return conn;
	}

	public static void resetConnection() {

		new DbConn();

	}

}
