package jp.co.aforce.util;

import jp.co.aforce.parameters.CartParameter;
import jp.co.aforce.parameters.ItemInfoParameter;
import jp.co.aforce.parameters.UserInfoParameter;
import jp.co.aforce.parameters.UserRoleParameter;
//入力が必須なものをnullチェックするクラス
//入力できていない場合は上から順番に「入力してほしい情報の名前」を返す
//全て入力していた場合は空を返す
public class NullCheck {

	public String addUserCheck(
			String lastName,
			String firstName,
			String sex,
			String id,
			String password,
			String role,
			String year,
			String month,
			String day,
			String phoneNumber,
			String mailAddress
			) {

		if(lastName.equals("")) {
			return UserInfoParameter.LAST_NAME_STR;
		}
		if(firstName.equals("")) {
			return UserInfoParameter.FIRST_NAME_STR;
		}
		try {
			if(sex.equals("") || !(sex.equals("1") || sex.equals("2"))) {
				return UserInfoParameter.SEX_STR;
			}
		} catch (Exception e) {
			return UserInfoParameter.SEX_STR;
		}
		if(id.equals("")) {
			return UserInfoParameter.USER_ID_STR;
		}
		if(password.equals("")) {
			return UserRoleParameter.PASSWORD_STR;
		}
		try {
			if(role.equals("") || !(role.equals("producer") || role.equals("consumer"))) {
				return UserRoleParameter.ROLE_STR;
			}
		} catch (Exception e) {
			return UserRoleParameter.ROLE_STR;
		}
		try {
			if(year.equals("") || !(Integer.parseInt(year) >= 1920 && Integer.parseInt(year) <= 2022)) {
				return UserInfoParameter.YEAR_STR;
			}
		} catch (Exception e) {
			return UserInfoParameter.YEAR_STR;
		}
		try {
			if(month.equals("") || !(Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12)) {
				return UserInfoParameter.MONTH_STR;
			}
		} catch (Exception e) {
			return UserInfoParameter.MONTH_STR;
		}
		try {
			if(day.equals("") || !(Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31)) {
				return UserInfoParameter.DAY_STR;
			}
		} catch (Exception e) {
			return UserInfoParameter.DAY_STR;
		}
		if(phoneNumber.equals("")) {
			return UserInfoParameter.PHONE_NUMBER_STR;
		}
		if(mailAddress.equals("")) {
			return UserInfoParameter.MAIL_ADDRESS_STR;
		}

		return "";
	}

	public String loginCheck(String userId, String password) {

		if(userId.equals("")) {
			return UserInfoParameter.USER_ID_STR;
		}
		if(password.equals("")) {
			return UserRoleParameter.PASSWORD_STR;
		}

		return "";
	}

	//追加・更新時に使う
	public String itemCheck(
			String itemName,
			String origin,
			String unit,
			String price,
			String explanation
			) {

		if(itemName.equals("")) {
			return ItemInfoParameter.NAME_STR;
		}
		if(origin.equals("")) {
			return ItemInfoParameter.ORIGIN_STR;
		}
		try {
			if(unit.equals("")) {
				return ItemInfoParameter.UNIT_STR;
			}
		} catch (Exception e) {
			return ItemInfoParameter.UNIT_STR;
		}
		try {
			if(price.equals("") || Integer.parseInt(price) < 0) {
				return ItemInfoParameter.PRICE_STR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ItemInfoParameter.PRICE_STR;
		}

		if(explanation.equals("")) {
			return ItemInfoParameter.EXPLANATION_STR;
		}

		return "";
	}

	public String cart(
			String itemId,
			String quantity
			) {
		if(itemId.equals("")) {
			return "ID";
		}
		if(quantity.equals("")) {
			return CartParameter.QUANTITY＿STR;
		}

		return "";
	}

}

