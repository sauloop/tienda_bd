package info.pablogiraldo.tienda_bd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import info.pablogiraldo.tienda_bd.dao.UserDAO;
import info.pablogiraldo.tienda_bd.model.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	String name;
	String pass;
	boolean error;
	User usr;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDAO = new UserDAO();
		usr = new User("", "");
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

		error = false;

		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()
				&& request.getParameter("pass") != null && !request.getParameter("pass").isEmpty()) {

			name = request.getParameter("name");
			pass = request.getParameter("pass");

			try {
				usr = userDAO.getUser(name);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (!usr.getName().equals("")) {
				error = true;

				request.setAttribute("usr", name);

				request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
			}

			if (!error) {
				User newUser = new User(name, pass);

				try {
					userDAO.insertUser(newUser);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				response.sendRedirect(request.getContextPath() + "/login");

			}

		}

		else

		{
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		}
	}

}
