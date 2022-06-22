package jp.co.aforce.servlets.admin;

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
 * Servlet implementation class AdminSearchItemServlet
 */
//doGetでkeywordを受け取り商品検索をする。
@WebServlet("/adminSearchItemServlet")
public class AdminSearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			String itemName;
			try {
				itemName = request.getParameter("itemName");
				if(itemName == null) {
					itemName = "";
				}
			} catch (Exception e) {
				itemName = "";
			}
			ItemDAO itemDAO = new ItemDAO();
			try {
				session.setAttribute("itemInfoBean", itemDAO.adminSearchItem(itemName));
				session.setAttribute("itemName", itemName);
				response.sendRedirect("/ShoppingSite/views/admin/adminItemSearch.jsp");
			} catch (Exception e) {
				session.setAttribute("adminSearchItemMessage", MessageParameter.SYSTEM_ERROR);
				response.sendRedirect("/ShoppingSite/views/admin/adminItemSearch.jsp");
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
