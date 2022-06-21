package jp.co.aforce.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.models.ItemDAO;
import jp.co.aforce.parameters.MessageParameter;

/**
 * Servlet implementation class AdminDeleteItemServlet
 */
@WebServlet("/adminDeleteItemServlet")
public class AdminDeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteItemServlet() {
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
		String itemId = request.getParameter("deleteItem");
		ItemDAO dao = new ItemDAO();
		try {
			String itemName = session.getAttribute("itemName").toString();
			dao.deleteItemById(Integer.parseInt(itemId));
			session.setAttribute("itemInfoBean", dao.adminSearchItem(itemName));
			response.sendRedirect("/ShoppingSite/views/admin/adminItemSearch.jsp");
		} catch (Exception e) {
			session.setAttribute("adminSearchItemMessage", MessageParameter.SYSTEM_ERROR);
			response.sendRedirect("/ShoppingSite/views/admin/adminItemSearch.jsp");
			e.printStackTrace();
		}
	}

}
