package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.BuyedBean;
import jp.co.aforce.beans.BuyedBeanEx;
import jp.co.aforce.beans.BuyedInfoBean;
import jp.co.aforce.beans.BuyedInfoBeanEx;
import jp.co.aforce.parameters.BuyedParameter;
import jp.co.aforce.parameters.ItemInfoParameter;

public class BuyedDAO extends DAO{

	public BuyedInfoBean findAllByProducerId(String producerId) throws Exception {

		BuyedInfoBean infoBean = new BuyedInfoBean();

		Connection con = getConnection();
		String sql = "select * from "
				+ BuyedParameter.TABLE
				+ " where "
				+ BuyedParameter.PRODUCER_ID + " = ?"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, producerId);

		ResultSet rs = st.executeQuery();

		BuyedBean buyedBean = null;
		while(rs.next()) {
			buyedBean = new BuyedBean(
					rs.getInt("ID"),
					rs.getInt(BuyedParameter.ITEM_ID),
					rs.getString(BuyedParameter.CONSUMER_ID),
					rs.getString(BuyedParameter.PRODUCER_ID),
					rs.getInt(BuyedParameter.PRICE),
					rs.getInt(BuyedParameter.QUANTITY)
					);
			infoBean.addItem(buyedBean);
		}
		rs.close();
		st.close();
		con.close();

		return infoBean;
	}

	public BuyedInfoBeanEx findSalesbyUserId(String userId) throws Exception {
		BuyedInfoBeanEx infoBeanEx = new BuyedInfoBeanEx();

		Connection con = getConnection();

		String sql = "select "
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.NAME + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.ORIGIN + ","
				+ BuyedParameter.TABLE + "." + BuyedParameter.QUANTITY + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.UNIT + ","
				+ BuyedParameter.TABLE + "." + BuyedParameter.PRICE + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.EXPLANATION + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.IMAGE
				+ " from "
				+ BuyedParameter.TABLE + " inner join " + ItemInfoParameter.TABLE
				+ " on "
				+ BuyedParameter.TABLE + "." + BuyedParameter.ITEM_ID
				+ " = "
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.ITEM_ID
				+ " where "
				+ BuyedParameter.TABLE + "." + BuyedParameter.CONSUMER_ID
				+ " = ?"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, userId);

		ResultSet rs = st.executeQuery();

		BuyedBeanEx beanEx = null;
		while(rs.next()) {
			beanEx = new BuyedBeanEx(
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getInt(BuyedParameter.QUANTITY),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(BuyedParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE)
					);
			infoBeanEx.addItem(beanEx);
		}
		rs.close();
		st.close();
		con.close();

		return infoBeanEx;
	}

}
