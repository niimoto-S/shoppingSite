package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.CartBean;
import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.CartDAO;
import jp.co.aforce.models.ItemDAO;
import jp.co.aforce.parameters.MessageParameter;
import jp.co.aforce.util.NullCheck;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		String  itemId = request.getParameter("addItem");
		String quantity = request.getParameter("quantity");
		RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
		String consumerId = roleBean.getId();
		ItemDAO itemDAO = new ItemDAO();

		NullCheck check = new NullCheck();
		String c = check.cart(itemId, quantity);
		if(!c.equals("")) {
			session.setAttribute("searchItemMessage", c + MessageParameter.MESSAGE);
			response.sendRedirect("/ShoppingSite/views/consumer/consumer_search_item.jsp");
		} else {
			int itemIdInt = Integer.parseInt(itemId);
			int quantityInt = Integer.parseInt(quantity);
			try {
				String producerId = itemDAO.findProducerIdByItemId(itemIdInt);
				CartBean cartBean = new CartBean(itemIdInt, producerId, consumerId, quantityInt);
				CartDAO dao = new CartDAO();
				dao.addCart(cartBean);
				request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
				session.setAttribute("searchItemMessage", MessageParameter.ADD_CART_COMPLETE);
				response.sendRedirect("/ShoppingSite/views/consumer/consumer_search_item.jsp");

			} catch (Exception e) {
				session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
				response.sendRedirect("/ShoppingSite/views/consumer/consumer_search_item.jsp");
				e.printStackTrace();
			}
		}


	}

}
