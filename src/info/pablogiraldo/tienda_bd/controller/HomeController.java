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

	String rutaIdioma;

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
		productDAO = new ProductDAO();
		listProducts = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("idioma") == null) {
			request.getSession().setAttribute("idioma", "es");
		}

		if (request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()) {
			String idioma = request.getParameter("idioma");

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

		if (request.getParameter("mensaje") != null) {
			request.setAttribute("mensaje", request.getParameter("mensaje"));
		}

		try {

			listProducts = productDAO.listAllProducts();

		} catch (SQLException ex) {
			throw new ServletException(ex);
//			response.sendRedirect(request.getContextPath() + "home");
		}

		request.setAttribute("proplan", proplan);

		request.setAttribute("products", listProducts);

		request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);

//		try {
//
//			listProducts(request, response);
//
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
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

//	private void listProducts(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//
//		List<Product> listProducts = productDAO.listAllProducts();
//
//		request.setAttribute("products", listProducts);
//
//		request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
//	}
}
