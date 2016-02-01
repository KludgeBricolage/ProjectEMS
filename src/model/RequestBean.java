package model;

import java.sql.Date;

public class RequestBean {
	
	//ELECT student_id, category_name, brand_name, serial_no, property_no, room, date_req, date_res, date_deadline, "
	
	private String 	studentId;
	private String 	category;
	private String 	brand;
	private String 	serialNo;
	private String 	propertyNo;
	private String 	room;
	private Date 	dateReq;
	private Date 	dateOfRes;
	private Date	dateDeadline;
	private boolean	declined;
	private boolean	allowed;
	private boolean	returned;
	private int		requestId;
	private	int		itemId;
	
	public RequestBean(String studentId, String category, String brand,	String serialNo, String propertyNo,	String room,
	  Date dateReq, Date dateOfRes, Date dateDeadline, boolean declined, boolean allowed, boolean	returned, 
	  int requestId, int itemId) {
		setStudentId(studentId);
		setCategory(category);
		setBrand(brand);
		setSerialNo(serialNo);
		setPropertyNo(propertyNo);
		setRoom(room);
		setDateReq(dateReq);
		setDateOfRes(dateOfRes);
		setDateDeadline(dateDeadline);
		setDeclined(declined);
		setAllowed(allowed);
		setReturned(returned);
		setRequestId(requestId);
		setItemId(itemId);
	}

	//Getters and Setters
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Date getDateReq() {
		return dateReq;
	}
	public void setDateReq(Date dateReq) {
		this.dateReq = dateReq;
	}
	public Date getDateOfRes() {
		return dateOfRes;
	}
	public void setDateOfRes(Date dateOfRes) {
		this.dateOfRes = dateOfRes;
	}
	public Date getDateDeadline() {
		return dateDeadline;
	}
	public void setDateDeadline(Date dateDeadline) {
		this.dateDeadline = dateDeadline;
	}
	public boolean isDeclined() {
		return declined;
	}
	public void setDeclined(boolean declined) {
		this.declined = declined;
	}
	public boolean isAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
