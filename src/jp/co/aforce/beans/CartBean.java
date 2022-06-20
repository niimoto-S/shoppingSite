package jp.co.aforce.beans;

public class CartBean {

	int itemId;
	String producerId;
	String consumerId;
	int quantity;

	public CartBean() {}

	public CartBean(
			int itemId,
			String producerId,
			String consumerId,
			int quantity
			) {
		this.itemId = itemId;
		this.producerId = producerId;
		this.consumerId = consumerId;
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getProducerId() {
		return producerId;
	}
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
