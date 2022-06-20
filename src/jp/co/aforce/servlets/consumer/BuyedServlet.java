package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.MyBasketInfoBean;
import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.CartDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class BuyedServlet
 */
@WebServlet("/buyedServlet")
public class BuyedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyedServlet() {
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
		HttpSession session = request.getSession();
		request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
		CartDAO cartDAO = new CartDAO();
		RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
		try {
			cartDAO.buyItems((MyBasketInfoBean)session.getAttribute("myBasket"), roleBean.getId());
			session.setAttribute("cartMessage", MessageParameter.BUY_CART_COMPLETE);
			request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
			response.sendRedirect("/ShoppingSite/views/consumer/consumer_cart.jsp");
		} catch (Exception e) {
			session.setAttribute("cartMessage", MessageParameter.SYSTEM_ERROR);
			request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
			response.sendRedirect("/ShoppingSite/views/consumer/consumer_cart.jsp");
			e.printStackTrace();
		}
	}

}
