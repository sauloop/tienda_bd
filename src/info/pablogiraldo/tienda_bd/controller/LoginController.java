package info.pablogiraldo.tienda_bd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import info.pablogiraldo.tienda_bd.common.Data;
import info.pablogiraldo.tienda_bd.config.DbConn;
import info.pablogiraldo.tienda_bd.dao.UserDAO;
import info.pablogiraldo.tienda_bd.model.Product;
import info.pablogiraldo.tienda_bd.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	String name;
	String pass;
	User usr;
	String encode_pass;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDAO = new UserDAO();
		usr = new User("", "");
		encode_pass = null;
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

		if (DbConn.envProperties == null) {
			response.sendRedirect(request.getContextPath() + "/home");
		} else {

			if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()
					&& request.getParameter("pass") != null && !request.getParameter("pass").isEmpty()) {

				name = request.getParameter("name");
				pass = request.getParameter("pass");

				encode_pass = getEncodePass(pass);

				usr = getUser(name);

				if (usr.getPass().equals(encode_pass)) {

					Data.carro = new ArrayList<Product>();

					request.getSession().setAttribute("user", name);

					request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);

				} else {
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				}

			}

			else {

				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}

		}

	}

	private User getUser(String name) {

		User user = new User();

		try {
			user = userDAO.getUser(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	private String getEncodePass(String pass) {

		Base64 base64 = new Base64();

		String enc_pass = new String(base64.encode(pass.getBytes()));

		return enc_pass;
	}

}
