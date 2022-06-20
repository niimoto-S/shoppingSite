package jp.co.aforce.servlets.producer;

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
 * Servlet implementation class DeleteItemServlet
 */
@WebServlet("/deleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemServlet() {
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
		int itemId = Integer.parseInt(request.getParameter("deleteItem"));
		ItemDAO itemDAO = new ItemDAO();
		try {
			itemDAO.deleteItemById(itemId);
			session.setAttribute("searchItemMessage", MessageParameter.DELETE_COMPLETE);
//			response.sendRedirect("/ShoppingSite/views/producer/search_item.jsp");
			request.getRequestDispatcher("/searchItemServlet").forward(request, response);
		} catch (Exception e) {
			session.setAttribute("searchItemMessage", MessageParameter.SYSTEM_ERROR);
			response.sendRedirect("/ShoppingSite/views/producer/search_item.jsp");
			e.printStackTrace();
		}

	}

}
