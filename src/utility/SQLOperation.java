package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import security.Security;
import model.ItemBean;
import model.RequestBean;
import model.StudentBean;

public class SQLOperation implements SQLCommands {	
	
	private static Connection connection;
	private SQLOperation() {}
	//Connection
	private static Connection getDBConnection() {
		try {
		    DataSource dataSource = (DataSource) 
		    	InitialContext.doLookup("java:comp/env/jdbc/ems");
		    
		    if (dataSource != null) {
		    	connection = dataSource.getConnection();
		    } else {
		    	throw new NullPointerException("DataSource is NULL."); 
		    }
		} catch (NamingException e) {
			System.out.println("NamingException @ getDBConnection: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException @ getDBConnection: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("NullPointerException @ getDBConnection: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception @ getDBConnection: " + e.getMessage());
		} 
		return connection;
	}
	
	public static Connection getConnection() {
		return (connection != null) ? connection : getDBConnection();
	}
	
	//Login
	public static ResultSet getAllUser() {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ALL_USER);  
		} catch (Exception e) {
			System.out.println("Exception @ getAllUser: " + e.getMessage());
			return rs; 
		}	
		return rs;
	}
	//Admin
	public static ResultSet getAllAdmin() {	
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ALL_ADMIN);
		} catch (Exception e) {
			System.out.println("Exception @ getAllAdmin: " + e.getMessage());
			return rs;
		}
		return rs;
	}
	public static void addAdmin(String adminId, String name, String password) {
		PreparedStatement ps;		
		try {
			ps = connection.prepareStatement(ADD_ADMIN);
			ps.setString(1, adminId);
			ps.setString(2, name);
			ps.setString(3, Security.encrypt(password));
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addAdmin: " + e.getMessage());
		}
	}
	//Items
	public static List<ItemBean> getItemList(String cmd) {
		List<ItemBean> itemList = new ArrayList<>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(cmd);
			while(rs.next()) {
				itemList.add(
					new ItemBean(rs.getInt(1), rs.getString(2),	rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6))
				);
			}
		} catch (Exception e) {
			System.out.println("Exception @ getItemList: " + e.getMessage());
			return itemList; 
		}	
		return itemList;
	}
	public static void addItem(int category, int brand, String serialNo, String propertyNo) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_ITEM);
			
			ps.setInt	(1, category);
			ps.setInt	(2, brand);
			ps.setString(3, serialNo);
			ps.setString(4, propertyNo);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addItem: " + e.getMessage());
		}
	}
	//Category
	public static List<String> getCategories() {
		List<String> categories = new ArrayList<String>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_CATEGORIES);
			while(rs.next()) {
				categories.add(rs.getString(1));
			}
			return categories;
		} catch (Exception e) {
			System.out.println("Exception @ getCategories: " + e.getMessage());
			return categories; 
		}	
	}
	
	public static void addCategory(String category) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_CATEGORY);
			
			ps.setString(1, category);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addCategory: " + e.getMessage());
		}
	}
	//Brand
	public static List<String> getBrands() {
		List<String> brands = new ArrayList<String>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_BRANDS);
			while(rs.next()) {
				brands.add(rs.getString(1));
			}
			return brands;
		} catch (Exception e) {
			System.out.println("Exception @ getBrands: " + e.getMessage());
			return brands; 
		}	
	}

	public static void addBrand(String brand) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_BRAND);
			
			ps.setString(1, brand);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addBrand: " + e.getMessage());
		}
	}
	
	//Rooms
	public static List<String> getRooms() {
		List<String> rooms = new ArrayList<String>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ROOMS);
			while(rs.next()) {
				rooms.add(rs.getString(1));
			}
			return rooms;
		} catch (Exception e) {
			System.out.println("Exception @ getRooms: " + e.getMessage());
			return rooms; 
		}	
	}
	public static void addRoom(String room) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_ROOM);
			
			ps.setString(1, room);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addRoom: " + e.getMessage());
		}
	}
	//Converts modular db to get FK
	public static int convert(String sqlCmd, String value) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = connection.prepareStatement(sqlCmd);
			
			ps.setString(1, value);
			rs = ps.executeQuery();
			
	        while(rs.next())
	        {
	        	return rs.getInt(1);
	        }
			return 0;
		} catch (Exception e) {
			System.out.println("Exception @ convert: " + e.getMessage());
			return 0;
		}
	}
	//Request
	public static void requestItem(String userId, int roomId, int itemId, Date dateReq, Date dateOfReserve, Date dateDeadline) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(REQUEST_ITEM);
			
			ps.setString(1, userId);
			ps.setInt(2, roomId);
			ps.setInt(3, itemId);
			ps.setDate(4, dateReq);
			ps.setDate(5, dateOfReserve);
			ps.setDate(6, dateDeadline);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ requestItem: " + e.getMessage());
		}
	}
		
	public static List<RequestBean> getRequestList() {
		List<RequestBean> requestList = new ArrayList<>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_REQUEST);
			while(rs.next()) {
				requestList.add(
					new RequestBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
							rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getBoolean(10), rs.getBoolean(11), rs.getBoolean(12), 
							rs.getInt(13), rs.getInt(14)
					)
				);
			}
		} catch (Exception e) {
			System.out.println("Exception @ getRequestList: " + e.getMessage());
			return requestList; 
		}	
		return requestList;
	}
	
	public static void respondRequest(String sqlCmd, int reqId) {
		List<RequestBean> requestList = getRequestList();
		RequestBean request = null;
		PreparedStatement ps;
		try {			
			for(RequestBean item : requestList) {
				if(reqId == item.getRequestId()) {
					request = item;
					//The actual accept command
					ps = connection.prepareStatement(sqlCmd);
					ps.setInt(1, reqId);
					ps.executeUpdate();
					
					//Change item availability if item is borrowed
					if(sqlCmd.equals(ACCEPT_REQUEST)) {
						itemAvailability(false, request.getItemId());
					}
					if(sqlCmd.equals(RETURN_REQUEST)) {
						itemAvailability(true, request.getItemId());
					}
				}
			}
			//Automatically declines existing requests if the item is already being borrowed
			if(request != null) {
				for(RequestBean item:requestList) {
					if((request.getItemId() == item.getItemId()) && (request.getRequestId() != item.getRequestId())) {		
						if(sqlCmd.equals(ACCEPT_REQUEST)) {
							ps = connection.prepareStatement(DECLINED_REQUEST);
							ps.setInt(1, item.getRequestId());
							ps.executeUpdate();
						}	
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception @ respondRequest: " + e.getMessage());
		}
	}
	//Item Availability (Delete item)
	public static void itemAvailability(boolean available, int itemId) {
		try {
			PreparedStatement ps; 
			
			ps = connection.prepareStatement(ITEM_AVAILABLE);
			ps.setBoolean(1, available);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ itemAvailability: " + e.getMessage());
		}
	}
	//Deadline Duration
	public static int getDeadlineDuration() {
		ResultSet rs = null;
		int duration = 1;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(DATE_DEADLINE);
			while(rs.next()) {
				duration = rs.getInt(1);
			}
			return duration;
		} catch (Exception e) {
			System.out.println("Exception @ getDeadlineDuration: " + e.getMessage());
		}
		return duration;	
	}
	public static void updateDeadlineDuration(int duration) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(UPDATE_DEADLINE);
			
			ps.setInt(1, duration);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ updateDeadlineDuration: " + e.getMessage());
		}
	}
	//Courses
	public static List<String> getCourses() {
		List<String> courses = new ArrayList<String>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_COURSE);
			while(rs.next()) {
				courses.add(rs.getString(1));
			}
			return courses;
		} catch (Exception e) {
			System.out.println("Exception @ getCourses: " + e.getMessage());
			return courses; 
		}	
	}
	public static void addCourse(String course) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_COURSE);
			
			ps.setString(1, course);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addCourse: " + e.getMessage());
		}
	}
	//Students
	public static List<StudentBean> getStudentList() {
		List<StudentBean> studentList = new ArrayList<>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_STUDENTS);
			while(rs.next()) {
				studentList.add(
					new StudentBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4))
				);
			}
		} catch (Exception e) {
			System.out.println("Exception @ getStudentList: " + e.getMessage());
			return studentList; 
		}	
		return studentList;
	}
	public static void addStudent(String studentId, String firstName, String lastName, int courseId) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(ADD_STUDENT);
			
			ps.setString(1, studentId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt	(4, courseId);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception @ addStudent: " + e.getMessage());
		}
	}
}
