package model;

public class ItemBean {
	private int itemId;
	private String category;
	private String brand;
	private String serialNo;
	private String propertyNo;
	private boolean available;
	
	public ItemBean(int itemId, String category, String brand, String serialNo, String propertyNo, boolean available) {
		setItemId(itemId);
		setCategory(category);
		setBrand(brand);
		setSerialNo(serialNo);
		setPropertyNo(propertyNo);
		setAvailable(available);
	}
	
	//Getters and Setters
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPropertyNo() {
		return propertyNo;
	}
	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
