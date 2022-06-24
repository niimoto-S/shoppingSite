package jp.co.aforce.servlets.producer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jp.co.aforce.beans.ItemBean;
import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.models.ItemDAO;
import jp.co.aforce.parameters.ItemInfoParameter;
import jp.co.aforce.parameters.MessageParameter;
import jp.co.aforce.util.NullCheck;

/**
 * Servlet implementation class UpdateItem2Servlet
 */
@WebServlet("/updateItem2Servlet")
@MultipartConfig
public class UpdateItem2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItem2Servlet() {
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
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			response.sendRedirect("/ShoppingSite/views/login/login.jsp");
		} else {
			String itemName = request.getParameter("item_name");
			String origin = request.getParameter("origin");
			String unit = request.getParameter("unit");
			String price = request.getParameter("price");
			String explanation = request.getParameter("explanation");
			//エラー発生時、値を所持したまま戻るため、値を入れたままにする処理
			ItemBean itemBean = (ItemBean) session.getAttribute("updateItemBean");
			itemBean.setItemName(itemName);
			itemBean.setOrigin(origin);
			itemBean.setUnit(unit);
			try {
				itemBean.setPrice(Integer.parseInt(price));
			} catch (Exception e) {itemBean.setPrice(0);}
			itemBean.setExplanation(explanation);

			NullCheck check = new NullCheck();
			String c = check.itemCheck(itemName, origin, unit, price, explanation);
			if(!c.equals("")) {
				session.setAttribute("updateItemMessage", c + MessageParameter.MESSAGE);
				session.setAttribute("updateItemBean", itemBean);
				response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
			} else {
				Part part = request.getPart("image");
				if(part.getSize() == 0) {
					session.setAttribute("updateItemMessage", ItemInfoParameter.IMAGE_STR + MessageParameter.MESSAGE);
					session.setAttribute("updateItemBean", itemBean);
					response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
				} else {
					if(part.getContentType().equals("image/png") || part.getContentType().equals("image/jpeg")) {
						String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						String path = getServletContext().getRealPath("/img");
						//画像の保存
						part.write(path+File.separator+fileName);
						//beanに情報を投げる
						itemBean.setImageName(fileName);
						//DAOでDBに登録
						ItemDAO itemDAO = new ItemDAO();
						try {
							itemDAO.updateItem(itemBean);
							session.setAttribute("updateItemMessage", MessageParameter.UPDATE_COMPLETE);
							response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
						} catch (Exception e) {
							session.setAttribute("updateItemMessage", MessageParameter.SYSTEM_ERROR);
							session.setAttribute("updateItemBean", itemBean);
							response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
							e.printStackTrace();
						}
					} else {
						session.setAttribute("updateItemMessage", ItemInfoParameter.IMAGE_CONTENT_TYPE_STR);
						session.setAttribute("updateItemBean", itemBean);
						response.sendRedirect("/ShoppingSite/views/producer/updateItem.jsp");
					}


				}
			}
		}


	}

}
