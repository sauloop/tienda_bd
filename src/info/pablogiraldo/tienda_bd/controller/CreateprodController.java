package info.pablogiraldo.tienda_bd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.pablogiraldo.tienda_bd.dao.ProductDAO;
import info.pablogiraldo.tienda_bd.model.Product;

/**
 * Servlet implementation class CreateprodController
 */
@WebServlet("/createprod")
public class CreateprodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

	String name;
	String info;
	float price;
	int iva;
	int stock;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateprodController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()
				&& request.getParameter("info") != null && !request.getParameter("info").isEmpty()) {

			name = request.getParameter("name");
			info = request.getParameter("info");
			price = Float.parseFloat(request.getParameter("price"));
			iva = Integer.parseInt(request.getParameter("iva"));
			stock = Integer.parseInt(request.getParameter("stock"));

			Product newProduct = new Product(name, info, price, iva, stock);

			try {

				productDAO.insertProduct(newProduct);

			} catch (SQLException ex) {
				throw new ServletException(ex);
			}

//			response.sendRedirect(request.getContextPath());

			request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		}

		else {
//			response.sendRedirect(request.getContextPath());

			request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		}
	}

}
