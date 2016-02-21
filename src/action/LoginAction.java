package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import security.Security;
import utility.SQLOperation;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware {
	
	// For SessionAware :: Reference = http://www.codejava.net/frameworks/struts/working-with-httpsession-in-struts2-a-login-example
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private String userId;
	
	public String execute() {
	  try {	
		if(userId.equals("LOGOUTUSER")) {
			if (session.containsKey("userIdSession")) {
	            session.remove("userIdSession");
	            return LOGIN;
	        }
		}
		
		String loggedUsername = null;
	
		Connection connection = SQLOperation.getConnection();
		
		if (connection != null) {
			ResultSet rs = SQLOperation.getAllUser();
			ResultSet rsForAdmin = SQLOperation.getAllAdmin();
			//if session exists
			if(session.containsKey("userIdSession")) {
				loggedUsername = (String) session.get("userIdSession");
				userId = loggedUsername;
				while(rs.next()) {
					if(userId.equals(rs.getString("student_id"))) {
						session.put("userIdSession", userId	);
						userId = Security.encrypt(rs.getString("student_id"));
						return "user";
					} 
				}
				while(rsForAdmin.next()) {
					String[] admin = userId.split("@");
					if(admin.length == 2) {
						System.out.println("asdsa");
					  if(admin[0].equals(rsForAdmin.getString("admin_id"))) {
							System.out.println("aTES");
						if(admin[1].equals(Security.decrypt(rsForAdmin.getString("password")))) {
							return "admin";
						}
					  }	
					}
				}
			} else { //if session doesn't exist -> create		
				while(rs.next()) {
					if(userId.equals(rs.getString("student_id"))) {
						session.put("userIdSession", userId	);
						userId = Security.encrypt(rs.getString("student_id"));
						return "user";
					} 
				}
				while(rsForAdmin.next()) {
					String[] admin = userId.split("@");
					if(admin.length == 2) {
						System.out.println("asdsa");
					  if(admin[0].equals(rsForAdmin.getString("admin_id"))) {
							System.out.println("aTES");
						if(admin[1].equals(Security.decrypt(rsForAdmin.getString("password")))) {
							return "admin";
						}
					  }	
					}
				}
			}
		} else {
			return LOGIN;
		}
		return LOGIN;

	  } catch (Exception e) {
		  System.out.println("Exception @ LoginAction: " + e.getMessage());
		  return LOGIN;
	  }
	}
	
	//Getters and Setters
	public String getUserId() {	return userId; }
	public void setUserId(String userId) { this.userId = userId; }
}
