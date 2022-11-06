package helper;

import java.sql.*;

public class DBConnection {
	Connection c= null;
	
	public DBConnection() {
		
	}

	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hastane?user=postgres&password=erdal");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c; 
		
	}
}
