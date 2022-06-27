package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.BuyedDAO;
import jp.co.aforce.parameters.MessageParameter;


@WebServlet("/buyedSearchServlet")
public class BuyedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BuyedSearchServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("consumer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String consumerId = roleBean.getId();
			BuyedDAO buyedDAO = new BuyedDAO();
			try {
				session.setAttribute("buyedInfoBeanEx", buyedDAO.findSalesbyUserId(consumerId));
				request.getRequestDispatcher("/views/consumer/consumer_buyed.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("buyedMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/consumer/consumer_buyed.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}

}
