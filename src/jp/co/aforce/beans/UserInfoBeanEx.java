package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfoBeanEx implements Serializable{

	private ArrayList<UserBeanEx> userBeanExs;

	public UserInfoBeanEx() {
		userBeanExs = new ArrayList<UserBeanEx>();
	}

	public void addUser(UserBeanEx obj) {
		userBeanExs.add(obj);
	}

	public int getArraySize() {
		return userBeanExs.size();
	}

	public ArrayList<UserBeanEx> getUserBeanExArray() {
		return userBeanExs;
	}

	public void setUserBeanExArray(ArrayList<UserBeanEx> userBeanExs) {
		this.userBeanExs = userBeanExs;
	}

}
