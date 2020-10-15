package info.pablogiraldo.tienda_bd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
//import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.pablogiraldo.tienda_bd.dao.ProductDAO;
import info.pablogiraldo.tienda_bd.model.Product;

/**
 * Servlet implementation class UpdateprodController
 */
@WebServlet("/updateprod")
public class UpdateprodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

	int id;
	String name;
	String info;
	float price;
	int iva;
	int stock;

	Product prod;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateprodController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
		prod = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {

			try {

				prod = productDAO.getProduct(Integer.parseInt(request.getParameter("id")));

			} catch (SQLException ex) {
				throw new ServletException(ex);
			}

			request.setAttribute("prod", prod);

			request.getRequestDispatcher("/jsp/updateprod.jsp").forward(request, response);
		}

		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()
				&& request.getParameter("info") != null && !request.getParameter("info").isEmpty()) {

			id = Integer.parseInt(request.getParameter("ident"));
			name = request.getParameter("name");
			info = request.getParameter("info");
			price = Float.parseFloat(request.getParameter("price"));
			iva = Integer.parseInt(request.getParameter("iva"));
			stock = Integer.parseInt(request.getParameter("stock"));

			Product updatedProduct = new Product(id, name, info, price, iva, stock);

			try {

				productDAO.updateProduct(updatedProduct);

			} catch (SQLException ex) {
				throw new ServletException(ex);
			}

			response.sendRedirect(request.getContextPath());
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

}
