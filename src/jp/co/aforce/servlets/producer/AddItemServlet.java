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


@WebServlet("/addItemServlet")
@MultipartConfig
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public AddItemServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=" + "UTF-8");
		HttpSession session = request.getSession();
		RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
		if(roleBean == null || !roleBean.getRole().equals("producer")) {
			request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
		} else {
			String itemName = request.getParameter("item_name");
			String origin = request.getParameter("origin");
			String unit = request.getParameter("unit");
			String price = request.getParameter("price");
			String explanation = request.getParameter("explanation");

			NullCheck check = new NullCheck();
			String c = check.itemCheck(itemName, origin, unit, price, explanation);
			if(!c.equals("")) {
				session.setAttribute("addItemMessage", c + MessageParameter.MESSAGE);
				request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
			} else {
				Part part = request.getPart("image");
				if(part.getSize() == 0) {
					session.setAttribute("addItemMessage", ItemInfoParameter.IMAGE_STR + MessageParameter.MESSAGE);
					request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
				} else {
					if(part.getContentType().equals("image/png") || part.getContentType().equals("image/jpeg")) {
						int priceInt = Integer.parseInt(price);
						String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						String path = getServletContext().getRealPath("/img");
						//画像の保存
						part.write(path+File.separator+fileName);
						RoleBean bean = (RoleBean)session.getAttribute("userInfo");
						//beanに情報を投げる
						ItemBean itemBean = new ItemBean(null, bean.getId(), itemName, origin, unit, priceInt, explanation, fileName, "enable");
						//DAOでDBに登録
						ItemDAO itemDAO = new ItemDAO();
						try {
							itemDAO.addItem(itemBean);
							session.setAttribute("addItemMessage", MessageParameter.ADD_COMPLETE);
							request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
						} catch (Exception e) {
							session.setAttribute("addItemMessage", MessageParameter.SYSTEM_ERROR);
							request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
							e.printStackTrace();
						}
					} else {
						session.setAttribute("addItemMessage", ItemInfoParameter.IMAGE_CONTENT_TYPE_STR);
						request.getRequestDispatcher("/views/producer/addItem.jsp").forward(request, response);
					}


				}
			}
		}





	}

}
