package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CartInfoBean implements Serializable {

	private ArrayList<CartBean> cartBeans;

	public CartInfoBean() {
		cartBeans = new ArrayList<CartBean>();
	}

	public void addCart(CartBean obj) {
		cartBeans.add(obj);
	}

	public int getArraySize() {
		return cartBeans.size();
	}

	public ArrayList<CartBean> getCartBeanArray() {
		return cartBeans;
	}

	public void setCartBeanArray(ArrayList<CartBean> cartBeans) {
		this.cartBeans = cartBeans;
	}

}
