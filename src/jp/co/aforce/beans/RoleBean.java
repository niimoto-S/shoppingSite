package jp.co.aforce.beans;

import java.io.Serializable;

public class RoleBean implements Serializable {

	private String id;
	private String password;
	private String role;
	private String accountStatus;

	public RoleBean() {}

	public RoleBean(
			String id,
			String password,
			String role,
			String accountStatus
			) {
		this.id = id;
		this.password = password;
		this.role = role;
		this.accountStatus = accountStatus;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
