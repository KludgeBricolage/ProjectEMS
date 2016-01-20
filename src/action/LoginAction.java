package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.Action;

import security.Security;
import utility.SQLOperation;

public class LoginAction implements Action {
	private String userId;
	private String formResult = "empty";
	
	public String execute() {
		
		Connection connection = SQLOperation.getConnection();
		String ret = LOGIN;
		if(formResult.equals("empty")) {
			if (connection != null) {	
				ResultSet rs = SQLOperation.getAllUser();
				ResultSet rsForAdmin = SQLOperation.getAllAdmin();
				try {
					while(rs.next()) {
						if(userId.equals(rs.getString("student_id"))) {
							userId = Security.encrypt(rs.getString("student_id"));
							return "user";
						} 
					}
					while(rsForAdmin.next()) {
						if(userId.equals(rsForAdmin.getString("admin_id"))) {
							return "admin";
						}	
					}
				} catch (SQLException e) {
					e.printStackTrace(); //TODO CHANGE THIS
				}	
			} else {
				return ERROR;
			}
		} else {
			formResult = Security.encrypt(formResult);
			ret = "send";
		}
		return ret;
	}
	
	//Getters and Setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFormResult() {
		return formResult;
	}

	public void setFormResult(String formResult) {
		this.formResult = formResult;
	}
}
