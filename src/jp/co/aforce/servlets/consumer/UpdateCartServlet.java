package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.models.CartDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/updateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
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
		int itemId = Integer.parseInt(request.getParameter("updateItem"));
		String quantity = request.getParameter("quantity");
		if(!quantity.equals("")) {
			int quantityInt = Integer.parseInt(quantity);
			CartDAO cartDAO = new CartDAO();
			try {
				cartDAO.updateCart(itemId, quantityInt);
				request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
				session.setAttribute("cartMessage", MessageParameter.UPDATE_COMPLETE);
				response.sendRedirect("/ShoppingSite/views/consumer/consumer_cart.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
