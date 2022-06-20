package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class MyBasketInfoBean implements Serializable {

	private ArrayList<MyBasketBean> myBasketBeans;

	public MyBasketInfoBean() {
		myBasketBeans = new ArrayList<MyBasketBean>();
	}

	public void addMyBasket(MyBasketBean obj) {
		myBasketBeans.add(obj);
	}

	public int getArraySize() {
		return myBasketBeans.size();
	}

	public ArrayList<MyBasketBean> getMyBasketBeanArray() {
		return myBasketBeans;
	}

	public void setMyBasketBeanArray(ArrayList<MyBasketBean> myBasketBeans) {
		this.myBasketBeans = myBasketBeans;
	}

}
