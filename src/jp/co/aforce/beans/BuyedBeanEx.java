package jp.co.aforce.beans;

import java.io.Serializable;

public class BuyedBeanEx implements Serializable {

	private String itemName;
	private String origin;
	private int quantity;
	private String  unit;
	private int price;
	private String explanation;
	private String image;

	public BuyedBeanEx() {}

	public BuyedBeanEx(
			String itemName,
			String origin,
			int quantity,
			String  unit,
			int price,
			String explanation,
			String image
			) {
		this.itemName = itemName;
		this.origin = origin;
		this.quantity = quantity;
		this.unit = unit;
		this.price = price;
		this.explanation = explanation;
		this.image = image;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



}
