package action.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import model.ItemBean;
import utility.SQLOperation;

public class DatabaseGUIAction implements Action{
	
	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	private List<String> categoryList = new ArrayList<String>();
	private List<String> brandList = new ArrayList<String>();

	private String addItem = "none", addCategory = "none", addBrand = "none";
	
	public String execute() {
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			itemList = SQLOperation.getItemList();
			categoryList = SQLOperation.getCategories();
			brandList = SQLOperation.getBrands();
			//Move to a different return value
			//Adding
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
				
				SQLOperation.addItem(category, brand, serialNo, propertyNo);
			}
			//Add category + brand
			if(!addCategory.equals("none")) {
				if(categoryList.contains(addCategory) == false) {
					SQLOperation.addCategory(addCategory);
				}
			}
			if(!addBrand.equals("none")) {
				if(brandList.contains(addBrand) == false) {
					SQLOperation.addBrand(addBrand);
				}
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}
	//Functions
	
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

	public String getAddCategory() {
		return addCategory;
	}

	public void setAddCategory(String addCategory) {
		this.addCategory = addCategory;
	}

	public String getAddBrand() {
		return addBrand;
	}

	public void setAddBrand(String addBrand) {
		this.addBrand = addBrand;
	}
}
