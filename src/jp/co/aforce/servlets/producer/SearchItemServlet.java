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


@WebServlet("/searchItemServlet")
public class SearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SearchItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			String keyword = "";
			String userId = roleBean.getId();
			try {
				keyword = request.getParameter("itemWord");
				if(keyword == null || keyword.equals("")) {
					keyword = "";
				}
			} catch (Exception e) {}

			ItemDAO itemDAO = new ItemDAO();

			try {
				session.setAttribute("itemInfoBean", itemDAO.searchItem(keyword, userId));
				request.getRequestDispatcher("/views/producer/search_item.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/producer/search_item.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
