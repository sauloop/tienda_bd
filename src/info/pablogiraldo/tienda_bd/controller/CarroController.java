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
import info.pablogiraldo.tienda_bd.common.Data;

/**
 * Servlet implementation class CarroController
 */
@WebServlet("/carro")
public class CarroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

	Product prod;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarroController() {
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
			int index = Integer.parseInt(request.getParameter("id"));

			try {

				prod = productDAO.getProduct(index);

			} catch (SQLException ex) {
				throw new ServletException(ex);
			}

			Data.carro.add(prod);

//			response.sendRedirect(request.getContextPath() + "?mensaje=" + prod.getName() + " comprado.");

			response.sendRedirect("?mensaje=" + prod.getName() + " comprado.");
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
