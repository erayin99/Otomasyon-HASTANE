package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doktor extends User{
	
	
	Statement st = null;
	ResultSet rs = null;;
	Connection con = conn.connDb();
	PreparedStatement preparestatement = null;
	public Doktor() {
		super();
	
	}
	public Doktor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		
	}
	public boolean addwhour(int doktor_id, String doktor_name, String wdate) throws SQLException{
		
		int key = 0;
		int count = 0;
		String querry = "INSERT INTO whour" + "(doktor_id,doktor_name,wdate) VALUES" + "(?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE wstatus='true' AND doktor_id =" 
			+ doktor_id + "AND wdate ='" + wdate +"'");	
			
			while(rs.next()) {
				count++;
				break;				
			}
			if(count ==0) {
				preparestatement = con.prepareStatement(querry);
				preparestatement.setInt(1, doktor_id);
				preparestatement.setString(2, doktor_name);
				preparestatement.setString(3, wdate);
				preparestatement.executeUpdate();
			}
			key =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			
		if(key ==1) 
			return true;
		else 
			return false;
			
		
	
	}
	public ArrayList<Whour> getWhourList(int doktor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		
		
		Whour obj;
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE wstatus='true' AND doktor_id=" + doktor_id);
			while(rs.next()){
				obj = new Whour();
				obj.setWid(rs.getInt("wid"));
				obj.setDoktor_id(rs.getInt("doktor_id"));
				obj.setDoktor_name(rs.getString("doktor_name"));
				obj.setWstatus(rs.getBoolean("wstatus"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public boolean deleteWhour(int wid) throws SQLException {
		String query = "DELETE FROM whour WHERE wid=?";
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparestatement = con.prepareStatement(query);
			preparestatement.setInt(1, wid);
			preparestatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(key) 
			return true;
		else
			return true;
		
		
	}
}
