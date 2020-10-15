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
 * Servlet implementation class DeleteprodController
 */
@WebServlet("/deleteprod")
public class DeleteprodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

	int id;

	Product prod;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteprodController() {
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

			int id = Integer.parseInt(request.getParameter("id"));

			try {

				prod = productDAO.getProduct(id);

			} catch (SQLException ex) {
				throw new ServletException(ex);
			}

			if (prod != null) {
				try {

					productDAO.deleteProduct(prod);

				} catch (SQLException ex) {
					throw new ServletException(ex);
				}

			}

//			response.sendRedirect(request.getContextPath());

			request.getRequestDispatcher("").forward(request, response);
		}

		else {
//			response.sendRedirect(request.getContextPath());

			request.getRequestDispatcher("").forward(request, response);
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
