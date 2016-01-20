package action.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import model.ItemBean;
import utility.SQLOperation;

public class DatabaseManipulationAction implements Action{
	
	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	private List<String> categoryList = new ArrayList<String>();
	private List<String> brandList = new ArrayList<String>();

	private String addItem = "none";
	
	public String execute() {
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			itemList = SQLOperation.getItemList();
			categoryList = SQLOperation.getCategories();
			brandList = SQLOperation.getBrands();
			
			if(!addItem.equals("none")) {
				int category = 0, brand = 0;
				String serialNo = "", propertyNo = "";
				
				String data[] = getAddItem().split(",");
		
				for(String line : categoryList) {
					if(data[0].equals(line)) {
						category = categoryList.indexOf(data[0]) + 1;
					}
				}
				
				for(String line : brandList) {
					if(data[1].equals(line)) {
						brand = brandList.indexOf(data[1]) + 1;
					}
				}
				serialNo = data[2];
				propertyNo = data[3];
				
				//System.out.println(category + ", " + brand  + ", " + serialNo + ", " + productNo);
				
				SQLOperation.addItem(category, brand, serialNo, propertyNo);
			}
			
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}
	
	//Getters and Setters
	public List<ItemBean> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemBean> itemList) {
		this.itemList = itemList;
	}
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public List<String> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<String> brandList) {
		this.brandList = brandList;
	}
	public String getAddItem() {
		return addItem;
	}
	public void setAddItem(String addItem) {
		this.addItem = addItem;
	}
}
