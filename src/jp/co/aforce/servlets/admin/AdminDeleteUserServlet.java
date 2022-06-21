package jp.co.aforce.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.models.UserDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class AdminDeleteUserServlet
 */
@WebServlet("/adminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("deleteUser");
		UserDAO userDAO = new UserDAO();
		String searchId;
		HttpSession session = request.getSession();
		try {
			searchId = (String) session.getAttribute("userId");
			if(searchId == null) {
				searchId = "";
			}
		} catch (Exception e) {
			searchId = "";
		}

		try {
			userDAO.deleteUser(userId);
			session.setAttribute("userInfoBean", userDAO.searchUserByAllName(searchId));
			session.setAttribute("adminSearchUserMessage", MessageParameter.DELETE_COMPLETE);
			response.sendRedirect("/ShoppingSite/views/admin/adminUserSearch.jsp");
		} catch (Exception e) {
			session.setAttribute("adminSearchUserMessage", MessageParameter.SYSTEM_ERROR);
			response.sendRedirect("/ShoppingSite/views/admin/adminUserSearch.jsp");
			e.printStackTrace();
		}
	}

}
