package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.ItemBean;
import jp.co.aforce.beans.ItemInfoBean;
import jp.co.aforce.parameters.ItemInfoParameter;

public class ItemDAO extends DAO {

	public void addItem(ItemBean itemBean) throws Exception {

		Connection con = getConnection();
		String sql = "insert into "
				+ ItemInfoParameter.TABLE
				+ " values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, itemBean.getUserId());
		st.setString(2, itemBean.getItemName());
		st.setString(3, itemBean.getOrigin());
		st.setString(4, itemBean.getUnit());
		st.setInt(5, itemBean.getPrice());
		st.setString(6, itemBean.getExplanation());
		st.setString(7, itemBean.getImageName());
		st.setString(8, itemBean.getItemStatus());
		st.executeUpdate();

		st.close();
		con.close();

	}

	public void updateItem(ItemBean itemBean) throws Exception {

		Connection con = getConnection();
		String sql = "update "
				+ ItemInfoParameter.TABLE
				+ " set "
				+ ItemInfoParameter.NAME + " = ?, "
				+ ItemInfoParameter.ORIGIN + " = ?, "
				+ ItemInfoParameter.UNIT + " = ?, "
				+ ItemInfoParameter.PRICE + " = ?, "
				+ ItemInfoParameter.EXPLANATION + " = ?, "
				+ ItemInfoParameter.IMAGE + " = ? "
				+ " where "
				+ ItemInfoParameter.ITEM_ID + " = ? "

				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, itemBean.getItemName());
		st.setString(2, itemBean.getOrigin());
		st.setString(3, itemBean.getUnit());
		st.setInt(4, itemBean.getPrice());
		st.setString(5, itemBean.getExplanation());
		st.setString(6, itemBean.getImageName());
		st.setString(7, itemBean.getItemId());
		st.executeUpdate();

