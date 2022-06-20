package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyedInfoBean implements Serializable {

	private ArrayList<BuyedBean> buyedBeans;

	public BuyedInfoBean() {
		buyedBeans = new ArrayList<BuyedBean>();
	}

	public void addItem(BuyedBean obj) {
		buyedBeans.add(obj);
	}

	public int getArraySize() {
		return buyedBeans.size();
	}

	public ArrayList<BuyedBean> getBuyedBeanArray() {
		return buyedBeans;
	}

	public void setBuyedBeanArray(ArrayList<BuyedBean> buyedBeans) {
		this.buyedBeans = buyedBeans;
	}

}
