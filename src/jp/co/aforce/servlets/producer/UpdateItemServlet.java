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


@WebServlet("/updateItemServlet")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		int itemId = Integer.parseInt(request.getParameter("updateItem"));
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			ItemDAO itemDAO = new ItemDAO();
			try {
				session.setAttribute("updateItemBean", itemDAO.updateItemById(itemId));
				request.getRequestDispatcher("/views/producer/updateItem.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/producer/search_item.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
	}
}
