package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.CartDAO;
import jp.co.aforce.parameters.MessageParameter;


@WebServlet("/updateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateCartServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("consumer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			int itemId = Integer.parseInt(request.getParameter("updateItem"));
			String quantity = request.getParameter("quantity");
			if(!quantity.equals("")) {
				int quantityInt = Integer.parseInt(quantity);
				CartDAO cartDAO = new CartDAO();
				try {
					cartDAO.updateCart(itemId, quantityInt);
					request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
					session.setAttribute("cartMessage", MessageParameter.UPDATE_COMPLETE);
					request.getRequestDispatcher("/views/consumer/consumer_cart.jsp").forward(request, response);
				} catch (Exception e) {
					session.setAttribute("cartMessage", MessageParameter.UPDATE_ERROR);
					request.getRequestDispatcher("/views/consumer/consumer_cart.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
		}

	}

}
