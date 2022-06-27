package jp.co.aforce.servlets.consumer;

import java.io.IOException;
import java.sql.SQLException;

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


@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AddCartServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("consumer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String  itemId = request.getParameter("addItem");
			String quantity = request.getParameter("quantity");
			RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
			String consumerId = roleBean.getId();
			ItemDAO itemDAO = new ItemDAO();

			NullCheck check = new NullCheck();
			String c = check.cart(itemId, quantity);
			if(!c.equals("")) {
				session.setAttribute("searchItemMessage", c + MessageParameter.MESSAGE);
				request.getRequestDispatcher("/views/consumer/consumer_search_item.jsp").forward(request, response);
			} else {
				int itemIdInt = Integer.parseInt(itemId);
				int quantityInt = Integer.parseInt(quantity);
				try {
					String producerId = itemDAO.findProducerIdByItemId(itemIdInt);
					CartBean cartBean = new CartBean(itemIdInt, producerId, consumerId, quantityInt);
					CartDAO dao = new CartDAO();
					if(dao.check(itemIdInt, bean.getId()) == 0) {
						dao.addCart(cartBean);
						request.getRequestDispatcher("/myCartInfoServlet").include(request, response);
						session.setAttribute("searchItemMessage", MessageParameter.ADD_CART_COMPLETE);
						request.getRequestDispatcher("/views/consumer/consumer_search_item.jsp").forward(request, response);
					} else {
						session.setAttribute("searchItemMessage", MessageParameter.ADD_CART_ERROR);
						request.getRequestDispatcher("/views/consumer/consumer_search_item.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					if(e.getErrorCode() == 1062) {
						session.setAttribute("searchItemMessage", MessageParameter.ADD_CART_ERROR);
						request.getRequestDispatcher("/views/consumer/consumer_search_item.jsp").forward(request, response);
					} else {
						e.getNextException();
					}
				} catch (Exception e) {
					session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
					request.getRequestDispatcher("/views/consumer/consumer_search_item.jsp").forward(request, response);
					e.printStackTrace();
				}
			}
		}



	}

}
