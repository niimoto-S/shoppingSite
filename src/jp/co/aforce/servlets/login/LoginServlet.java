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


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		NullCheck check = new NullCheck();
		String c = check.loginCheck(id, password);
		if(!c.equals("")) {
			session.setAttribute("loginMessage", c + MessageParameter.MESSAGE);
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
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
					request.getRequestDispatcher("/searchItemServlet").forward(request, response);
				//管理者
				} else if(roleBean.getRole().equals("admin") && roleBean.getAccountStatus().equals("enable")) {
					session.setAttribute("userInfo", roleBean);
					session.setAttribute("userName", name);
					request.getRequestDispatcher("/adminSearchItemServlet").forward(request, response);
				//アカウント削除
				} else {
					session.setAttribute("loginMessage", MessageParameter.DISABLE_ERROR);
					request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				session.setAttribute("loginMessage", MessageParameter.LOGIN_ERROR);
				request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}

}
