package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.UserBeanEx;
import jp.co.aforce.beans.UserInfoBeanEx;
import jp.co.aforce.parameters.UserInfoParameter;
import jp.co.aforce.parameters.UserRoleParameter;

public class UserDAO extends DAO {

	public UserInfoBeanEx searchUserByAllName(String userId) throws Exception {
		UserInfoBeanEx infoBeanEx = new UserInfoBeanEx();

		Connection con = getConnection();
		String sql = "select "
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.USER_ID + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.LAST_NAME + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.FIRST_NAME + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.SEX + ","
				+ UserRoleParameter.TABLE + "." + UserRoleParameter.ROLE + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.YEAR + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.MONTH + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.DAY + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.PHONE_NUMBER + ","
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.MAIL_ADDRESS
				+ " from "
				+ UserInfoParameter.TABLE + " inner join " + UserRoleParameter.TABLE
				+ " on "
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.USER_ID
				+ " = "
				+ UserRoleParameter.TABLE + "." + UserInfoParameter.USER_ID
				+ " where "
				+ UserInfoParameter.TABLE + "." + UserInfoParameter.USER_ID
				+ " like ?"
				+ " && "
				+ UserRoleParameter.TABLE + "." + UserRoleParameter.ACCOUNT_STATUS
				+ " = 'enable'"
				+ " && "
				+ UserRoleParameter.TABLE + "." + UserRoleParameter.ROLE
				+ " != 'admin'"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + userId + "%");

		ResultSet rs = st.executeQuery();
		UserBeanEx beanEx = null;
		while(rs.next()) {
			beanEx = new UserBeanEx(
					rs.getString(UserInfoParameter.USER_ID),
					rs.getString(UserInfoParameter.LAST_NAME),
					rs.getString(UserInfoParameter.FIRST_NAME),
					rs.getString(UserInfoParameter.SEX),
					rs.getString(UserRoleParameter.ROLE),
					rs.getInt(UserInfoParameter.YEAR),
					rs.getInt(UserInfoParameter.MONTH),
					rs.getInt(UserInfoParameter.DAY),
					rs.getString(UserInfoParameter.PHONE_NUMBER),
					rs.getString(UserInfoParameter.MAIL_ADDRESS)
					);
			infoBeanEx.addUser(beanEx);
		}
		rs.close();
		st.close();
		con.close();


		return infoBeanEx;
	}

	public void deleteUser(String userId) throws Exception {
		Connection con = getConnection();
		String sql = "update "
				+ UserRoleParameter.TABLE
				+ " set "
				+ UserRoleParameter.ACCOUNT_STATUS + " = 'disable'"
				+ " where "
				+ UserRoleParameter.USER_ID + " = ?"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, userId);

		st.executeUpdate();
		st.close();
		con.close();
	}
}
