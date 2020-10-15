package info.pablogiraldo.tienda_bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import info.pablogiraldo.tienda_bd.config.DbConnection;
import info.pablogiraldo.tienda_bd.model.User;
import org.apache.commons.codec.binary.Base64;

public class UserDAO {

	private Connection connection;

	public UserDAO() {

	}

	private Connection getConnection() {
		return DbConnection.getConnection();
	}

	public boolean insertUser(User user) throws SQLException {

		String sql = "INSERT INTO users (name, pass ) VALUES (?, ?)";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getName());
//		statement.setString(2, user.getPass());

		Base64 base64 = new Base64();

		String encode_pass = new String(base64.encode(user.getPass().getBytes()));

		statement.setString(2, encode_pass);

		boolean rowInserted = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowInserted;
	}

	public List<User> listAllUsers() throws SQLException {

		List<User> listUsers = new ArrayList<>();
		String sql = "SELECT * FROM users";

//		connection = DbConnection.getConnection();

		connection = getConnection();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String pass = resultSet.getString("pass");

			User user = new User(id, name, pass);
			listUsers.add(user);
		}

		resultSet.close();
		statement.close();
		connection.close();

		Collections.sort(listUsers);

		return listUsers;
	}

	public boolean deleteUser(User user) throws SQLException {

		String sql = "DELETE FROM users where id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, user.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {

		String sql = "UPDATE users SET name = ?, pass = ?";
		sql += " WHERE id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getName());
		statement.setString(2, user.getPass());

		boolean rowUpdated = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowUpdated;
	}

	public User getUser(int id) throws SQLException {

		User user = null;
		String sql = "SELECT * FROM users WHERE id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String name = resultSet.getString("name");
			String pass = resultSet.getString("pass");

			user = new User(id, name, pass);
		}

		resultSet.close();
		statement.close();

		return user;
	}

	public User getUser(String usrname) throws SQLException {

		User user = null;
		String sql = "SELECT * FROM users WHERE name = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, usrname);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String name = resultSet.getString("name");
			String pass = resultSet.getString("pass");

			user = new User(name, pass);
		} else {
			user = new User("", "");
		}

		resultSet.close();
		statement.close();

		return user;
	}
}
