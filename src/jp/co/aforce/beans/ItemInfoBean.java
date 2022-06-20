package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemInfoBean implements Serializable {

	private ArrayList<ItemBean> itemBeans;

	public ItemInfoBean() {
		itemBeans = new ArrayList<ItemBean>();
	}

	public void addItem(ItemBean obj) {
		itemBeans.add(obj);
	}

	public int getArraySize() {
		return itemBeans.size();
	}

	public ArrayList<ItemBean> getItemBeanArray() {
		return itemBeans;
	}

	public void setItemBeanArray(ArrayList<ItemBean> itemBeans) {
		this.itemBeans = itemBeans;
	}


}
