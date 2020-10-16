package info.pablogiraldo.tienda_bd.config;

import java.sql.*;

public class DbConn {

	// Propiedades
	private static Connection conn = null;
	private String driver;
	private String url;
	private String usuario;
	private String password;

	// Constructor
	private DbConn() {

		this.driver = "com.mysql.cj.jdbc.Driver";

		// Dev

//		this.url = "jdbc:mysql://localhost:3306/tienda_bd?serverTimezone=UTC";
//		this.usuario = "root";
//		this.password = "";

		// Prod

		this.url = "jdbc:mysql://ryfqldzbliwmq6g5.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/vfyv7vwy5u7165ss";
		this.usuario = "m9rbuvbdcexo6zye";
		this.password = "r86jezkjgdc8mb7z";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	} // Fin constructor

	// Métodos
	public static Connection getConnection() {

		if (conn == null) {
			new DbConn();
		}

		return conn;
	} // Fin getConnection

}
