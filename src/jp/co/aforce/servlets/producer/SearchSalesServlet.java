package jp.co.aforce.servlets.producer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.BuyedDAO;
import jp.co.aforce.parameters.MessageParameter;


@WebServlet("/searchSalesServlet")
public class SearchSalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SearchSalesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			BuyedDAO dao = new BuyedDAO();
			try {
				session.setAttribute("salesBean", dao.findAllByProducerId(roleBean.getId()));
				request.getRequestDispatcher("/views/producer/sales.jsp").forward(request, response);
			} catch (Exception e) {
				session.setAttribute("salesMessage", MessageParameter.SYSTEM_ERROR);
				request.getRequestDispatcher("/views/producer/sales.jsp").forward(request, response);
				e.printStackTrace();
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
