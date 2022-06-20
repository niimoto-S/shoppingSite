package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.aforce.beans.RoleBean;
import jp.co.aforce.beans.UserBean;
import jp.co.aforce.parameters.UserInfoParameter;
import jp.co.aforce.parameters.UserRoleParameter;
import jp.co.aforce.util.MD5;

public class LoginDAO extends DAO {

	public void addUser(UserBean userBean, RoleBean roleBean) throws SQLException, Exception {

		Connection con = getConnection();

		String sql = "insert into " + UserInfoParameter.TABLE + " values (?, ? ,? ,? ,? ,? ,? ,? ,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, userBean.getId());
		st.setString(2, userBean.getLastName());
		st.setString(3, userBean.getFirstName());
		st.setString(4, userBean.getSex());
		st.setInt(5, userBean.getYear());
		st.setInt(6, userBean.getMonth());
		st.setInt(7, userBean.getDay());
		st.setString(8, userBean.getPhoneNumber());
		st.setString(9, userBean.getMailAddress());
		st.executeUpdate();

		MD5 md5 = new MD5();
		String hashPass = md5.doHash(roleBean.getId(), roleBean.getPassword());

		sql = "insert into " + UserRoleParameter.TABLE + " values (?, ? ,? ,?)";
		st = con.prepareStatement(sql);
		st.setString(1, roleBean.getId());
		st.setString(2, hashPass);
		st.setString(3, roleBean.getRole());
		st.setString(4, roleBean.getAccountStatus());
		st.executeUpdate();

		st.close();
		con.close();
	}

	public RoleBean login(String id, String password) throws Exception {

		MD5 md5 = new MD5();
		String hashPass = md5.doHash(id, password);

		Connection con = getConnection();

		String sql = "select "
		+ UserInfoParameter.USER_ID + ", "
		+ UserRoleParameter.ROLE + ", "
		+ UserRoleParameter.ACCOUNT_STATUS
		+ " from "
		+ UserRoleParameter.TABLE
		+ " where "
		+ UserInfoParameter.USER_ID + " = ?"
		+ " and "
		+ UserRoleParameter.PASSWORD + " = ?"
		;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, hashPass);

		ResultSet rs = st.executeQuery();

		RoleBean bean = null;

		while(rs.next()) {
			bean = new RoleBean(
					rs.getString(UserInfoParameter.USER_ID),
					"",
					rs.getString(UserRoleParameter.ROLE),
					rs.getString(UserRoleParameter.ACCOUNT_STATUS)
					);
		}
		st.close();
		rs.close();
		con.close();

		return bean;
	}

	public String getUserName(String id) throws Exception {

		Connection con = getConnection();
		String sql = "select "
		+ UserInfoParameter.USER_ID + ", "
		+ UserInfoParameter.LAST_NAME + ", "
		+ UserInfoParameter.FIRST_NAME
		+ " from "
		+ UserInfoParameter.TABLE
		+ " where "
		+ UserInfoParameter.USER_ID + " = ?"
		;
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		String name = "";
		while(rs.next()) {
			name = rs.getString(UserInfoParameter.LAST_NAME) + rs.getString(UserInfoParameter.FIRST_NAME);
		}
		st.close();
		rs.close();
		con.close();

		return name;
	}
}
