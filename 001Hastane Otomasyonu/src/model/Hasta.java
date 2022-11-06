package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helper.Helper;

public class Hasta extends User{
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;;
	PreparedStatement preparestatement = null;
	
	public Hasta() {
		
	}

	public Hasta(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		
	}
public boolean addappointment(int doktor_id, int hasta_id, String doktor_name, String hasta_name, String appdate) throws SQLException{
		
		int key = 0;
		
		String query = "INSERT INTO appointment (doktor_id, doktor_name, hasta_id, hasta_name,app_date) VALUES (?,?,?,?,?)";
		try {
		
		
			
				preparestatement = con.prepareStatement(query);
				preparestatement.setInt(1, doktor_id);
				preparestatement.setString(2, doktor_name);
				preparestatement.setInt(3, hasta_id);
				preparestatement.setString(4, hasta_name);
				preparestatement.setString(5, appdate);
				preparestatement.executeUpdate();
				key =1;		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			
		if(key == 1) 
			return true;
		else 
			return false;
					
	
	}
	
public boolean updateWhourStatus(int doktor_id, String wdate) throws SQLException{
	
	int key = 0;
	
	String query = "UPDATE whour SET wstatus = ? WHERE doktor_id = ? AND wdate= ? ";
	try {
	
		
			preparestatement = con.prepareStatement(query);
			
			preparestatement.setBoolean(1, false);
			preparestatement.setInt(2, doktor_id);
			preparestatement.setString(3, wdate);
			preparestatement.executeUpdate();
			key =1;		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		
	if(key == 1) 
		return true;
	else 
		return false;
				

}

public boolean addregister(String tcno, String password, String name) throws SQLException{
		
		int key = 0;
		boolean duplicate = true;
		String query = "INSERT INTO hasta (tcno, password, name, type) VALUES (?,?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM hasta WHERE tcno='" + tcno +"'");	
			while(rs.next()) {
				duplicate = false;
				Helper.SHOWmSG("Bu TC Numarasina ait bir kayit bulunmaktadir");	
				break;				
			}
			if(duplicate == true) {
					preparestatement = con.prepareStatement(query);
				preparestatement.setString(1, tcno);
				preparestatement.setString(2, password);
				preparestatement.setString(3, name);
				preparestatement.setString(4, "hasta");
				preparestatement.executeUpdate();
				key =1;		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			
		if(key == 1) 
			return true;
		else 
			return false;
					
	
	}
	
}
