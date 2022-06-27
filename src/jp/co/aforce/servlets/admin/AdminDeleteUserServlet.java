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


@WebServlet("/adminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AdminDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/adminUserSearch.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String userId = request.getParameter("deleteUser");
			UserDAO userDAO = new UserDAO();
			String searchId;
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
				request.getRequestDispatcher("/views/admin/adminUserSearch.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("adminSearchUserMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/admin/adminUserSearch.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}

}
