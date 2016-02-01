package action.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import model.ItemBean;
import model.RequestBean;
import utility.SQLCommands;
import utility.SQLOperation;

public class AdminAction extends ActionSupport implements SQLCommands {
	
	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	private List<String> categoryList = new ArrayList<String>();
	private List<String> brandList = new ArrayList<String>();
	private List<RequestBean> requestList = new ArrayList<RequestBean>();

	private String addItem, addCategory, addBrand;
	private String response, available;
	
	public String execute() {
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			itemList = SQLOperation.getItemList(GET_ALL_ITEMS);
			categoryList = SQLOperation.getCategories();
			brandList = SQLOperation.getBrands();
			requestList = SQLOperation.getRequestList();
			//Adding
			if(addItem != null) {
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
			if(addCategory != null) {
				if(categoryList.contains(addCategory) == false) {
					SQLOperation.addCategory(addCategory);
				}
			}
			if(addBrand != null) {
				if(brandList.contains(addBrand) == false) {
					SQLOperation.addBrand(addBrand);
				}
			}
			//Response
			if(response != null) {				
				String first = response.substring(0, 1);
				int reqId = Integer.parseInt(response.substring(1));
							
				if(first.equals("D")) {
					SQLOperation.respondRequest(DECLINED_REQUEST, reqId);
				}
				if(first.equals("A")) {
					SQLOperation.respondRequest(ACCEPT_REQUEST, reqId);
				}
				if(first.equals("R")) {
					SQLOperation.respondRequest(RETURN_REQUEST, reqId);
				}
			}
			//Available
			if(available != null) {
				String state = available.substring(0, 1);
				int itemId = Integer.parseInt(available.substring(1)) + 1;
				if(state.equals("T")) {
					SQLOperation.itemAvailability(false, itemId);
				}
				if(state.equals("F")) {
					SQLOperation.itemAvailability(true, itemId);
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

	public List<RequestBean> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<RequestBean> requestList) {
		this.requestList = requestList;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
}
