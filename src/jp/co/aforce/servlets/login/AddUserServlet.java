package jp.co.aforce.servlets.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.models.LoginDAO;
import jp.co.aforce.mySQL.MySQLError;
import jp.co.aforce.parameters.MessageParameter;
import jp.co.aforce.util.NullCheck;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AddUserServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/addUser.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();

		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String sex = request.getParameter("sex");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String role = request.getParameter("select");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String phoneNumber = request.getParameter("phone_number");
		String mailAddress = request.getParameter("mail_address");

		NullCheck check = new NullCheck();
		String c = check.addUserCheck(lastName, firstName, sex, id, password, role, year, month, day, phoneNumber, mailAddress);

		if(!c.equals("")) {
			session.setAttribute("addUserMessage", c + MessageParameter.MESSAGE);
			request.getRequestDispatcher("/views/login/addUser.jsp").forward(request, response);
		} else {
			//Beanに情報を
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			int dayInt = Integer.parseInt(day);
			UserBean userBean = new UserBean(id, lastName, firstName, sex, yearInt, monthInt, dayInt, phoneNumber, mailAddress);
			RoleBean roleBean = new RoleBean(id, password, role, "enable");
			LoginDAO loginDAO = new LoginDAO();
			try {
				loginDAO.addUser(userBean, roleBean);
				session.setAttribute("addUserMessage", MessageParameter.ADD_COMPLETE);
				request.getRequestDispatcher("/views/login/addUser.jsp").forward(request, response);
			} catch (SQLException e) {
				session.setAttribute("addUserMessage", MySQLError.getMessage(e.getErrorCode()));
				e.printStackTrace();
				request.getRequestDispatcher("/views/login/addUser.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("addUserMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/login/addUser.jsp").forward(request, response);
				e.printStackTrace();
			}
			//データベースに情報を入れる

		}


	}

}
