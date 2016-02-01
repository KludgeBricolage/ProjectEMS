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
import javax.swing.table.DefaultTableModel;

import model.ItemBean;
import model.RequestBean;

public class SQLOperation implements SQLCommands {
	
	/*
	 * Notes:
	 *	- Can make global function for getting one cell :: get(list, cmd)
	 *		- Could make global even if not one cell
	 * 		- Planned Convention :: list name = func, exception and list name
	 * 		- Drawback :: Maintainence @ Actions
	 * 		- Skip for now since deadlines :>
	 */
	
	
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
	
	//Admin Database
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
	
	//Request
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
			System.out.println("Exception @ addCategory: " + e.getMessage());
			return 0;
		}
	}
	
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
}
