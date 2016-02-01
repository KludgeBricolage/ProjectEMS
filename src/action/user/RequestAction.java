package action.user;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import security.Security;
import utility.SQLCommands;
import utility.SQLOperation;
import model.ItemBean;

import com.opensymphony.xwork2.Action;

public class RequestAction implements Action, SQLCommands {

	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	private List<String> roomList = new ArrayList<String>();
	private List<String> categoryList = new ArrayList<String>();
	
	private String i;
	private String data;
	
	public String execute() {
		
		Connection connection = SQLOperation.getConnection();
		
		if(data != null) {
		  try {
			String[] dataArray = getData().split(",");
			String[] items = dataArray[4].split(";");
			DateFormat df= new SimpleDateFormat("MM/dd/yyyy");
			String currentDate = df.format(new java.util.Date());
			java.util.Date dateRequested 	= df.parse(currentDate);	
			java.util.Date dateOfReserve	= df.parse(dataArray[1]);	
			java.util.Date dateDeadline	= df.parse(dataArray[2]);
				
			for(String itemId: items) {
							
			SQLOperation.requestItem(dataArray[0], SQLOperation.convert(CONVERT_ROOM, dataArray[3]), 
					Integer.valueOf(itemId), new java.sql.Date(dateRequested.getTime()), 
					new java.sql.Date(dateOfReserve.getTime()), new java.sql.Date(dateDeadline.getTime()));
			}
			return "land";
		  } catch (Exception e) {
			  System.out.println("Exception @ RequestAction: " + e.getMessage());
		  }
		}
		
		if (connection != null) {
			roomList = SQLOperation.getRooms();
			itemList = SQLOperation.getItemList(GET_ITEM_LIST);
			categoryList = SQLOperation.getCategories();
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

	public List<String> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<String> roomList) {
		this.roomList = roomList;
	}

	public String getI() {
		return Security.decrypt(i);
	}
	public void setI(String i) {
		this.i = i;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	
}
