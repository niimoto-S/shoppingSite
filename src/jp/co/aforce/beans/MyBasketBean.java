package jp.co.aforce.beans;

import java.io.Serializable;

public class MyBasketBean implements Serializable {

	private int id;
	private int itemId;
	private String itemName;
	private String origin;
	private int quantity;
	private String unit;
	private int price;
	private String explanation;
	private String image;
	private String producerId;

	public MyBasketBean() {}

	public MyBasketBean(
			int id,
			int itemId,
			String itemName,
			String origin,
			int quantity,
			String unit,
			int price,
			String explanation,
			String image,
			String producerId
			) {
		this.id = id;
		this.itemId = itemId;
		this.itemName = itemName;
		this.origin = origin;
		this.quantity = quantity;
		this.unit = unit;
		this.price = price;
		this.explanation = explanation;
		this.image = image;
		this.producerId = producerId;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	public String getProducerId() {
		return producerId;
	}
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

}
