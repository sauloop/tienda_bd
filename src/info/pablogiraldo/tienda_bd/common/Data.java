package info.pablogiraldo.tienda_bd.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import info.pablogiraldo.tienda_bd.model.Product;

public class Data {

	public static ArrayList<Product> carro = new ArrayList<Product>();

	private static Properties properties = new Properties();
	private static InputStream is = null;

	private static String rutaProp = "C:\\dev\\eclipse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\tienda_excel_web\\config\\config.properties";

	public static Properties getProperties() {

		try {
			is = new FileInputStream(rutaProp);
			properties.load(is);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		return properties;
	}

}
