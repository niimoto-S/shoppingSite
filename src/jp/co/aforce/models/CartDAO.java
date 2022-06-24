package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.aforce.beans.CartBean;
import jp.co.aforce.beans.MyBasketBean;
import jp.co.aforce.beans.MyBasketInfoBean;
import jp.co.aforce.parameters.BuyedParameter;
import jp.co.aforce.parameters.CartParameter;
import jp.co.aforce.parameters.ItemInfoParameter;

public class CartDAO extends DAO {

	public void addCart(CartBean cartBean) throws SQLException, Exception {
		Connection con = getConnection();
		String sql = "insert into "
				+ CartParameter.TABLE
				+ " values (null, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cartBean.getItemId());
		st.setString(2, cartBean.getProducerId());
		st.setString(3, cartBean.getConsumerId());
		st.setInt(4, cartBean.getQuantity());
		st.executeUpdate();

		st.close();
		con.close();
	}

	public int check(int itemId, String userId) throws Exception {
		Connection con = getConnection();
		String sql = "select count(*) from "
				+ CartParameter.TABLE
				+ " where "
				+ ItemInfoParameter.ITEM_ID + " = ?"
				+ " AND "
				+ CartParameter.CONSUMER_ID + " = ?"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, itemId);
		st.setString(2, userId);
		ResultSet rs = st.executeQuery();
		int count = 0;
		while(rs.next()) {
			count = rs.getInt("count(*)");
		}
		st.close();
		con.close();

		return count;
	}

	public MyBasketInfoBean findMyBasketByUserId(String userId) throws Exception {

		MyBasketInfoBean basketInfoBean = new MyBasketInfoBean();
		Connection con = getConnection();
		String sql = "select "
				+ CartParameter.TABLE + "." + CartParameter.ID + ","
				+ CartParameter.TABLE + "." + ItemInfoParameter.ITEM_ID + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.NAME + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.ORIGIN + ","
				+ CartParameter.TABLE + "." + CartParameter.QUANTITY + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.UNIT + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.PRICE + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.EXPLANATION + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.IMAGE + ","
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.USER_ID
				+ " from "
				+ CartParameter.TABLE + " inner join " + ItemInfoParameter.TABLE
				+ " on "
				+ CartParameter.TABLE + "." + ItemInfoParameter.ITEM_ID
				+ " = "
				+ ItemInfoParameter.TABLE + "." + ItemInfoParameter.ITEM_ID
				+ " where "
				+ CartParameter.TABLE + "." + CartParameter.CONSUMER_ID + " = ?"
				+ " and "
				+ ItemInfoParameter.TABLE+ "." + ItemInfoParameter.ITEM_STATUS + " = 'enable'"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, userId);

		ResultSet rs = st.executeQuery();
		MyBasketBean basketBean = null;
		while(rs.next()) {
			basketBean = new MyBasketBean(
					rs.getInt(CartParameter.ID),
					rs.getInt(ItemInfoParameter.ITEM_ID),
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getInt(CartParameter.QUANTITY),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(ItemInfoParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE),
					rs.getString(ItemInfoParameter.USER_ID)
					);
			basketInfoBean.addMyBasket(basketBean);
		}
		rs.close();
		st.close();
		con.close();

		return basketInfoBean;
	}

	public void deleteCart(int itemId) throws Exception {
		Connection con = getConnection();
		String sql = "delete from "
				+ CartParameter.TABLE
				+ " where "
				+ CartParameter.ID + " = ?"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, itemId);
		st.executeUpdate();

		st.close();
		con.close();
	}

	public void updateCart(int itemId,int quantity) throws Exception {
		Connection con = getConnection();
		String sql = "update "
				+ CartParameter.TABLE
				+ " set "
				+ CartParameter.QUANTITY + " = ?"
				+ " where "
				+ CartParameter.ID + " = ?"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, quantity);
		st.setInt(2, itemId);
		st.executeUpdate();

		st.close();
		con.close();
	}

	public void buyItems(MyBasketInfoBean myBasketInfoBean, String consumerId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = null;

		String sql = "";

		for(MyBasketBean bean : myBasketInfoBean.getMyBasketBeanArray()) {
			sql = "insert into "
					+ BuyedParameter.TABLE
					+ " values (null, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setInt(1, bean.getItemId());
			st.setString(2, bean.getProducerId());
			st.setString(3, consumerId);
			st.setInt(4, bean.getPrice());
			st.setInt(5, bean.getQuantity());
			st.executeUpdate();
		}

		sql = "delete from "
				+ CartParameter.TABLE
				+ " where "
				+ CartParameter.CONSUMER_ID + " = ?";
				;
		st = con.prepareStatement(sql);
		st.setString(1, consumerId);
		st.executeUpdate();

		st.close();
		con.close();
	}

}
