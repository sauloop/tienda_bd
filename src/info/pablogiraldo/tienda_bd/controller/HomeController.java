package info.pablogiraldo.tienda_bd.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import info.pablogiraldo.tienda_bd.common.Data;
import info.pablogiraldo.tienda_bd.config.DbConn;

//import org.apache.commons.codec.binary.Base64;

import info.pablogiraldo.tienda_bd.dao.ProductDAO;
import info.pablogiraldo.tienda_bd.model.Product;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Product> listProducts;

	private ProductDAO productDAO;

	private String rutaConfig;

	private Properties properties;
	private Properties configProperties;
	private Properties envProperties;
	private InputStream is;

	private String env;

	private String rutaIdioma;

	private String rutaEnv;

	private String idioma;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		properties = new Properties();
		configProperties = new Properties();
		envProperties = new Properties();
		is = null;
		productDAO = new ProductDAO();
		listProducts = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		rutaConfig = request.getServletContext().getRealPath("/config/config.properties");

		configProperties = getProperties(rutaConfig);

		env = configProperties.getProperty("env");

		if (env.equals("dev")) {
			rutaEnv = request.getServletContext().getRealPath("/config/dev.properties");

		} else {
			rutaEnv = request.getServletContext().getRealPath("/config/prod.properties");
		}

		if (DbConn.envProperties == null) {
			envProperties = getProperties(rutaEnv);
			DbConn.envProperties = envProperties;
		}

		if (request.getSession().getAttribute("idioma") == null) {
			request.getSession().setAttribute("idioma", "es");
		}

		if (request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()) {
			idioma = request.getParameter("idioma");

			request.getSession().setAttribute("idioma", idioma);

		}

		if (request.getSession().getAttribute("idioma").equals("es")) {
			rutaIdioma = request.getServletContext().getRealPath("/lan/es.properties");
		} else {
			rutaIdioma = request.getServletContext().getRealPath("/lan/en.properties");
		}

		Properties proplan = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream(rutaIdioma);
			proplan.load(is);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		request.setAttribute("proplan", proplan);

		if (request.getParameter("mensaje") != null) {
			request.setAttribute("mensaje", request.getParameter("mensaje"));
		}

		try {

			listProducts(request, response);

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Product> listProducts = productDAO.listAllProducts();

		request.setAttribute("products", listProducts);

		request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
	}

	private Properties getProperties(String ruta) {

		try {
			is = new FileInputStream(ruta);
			properties.load(is);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		return properties;
	}
}
