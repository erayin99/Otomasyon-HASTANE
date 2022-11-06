package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;


public class Whour {
	
	private int wid, doktor_id;
	private String doktor_name, wdate;
	private boolean wstatus;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;;
	PreparedStatement preparestatement = null;
	
	
	public Whour() {
		
	}


	public Whour(int wid, int doktor_id, String doktor_name, String wdate, boolean wstatus) {
		super();
		this.wid = wid;
		this.doktor_id = doktor_id;
		this.doktor_name = doktor_name;
		this.wdate = wdate;
		this.wstatus = wstatus;
	}


	public int getWid() {
		return wid;
	}


	public void setWid(int wid) {
		this.wid = wid;
	}


	public int getDoktor_id() {
		return doktor_id;
	}


	public void setDoktor_id(int doktor_id) {
		this.doktor_id = doktor_id;
	}


	public String getDoktor_name() {
		return doktor_name;
	}


	public void setDoktor_name(String doktor_name) {
		this.doktor_name = doktor_name;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public boolean isWstatus() {
		return wstatus;
	}


	public void setWstatus(boolean wstatus) {
		this.wstatus = wstatus;
	}
	
	public ArrayList<Whour> getWhourList(int doktor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		
		
		Whour obj;
		try {
			Connection con = conn.connDb();	
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

}
