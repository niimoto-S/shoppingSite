package jp.co.aforce.servlets.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.LoginDAO;
import jp.co.aforce.parameters.MessageParameter;
import jp.co.aforce.util.NullCheck;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		NullCheck check = new NullCheck();
		String c = check.loginCheck(id, password);
		if(!c.equals("")) {
			session.setAttribute("loginMessage", c + MessageParameter.MESSAGE);
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			LoginDAO loginDAO = new LoginDAO();
			RoleBean roleBean = new RoleBean();
			try {
				roleBean = loginDAO.login(id, password);
				String name = loginDAO.getUserName(id);
				//消費者
				if(roleBean.getRole().equals("consumer") && roleBean.getAccountStatus().equals("enable")) {
					session.setAttribute("userInfo", roleBean);
					session.setAttribute("userName", name);
					request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
					request.getRequestDispatcher("/searchAllServlet").forward(request, response);
				//生産者
				} else if(roleBean.getRole().equals("producer") && roleBean.getAccountStatus().equals("enable")) {
					session.setAttribute("userInfo", roleBean);
					session.setAttribute("userName", name);
					response.sendRedirect("/ShoppingSite/views/producer/producer_menu.jsp");
				//管理者
				} else if(roleBean.getRole().equals("admin") && roleBean.getAccountStatus().equals("enable")) {
					session.setAttribute("userInfo", roleBean);
					session.setAttribute("userName", name);
					response.sendRedirect("/ShoppingSite/views/admin/admin_menu.jsp");
				//アカウント削除
				} else {
					session.setAttribute("loginMessage", MessageParameter.DISABLE_ERROR);
					response.sendRedirect("/ShoppingSite/views/login/login.jsp");
				}
			} catch (Exception e) {
				session.setAttribute("loginMessage", MessageParameter.LOGIN_ERROR);
				response.sendRedirect("/ShoppingSite/views/login/login.jsp");
				e.printStackTrace();
			}
		}

	}

}
