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

		if (envProperties != null) {
			driver = envProperties.getProperty("db.driver");

			url = envProperties.getProperty("db.url");
			user = envProperties.getProperty("db.user");
			pass = envProperties.getProperty("db.pass");

		} else {
			driver = "com.mysql.cj.jdbc.Driver";

			url = "jdbc:mysql://ryfqldzbliwmq6g5.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/vfyv7vwy5u7165ss";
			user = "m9rbuvbdcexo6zye";
			pass = "r86jezkjgdc8mb7z";
		}

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
}
