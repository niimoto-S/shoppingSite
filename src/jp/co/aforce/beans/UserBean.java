package jp.co.aforce.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	private String id;
	private String lastName;
	private String firstName;
	private String sex;
	private int year;
	private int month;
	private int day;
	private String phoneNumber;
	private String mailAddress;

	public UserBean(
			String id,
			String lastName,
			String firstName,
			String sex,
			int year,
			int month,
			int day,
			String phoneNumber,
			String mailAddress
			) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.sex = sex;
		this.year = year;
		this.month = month;
		this.day = day;
		this.phoneNumber = phoneNumber;
		this.mailAddress = mailAddress;
	}

	public UserBean() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}
