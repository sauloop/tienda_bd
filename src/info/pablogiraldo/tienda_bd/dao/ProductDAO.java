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
import info.pablogiraldo.tienda_bd.model.Product;

public class ProductDAO {

	private Connection connection;

	public ProductDAO() {

	}

	private Connection getConnection() {
		return DbConnection.getConnection();
	}

	public boolean insertProduct(Product product) throws SQLException {

		String sql = "INSERT INTO products (name, info, price, iva, stock ) VALUES (?, ?, ?, ?, ?)";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, product.getName());
		statement.setString(2, product.getInfo());
		statement.setFloat(3, product.getPrice());
		statement.setInt(4, product.getIva());
		statement.setInt(5, product.getStock());

		boolean rowInserted = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowInserted;
	}

	public List<Product> listAllProducts() throws SQLException {

		List<Product> listProducts = new ArrayList<>();
		String sql = "SELECT * FROM products";
		connection = getConnection();

		connection = DbConnection.getConnection();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String info = resultSet.getString("info");
			float price = resultSet.getFloat("price");
			int iva = resultSet.getInt("iva");
			int stock = resultSet.getInt("stock");

			Product product = new Product(id, name, info, price, iva, stock);
			listProducts.add(product);
		}

		resultSet.close();
		statement.close();
		connection.close();

		Collections.sort(listProducts);

		if (listProducts.size() == 0) {
			Product product = new Product(1, "test", "test", 10, 21, 15);
			listProducts.add(product);
		}

		return listProducts;
	}

	public boolean deleteProduct(Product product) throws SQLException {

		String sql = "DELETE FROM products where id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, product.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {

		String sql = "UPDATE products SET name = ?, info = ?, price = ?, iva = ?, stock = ?";
		sql += " WHERE id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, product.getName());
		statement.setString(2, product.getInfo());
		statement.setFloat(3, product.getPrice());
		statement.setInt(4, product.getIva());
		statement.setInt(5, product.getStock());
		statement.setInt(6, product.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;

		statement.close();
		connection.close();

		return rowUpdated;
	}

	public Product getProduct(int id) throws SQLException {

		Product product = null;
		String sql = "SELECT * FROM products WHERE id = ?";
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String name = resultSet.getString("name");
			String info = resultSet.getString("info");
			float price = resultSet.getFloat("price");
			int iva = resultSet.getInt("iva");
			int stock = resultSet.getInt("stock");

			product = new Product(id, name, info, price, iva, stock);
		}

		else {
			product = new Product(1, "", "", 0, 0, 0);
		}

		resultSet.close();
		statement.close();

		return product;
	}
}
