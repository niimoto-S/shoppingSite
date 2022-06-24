package jp.co.aforce.servlets.producer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.ItemDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class UpdateItemServlet
 */
@WebServlet("/updateItemServlet")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItemServlet() {
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
		response.setContentType("text/html; charset=" + "UTF-8");
		int itemId = Integer.parseInt(request.getParameter("updateItem"));
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			ItemDAO itemDAO = new ItemDAO();
			try {
				session.setAttribute("updateItemBean", itemDAO.updateItemById(itemId));
				response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
			} catch (Exception e) {
				session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
				response.sendRedirect("/ShoppingSite/views/producer/search_item.jsp");
				e.printStackTrace();
			}
		}
	}
}
