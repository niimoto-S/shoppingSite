package jp.co.aforce.beans;

import java.io.Serializable;

public class ItemBean implements Serializable {

	private String itemId;
	private String userId;
	private String itemName;
	private String origin;
	private String unit;
	private int price;
	private String explanation;
	private String imageName;
	private String itemStatus;

	public ItemBean() {
	}

	public ItemBean(
			String itemId,
			String userId,
			String itemName,
			String origin,
			String unit,
			int price,
			String explanation,
			String imageName,
			String itemStatus
			) {
		this.itemId = itemId;
		this.userId = userId;
		this.itemName = itemName;
		this.origin = origin;
		this.unit = unit;
		this.price = price;
		this.explanation = explanation;
		this.imageName = imageName;
		this.itemStatus = itemStatus;
	}

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}


}
