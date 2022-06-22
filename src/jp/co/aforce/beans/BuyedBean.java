package jp.co.aforce.beans;

import java.io.Serializable;

public class BuyedBean implements Serializable {

	private int id;
	private int itemId;
	private String consumerId;
	private String producerId;
	private int price;
	private int quantity;

	public BuyedBean() {}

	public BuyedBean(
			int id,
			int itemId,
			String consumerId,
			String producerId,
			int price,
			int quantity
			) {
		this.id = id;
		this.itemId = itemId;
		this.consumerId = consumerId;
		this.producerId = producerId;
		this.price = price;
		this.quantity = quantity;
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

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
