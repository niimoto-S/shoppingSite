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


@WebServlet("/buyedServlet")
public class BuyedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BuyedServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("consumer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
			CartDAO cartDAO = new CartDAO();
			try {
				cartDAO.buyItems((MyBasketInfoBean)session.getAttribute("myBasket"), roleBean.getId());
				session.setAttribute("cartMessage", MessageParameter.BUY_CART_COMPLETE);
				request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
				request.getRequestDispatcher("/views/consumer/consumer_cart.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("cartMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
				request.getRequestDispatcher("/views/consumer/consumer_cart.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}

}
