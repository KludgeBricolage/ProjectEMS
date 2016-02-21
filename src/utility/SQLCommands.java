package utility;

public interface SQLCommands {
	//ADMIN
	String GET_ALL_ADMIN 	= "SELECT * FROM admin";
	String ADD_ADMIN		= "INSERT INTO admin(admin_id, name, password) "
							+ "VALUES(?, ?, ?)";
	//Items
	String GET_ITEM_LIST	= "SELECT item_id, category_name, brand_name, serial_no, property_no, available "
						 	+ "FROM items "
						 	+ "JOIN item_brand " 						//table name
						 	+ "ON items.brand = item_brand.brand_id "	//table.column = table.column
						 	+ "JOIN item_category "
						 	+ "ON items.category = item_category.category_id "
						 	+ "AND available = 1 "
						 	+ "ORDER BY `items`.`item_id` ASC";
	String GET_ALL_ITEMS	= "SELECT item_id, category_name, brand_name, serial_no, property_no, available "
						 	+ "FROM items "
						 	+ "JOIN item_brand " 						
						 	+ "ON items.brand = item_brand.brand_id "
						 	+ "JOIN item_category "
						 	+ "ON items.category = item_category.category_id "
						 	+ "ORDER BY `items`.`item_id` ASC";
	String ADD_ITEM 		= "INSERT INTO items(category, brand, serial_no, property_no, available) "
							+ "VALUES (?, ?, ?, ?, 1)";
	String ITEM_AVAILABLE	= "UPDATE items "
							+ "SET available = ? "
							+ "WHERE item_id = ?";
	//Category
	String GET_CATEGORIES 	= "SELECT category_name "
							+ "FROM item_category "
							+ "ORDER BY `item_category`.`category_id` ASC";
	String ADD_CATEGORY 	= "INSERT INTO item_category(category_name) "
							+ "VALUES(?)";
	String CONVERT_CATEGORY	= "SELECT category_id "
							+ "FROM item_category "
							+ "WHERE category_name = ?";
	//Brands
	String GET_BRANDS 		= "SELECT brand_name "
							+ "FROM item_brand "
							+ "ORDER BY `item_brand`.`brand_id` ASC";
	String ADD_BRAND 		= "INSERT INTO item_brand(brand_name) "
					 		+ "VALUES(?)";
	String CONVERT_BRAND	= "SELECT brand_id "
							+ "FROM item_brand "
							+ "WHERE brand_name = ?";
	//Course
	String GET_COURSE 		= "SELECT course "
							+ "FROM user_course "
							+ "ORDER BY `user_course`.`course_id` ASC";
	String ADD_COURSE 		= "INSERT INTO user_course(course) "
	 						+ "VALUES(?)";
	String CONVERT_COURSE	= "SELECT course_id "
							+ "FROM user_course "
							+ "WHERE course = ?";
	//Student
	String GET_ALL_USER 	= "SELECT student_id FROM users";
	String GET_STUDENTS		= "SELECT student_id, first_name, last_name, user_course.course "
							+ "FROM users "
							+ "JOIN user_course "
							+ "ON users.course = user_course.course_id "
							+ "ORDER BY `users`.`student_id` ASC";	
	String ADD_STUDENT		= "INSERT INTO users(student_id, first_name, last_name, course) "
							+ "VALUES (?, ?, ?, ?)";
	//Rooms
	String GET_ROOMS		= "SELECT room "
							+ "FROM request_rooms "
							+ "ORDER BY `request_rooms`.`room_id` ASC";	;
	String ADD_ROOM			= "INSERT INTO request_rooms(room) "
							+ "VALUES(?)";
	String CONVERT_ROOM		= "SELECT room_id "
							+ "FROM request_rooms "
							+ "WHERE room = ?";
	//Request
	String REQUEST_ITEM		= "INSERT INTO request(student_id, item_id, room, date_req, date_res, date_deadline, "
							+ "declined, allowed, returned) "
							+ "VALUES(?, ?, ?, ?, ?, ?, 0, 0, 0)";
	String GET_REQUEST		= "SELECT student_id, item_category.category_name, item_brand.brand_name, serial_no, property_no, request_rooms.room, date_req, date_res, date_deadline, "
							+ "declined, allowed, returned, request_id, request.item_id "
							+ "FROM request "
							+ "JOIN items "
							+ "ON items.item_id = request.item_id "
							+ "JOIN request_rooms "
							+ "ON request_rooms.room_id = request.room "
						 	+ "JOIN item_category "
						 	+ "ON items.category = item_category.category_id "
							+ "JOIN item_brand " 					
						 	+ "ON items.brand = item_brand.brand_id "
						 	+ "WHERE declined = 0 "
						 	+ "AND returned = 0 ";
	String DECLINED_REQUEST	= "UPDATE request "
							+ "SET declined = 1 "
							+ "WHERE request_id = ?";
	String ACCEPT_REQUEST	= "UPDATE request "
							+ "SET allowed = 1 "
							+ "WHERE request_id = ?";
	String RETURN_REQUEST	= "UPDATE request "
							+ "SET returned = 1 "
							+ "WHERE request_id = ?";
	//Date Deadline
	String DATE_DEADLINE	= "SELECT deadline_duration "
							+ "FROM admin_control";
	String UPDATE_DEADLINE	= "UPDATE admin_control "
							+ "SET deadline_duration = ?";
}
