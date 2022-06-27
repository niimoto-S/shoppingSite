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


@WebServlet("/adminDeleteItemServlet")
public class AdminDeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AdminDeleteItemServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/adminItemSearch.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean bean = (RoleBean) session.getAttribute("userInfo");
		if(bean == null || !bean.getRole().equals("admin")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String itemId = request.getParameter("deleteItem");
			ItemDAO dao = new ItemDAO();
			try {
				String itemName = session.getAttribute("itemName").toString();
				dao.deleteItemById(Integer.parseInt(itemId));
				session.setAttribute("itemInfoBean", dao.adminSearchItem(itemName));
				session.setAttribute("adminSearchItemMessage", MessageParameter.DELETE_COMPLETE);
				request.getRequestDispatcher("/views/admin/adminItemSearch.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("adminSearchItemMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/admin/adminItemSearch.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}

}
