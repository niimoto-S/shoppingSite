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


//doGetでkeywordを受け取り商品検索をする。
@WebServlet("/adminSearchItemServlet")
public class AdminSearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchItemServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
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
				request.getRequestDispatcher("/views/admin/adminItemSearch.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("adminSearchItemMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/admin/adminItemSearch.jsp").forward(request, response);
				e.printStackTrace();
			}
		}



	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
