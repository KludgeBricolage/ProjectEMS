package utility;

public interface SQLCommands {
/*
	//Users
	String GET_ALL_USER = "select student_id from users";
	String SEARCH_USER = "select * from users where student_id=?";
	
	// Items
	String GET_ALL_ITEM_CATEGORY_LIST = "SELECT DISTINCT item_category FROM items";
	String GET_ITEM_COUNT = "SELECT COUNT(item_category) AS ? FROM items WHERE item_category = ?";
	String GET_AVAILABLE_COUNT = "SELECT COUNT(item_category) AS ? FROM items WHERE item_category = ? AND not_available = ?";
	String GET_ALL_ITEM = "SELECT * FROM items WHERE item_category = ? AND not_available = ?";

	// Reserve
	String INSERT_RESERVE = "INSERT INTO reserve(student_id, item_id, room, date_req, date_res, date_deadline, returned, allowed) values(?, ?, ?, ?, ?, ?, ?, ?)";
	
	// Admin Requests
	String GET_ALL_RESERVE_REQUESTS = "SELECT * FROM reserve WHERE returned = false AND allowed = false";
	String ACCEPT_REQUEST = "UPDATE reserve SET returned = true, allowed = true WHERE reserve_id = ?";
	String DECLINE_REQUEST = "UPDATE reserve SET returned = true, allowed = false WHERE reserve_id = ?";
	
	String ITEM_RETURNED = "UPDATE reserve SET returned = false, allowed = true WHERE reserve_id = ?";
	
	String GET_ALL_ACCEPTED_REQUESTS = "SELECT * FROM reserve WHERE returned = true AND allowed = true";
	String CANCEL_REQUEST = "UPDATE reserve SET returned = true, allowed = false WHERE item_id = ?";
	
	String UPDATE_ITEM_LIST = "UPDATE items SET not_available = ? WHERE item_id = ?";
	//update item list
	//update available? (depends on how well item list)
	// Admin
	String GET_ALL_ADMIN = "SELECT admin_id FROM admin";
	String SEARCH_ADMIN = "SELECT * FROM admin WHERE admin_id = ?";
*/

	//Login
	String GET_ALL_USER 	= "SELECT student_id FROM users";
	String GET_ALL_ADMIN 	= "SELECT admin_id FROM admin";
	
	//Database GUI
	String GET_ITEM_LIST	= "SELECT item_id, category_name, brand_name, serial_no, property_no, available "
						 	+ "FROM items "
						 	+ "JOIN item_brand "
						 	+ "ON items.brand=item_brand.brand_id "
						 	+ "JOIN item_category "
						 	+ "ON items.category=item_category.category_id "
						 	+ "ORDER BY `items`.`item_id` ASC";
	String GET_CATEGORIES 	= "SELECT category_name "
							+ "FROM item_category "
							+ "ORDER BY `item_category`.`category_id` ASC";
	String GET_BRANDS 		= "SELECT brand_name "
							+ "FROM item_brand "
							+ "ORDER BY `item_brand`.`brand_id` ASC";
	
	String ADD_ITEM 		= "INSERT INTO items(category, brand, serial_no, property_no, available) "
							+ "VALUES (?, ?, ?, ?, 1)";
	String ADD_CATEGORY 	= "INSERT INTO item_category(category_name) "
							+ "VALUES(?)";
	String ADD_BRAND 		= "INSERT INTO item_brand(brand_name) "
					 		+ "VALUES(?)";
}
