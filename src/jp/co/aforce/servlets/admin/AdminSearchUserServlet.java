package jp.co.aforce.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.UserDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class AdminSearchUserServlet
 */
//doGetでkeywordを受け取りユーザ検索する。
@WebServlet("/adminSearchUserServlet")
public class AdminSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			String userId;
			try {
				userId = request.getParameter("userId");
				if(userId == null) {
					userId = "";
				}
			} catch (Exception e) {
				userId = "";
			}
			UserDAO userDAO = new UserDAO();
			try {
				session.setAttribute("userInfoBean", userDAO.searchUserByAllName(userId));
				session.setAttribute("userId", userId);
				response.sendRedirect("/ShoppingSite/views/admin/adminUserSearch.jsp");
			} catch (Exception e) {
				session.setAttribute("adminSearchUserMessage", MessageParameter.SYSTEM_ERROR);
				response.sendRedirect("/ShoppingSite/views/admin/adminUserSearch.jsp");
				e.printStackTrace();
			}
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
