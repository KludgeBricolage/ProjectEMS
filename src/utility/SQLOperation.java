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

public class SQLOperation implements SQLCommands {
private static Connection connection;
	
	private SQLOperation() {}
	
	//connection
	private static Connection getDBConnection() {
		try {
		    DataSource dataSource = (DataSource) 
		    	InitialContext.doLookup("java:comp/env/jdbc/chitanda");
		    
		    if (dataSource != null) {
		    	connection = dataSource.getConnection();
		    } else {
		    	throw new NullPointerException("DataSource is NULL."); 
		    }
		} catch (NamingException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return (connection != null) ? connection : getDBConnection();
	}
	//

}
