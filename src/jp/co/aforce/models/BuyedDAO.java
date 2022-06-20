package jp.co.aforce.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.BuyedBean;
import jp.co.aforce.beans.BuyedInfoBean;
import jp.co.aforce.parameters.BuyedParameter;

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

}
