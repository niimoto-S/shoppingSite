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


//doGetでkeywordを受け取りユーザ検索する。
@WebServlet("/adminSearchUserServlet")
public class AdminSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchUserServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
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
				request.getRequestDispatcher("/views/admin/adminUserSearch.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("adminSearchUserMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/admin/adminUserSearch.jsp").forward(request, response);
				e.printStackTrace();
			}
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
