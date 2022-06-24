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

/**
 * Servlet implementation class BuyedSearchServlet
 */
@WebServlet("/buyedSearchServlet")
public class BuyedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyedSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("consumer")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			String consumerId = roleBean.getId();
			BuyedDAO buyedDAO = new BuyedDAO();
			try {
				session.setAttribute("buyedInfoBeanEx", buyedDAO.findSalesbyUserId(consumerId));
				response.sendRedirect("/ShoppingSite/views/consumer/consumer_buyed.jsp");
			} catch (Exception e) {
				session.setAttribute("buyedMessage", MessageParameter.SYSTEM_ERROR);
				response.sendRedirect("/ShoppingSite/views/consumer/consumer_buyed.jsp");
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
