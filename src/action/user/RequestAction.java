package action.user;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import utility.SQLOperation;
import model.ItemBean;

import com.opensymphony.xwork2.Action;

public class RequestAction implements Action {

	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	
	public String execute() {
		
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			itemList = SQLOperation.getItemList();
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
}