		st.close();
		con.close();

	}

	public ItemInfoBean searchItem(String name, String userId) throws Exception {

		ItemInfoBean itemInfoBean = new ItemInfoBean();

		Connection con = getConnection();
		String sql = "select * from "
				+ ItemInfoParameter.TABLE
				+ " where "
				+ ItemInfoParameter.NAME + " like ? && "
				+ ItemInfoParameter.USER_ID + " = ? && "
				+ ItemInfoParameter.ITEM_STATUS + " = 'enable'"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + name + "%");
		st.setString(2, userId);

		ResultSet rs = st.executeQuery();

		ItemBean itemBean = null;
		while(rs.next()) {
			itemBean = new ItemBean(
					rs.getString(ItemInfoParameter.ITEM_ID),
					rs.getString(ItemInfoParameter.USER_ID),
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(ItemInfoParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE),
					rs.getString(ItemInfoParameter.ITEM_STATUS)
					);
			itemInfoBean.addItem(itemBean);
		}
		rs.close();
		st.close();
		con.close();

		return itemInfoBean;
	}

	public ItemInfoBean adminSearchItem(String name) throws Exception {

		ItemInfoBean itemInfoBean = new ItemInfoBean();

		Connection con = getConnection();
		String sql = "select * from "
				+ ItemInfoParameter.TABLE
				+ " where "
				+ ItemInfoParameter.NAME + " like ? && "
				+ ItemInfoParameter.ITEM_STATUS + " = 'enable'"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + name + "%");

		ResultSet rs = st.executeQuery();

		ItemBean itemBean = null;
		while(rs.next()) {
			itemBean = new ItemBean(
					rs.getString(ItemInfoParameter.ITEM_ID),
					rs.getString(ItemInfoParameter.USER_ID),
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(ItemInfoParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE),
					rs.getString(ItemInfoParameter.ITEM_STATUS)
					);
			itemInfoBean.addItem(itemBean);
		}
		rs.close();
		st.close();
		con.close();

		return itemInfoBean;
	}

	public ItemInfoBean searchItemByAllId(String name) throws Exception {

		ItemInfoBean itemInfoBean = new ItemInfoBean();

		Connection con = getConnection();
		String sql = "select * from "
				+ ItemInfoParameter.TABLE
				+ " where "
				+ ItemInfoParameter.NAME + " like ? && "
				+ ItemInfoParameter.ITEM_STATUS + " = 'enable'"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + name + "%");

		ResultSet rs = st.executeQuery();

		ItemBean itemBean = null;
		while(rs.next()) {
			itemBean = new ItemBean(
					rs.getString(ItemInfoParameter.ITEM_ID),
					rs.getString(ItemInfoParameter.USER_ID),
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(ItemInfoParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE),
					rs.getString(ItemInfoParameter.ITEM_STATUS)
					);
			itemInfoBean.addItem(itemBean);
		}
		rs.close();
		st.close();
		con.close();

		return itemInfoBean;
	}

	public void deleteItemById(int itemId) throws Exception {
		Connection con = getConnection();
		String sql = "update "
				+ ItemInfoParameter.TABLE
				+ " set "
				+ ItemInfoParameter.ITEM_STATUS + " = 'disable'"
				+ " where "
				+ ItemInfoParameter.ITEM_ID + " = ?"
				;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, itemId);

		st.executeUpdate();
		st.close();
		con.close();
	}

	public ItemBean updateItemById(int itemId) throws Exception {
		Connection con = getConnection();
		String sql = "select * from "
				+ ItemInfoParameter.TABLE
				+ " where "
				+ ItemInfoParameter.ITEM_ID + " = ?"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, itemId);

		ResultSet rs = st.executeQuery();

		ItemBean itemBean = null;
		while(rs.next()) {
			itemBean = new ItemBean(
					rs.getString(ItemInfoParameter.ITEM_ID),
					rs.getString(ItemInfoParameter.USER_ID),
					rs.getString(ItemInfoParameter.NAME),
					rs.getString(ItemInfoParameter.ORIGIN),
					rs.getString(ItemInfoParameter.UNIT),
					rs.getInt(ItemInfoParameter.PRICE),
					rs.getString(ItemInfoParameter.EXPLANATION),
					rs.getString(ItemInfoParameter.IMAGE),
					rs.getString(ItemInfoParameter.ITEM_STATUS)
					);
		}
		rs.close();
		st.close();
		con.close();
		return itemBean;
	}

	public String findProducerIdByItemId(int itemId) throws Exception {

		Connection con = getConnection();
		String sql = "select * from "
				+ ItemInfoParameter.TABLE
				+ " where "
				+ ItemInfoParameter.ITEM_ID + " = ?"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, itemId);

		ResultSet rs = st.executeQuery();

		String id = "";
		while(rs.next()) {
			id = rs.getString(ItemInfoParameter.USER_ID);
		}
		rs.close();
		st.close();
		con.close();

		return id;
	}

//	public ItemInfoBean searchItemALL(String itemName) throws Exception {
//
//		ItemInfoBean itemInfoBean = new ItemInfoBean();
//
//		Connection con = getConnection();
//		String sql = "select * from "
//				+ ItemInfoParameter.TABLE
//				+ " where "
//				+ ItemInfoParameter.NAME + " like ? && "
//				+ ItemInfoParameter.USER_ID + " = ? && "
//				+ ItemInfoParameter.ITEM_STATUS + " = 'enable'"
//				;
//
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, "%" + itemName + "%");
//
//		ResultSet rs = st.executeQuery();
//
//		ItemBean itemBean = null;
//		while(rs.next()) {
//			itemBean = new ItemBean(
//					rs.getString(ItemInfoParameter.ITEM_ID),
//					rs.getString(ItemInfoParameter.USER_ID),
//					rs.getString(ItemInfoParameter.NAME),
//					rs.getString(ItemInfoParameter.ORIGIN),
//					rs.getString(ItemInfoParameter.UNIT),
//					rs.getInt(ItemInfoParameter.PRICE),
//					rs.getString(ItemInfoParameter.EXPLANATION),
//					rs.getString(ItemInfoParameter.IMAGE),
//					rs.getString(ItemInfoParameter.ITEM_STATUS)
//					);
//			itemInfoBean.addItem(itemBean);
//		}
//		rs.close();
//		st.close();
//		con.close();
//
//		return itemInfoBean;
//	}

}
