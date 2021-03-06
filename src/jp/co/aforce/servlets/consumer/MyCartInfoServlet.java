package jp.co.aforce.servlets.consumer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.CartDAO;

//遷移先が存在せずインクルードでのみ呼び出されることを想定したクラスである。
//また現在のカート情報を逐次更新してセッションに保持することが目的である
@WebServlet("/myCartInfoServlet")
public class MyCartInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public MyCartInfoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean)session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("consumer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String userId = roleBean.getId();
			CartDAO dao = new CartDAO();
			try {
				session.setAttribute("myBasket", dao.findMyBasketByUserId(userId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

}
