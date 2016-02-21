package action.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.ItemBean;
import model.RequestBean;
import model.StudentBean;
import utility.SQLCommands;
import utility.SQLOperation;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements SQLCommands {
	
	private List<ItemBean> itemList = new ArrayList<ItemBean>();
	private List<String> categoryList, brandList, roomList = new ArrayList<String>();
	private List<RequestBean> requestList = new ArrayList<RequestBean>();
	private List<StudentBean> studentList = new ArrayList<StudentBean>();
	private List<String> courseList = new ArrayList<String>();
	private int deadline = 0;
	
	private String addItem, addCategory, addBrand;
	private String response, available;
	private String addStudent, addCourse, addRoom, addAdmin;
	private String updateDeadline;
	
	public String execute() {
	  try {
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			//Get data from DB
			itemList = SQLOperation.getItemList(GET_ALL_ITEMS);
			categoryList = SQLOperation.getCategories();
			brandList = SQLOperation.getBrands();
			requestList = SQLOperation.getRequestList();
			studentList = SQLOperation.getStudentList();
			courseList = SQLOperation.getCourses();
			roomList = SQLOperation.getRooms();
			deadline = SQLOperation.getDeadlineDuration();
			//Add Item
			if(addItem != null) {
				int category = 0, brand = 0; String serialNo = "", propertyNo = "";
				String data[] = getAddItem().split(",");
		
				for(String line : categoryList) {
					if(data[0].equals(line)) { category = SQLOperation.convert(CONVERT_CATEGORY, line); }
				}
				for(String line : brandList) {
					if(data[1].equals(line)) { brand = SQLOperation.convert(CONVERT_BRAND, line); }
				}
				serialNo = data[2]; propertyNo = data[3];
				SQLOperation.addItem(category, brand, serialNo, propertyNo);
			}
			//Add category + brand
			if(addCategory != null) {
				if(categoryList.contains(addCategory) == false) { SQLOperation.addCategory(addCategory); }
			}
			if(addBrand != null) {
				if(brandList.contains(addBrand) == false) {	SQLOperation.addBrand(addBrand); }
			}
			//Response
			if(response != null) {				
				String first = response.substring(0, 1);
				int reqId = Integer.parseInt(response.substring(1));
							
				if(first.equals("D")) { SQLOperation.respondRequest(DECLINED_REQUEST, reqId); }
				if(first.equals("A")) { SQLOperation.respondRequest(ACCEPT_REQUEST, reqId);	}
				if(first.equals("R")) { SQLOperation.respondRequest(RETURN_REQUEST, reqId);	}
			}
			//Available
			if(available != null) {
				String state = available.substring(0, 1);
				int itemId = Integer.parseInt(available.substring(1)) + 1;
				if(state.equals("T")) {	SQLOperation.itemAvailability(false, itemId); }
				if(state.equals("F")) {	SQLOperation.itemAvailability(true, itemId); }
			}
			//Add Student
			if(addStudent != null) {
				System.out.println(addStudent);
				
				String studentId = "", firstName = "", lastName = ""; int course = 0;
				String data[] = getAddStudent().split(",");
				
				studentId = data[0]; firstName = data[1]; lastName = data[2];
				for(String line : courseList) {
					if(data[3].equals(line)) { course = SQLOperation.convert(CONVERT_COURSE, line); }
				}
				
				SQLOperation.addStudent(studentId, firstName, lastName, course);
			}
			//Add course/room
			if(addCourse != null) {
				if(courseList.contains(addCourse) == false) {	SQLOperation.addCourse(addCourse); }
			}
			if(addRoom != null) {
				SQLOperation.addRoom(addRoom);
			}
			//Add admin
			if(addAdmin != null) {
				String[] data = getAddAdmin().split(",");
				if(data.length == 3) { SQLOperation.addAdmin(data[0], data[1], data[2]); }
			}
			//Update Deadline
			if(updateDeadline != null) {
				SQLOperation.updateDeadlineDuration(Integer.valueOf(updateDeadline));
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	  } catch (Exception e) {
		  System.out.println("Exception @ AdminAction: " + e.getMessage());
		  return ERROR;
	  }
	}

	//Getters and Setters
	public List<ItemBean> getItemList() { return itemList; }
	public void setItemList(List<ItemBean> itemList) { this.itemList = itemList; }
	public List<String> getCategoryList() { return categoryList; }
	public void setCategoryList(List<String> categoryList) { this.categoryList = categoryList; }
	public List<String> getBrandList() { return brandList; }
	public void setBrandList(List<String> brandList) { this.brandList = brandList; }
	public String getAddItem() { return addItem; }
	public void setAddItem(String addItem) { this.addItem = addItem; }
	public String getAddCategory() { return addCategory; }
	public void setAddCategory(String addCategory) { this.addCategory = addCategory; }
	public String getAddBrand() { return addBrand; }
	public void setAddBrand(String addBrand) { this.addBrand = addBrand; }
	public List<RequestBean> getRequestList() { return requestList; }
	public void setRequestList(List<RequestBean> requestList) { this.requestList = requestList; }
	public String getResponse() { return response; }
	public void setResponse(String response) { this.response = response; }
	public String getAvailable() { return available; }
	public void setAvailable(String available) { this.available = available; }
	public List<StudentBean> getStudentList() { return studentList; }
	public void setStudentList(List<StudentBean> studentList) { this.studentList = studentList; }
	public String getAddStudent() { return addStudent; }
	public void setAddStudent(String addStudent) { this.addStudent = addStudent; }
	public List<String> getCourseList() { return courseList; }
	public void setCourseList(List<String> courseList) { this.courseList = courseList; }
	public String getAddCourse() { return addCourse; }
	public void setAddCourse(String addCourse) { this.addCourse = addCourse; }
	public int getDeadline() { return deadline; }
	public void setDeadline(int deadline) { this.deadline = deadline; }
	public String getUpdateDeadline() { return updateDeadline; }
	public void setUpdateDeadline(String updateDeadline) { this.updateDeadline = updateDeadline; }
	public String getAddRoom() { return addRoom; }
	public void setAddRoom(String addRoom) { this.addRoom = addRoom; }
	public List<String> getRoomList() { return roomList; }
	public void setRoomList(List<String> roomList) { this.roomList = roomList; }
	public String getAddAdmin() { return addAdmin; }
	public void setAddAdmin(String addAdmin) { this.addAdmin = addAdmin; }
}
