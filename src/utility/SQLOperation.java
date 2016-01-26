package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.table.DefaultTableModel;

import model.ItemBean;

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
	public static List<ItemBean> getItemList() {
		List<ItemBean> itemList = new ArrayList<>();
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(GET_ITEM_LIST);
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
}
