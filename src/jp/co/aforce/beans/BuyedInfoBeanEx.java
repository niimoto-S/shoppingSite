package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class BuyedInfoBeanEx implements Serializable {

	private ArrayList<BuyedBeanEx> buyedBeanExs;

	public BuyedInfoBeanEx() {
		buyedBeanExs = new ArrayList<BuyedBeanEx>();
	}

	public void addItem(BuyedBeanEx obj) {
		buyedBeanExs.add(obj);
	}

	public int getArraySize() {
		return buyedBeanExs.size();
	}

	public ArrayList<BuyedBeanEx> getBuyedBeanExArray() {
		return buyedBeanExs;
	}

	public void setBuyedBeanExArray(ArrayList<BuyedBeanEx> buyedBeanExs) {
		this.buyedBeanExs = buyedBeanExs;
	}

}
