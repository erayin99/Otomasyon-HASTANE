package model;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class Appointment {

	private int id, doktorid, hastaid;
	private String doktorname, hastaname, appdate;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;;
	PreparedStatement preparestatement = null;
	public Appointment() {
		
	}
	
	public Appointment(int id, int doktorid, int hastaid, String doktorname, String hastaname, String appdate) {
		super();
		this.id = id;
		this.doktorid = doktorid;
		this.hastaid = hastaid;
		this.doktorname = doktorname;
		this.hastaname = hastaname;
		this.appdate = appdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoktorid() {
		return doktorid;
	}

	public void setDoktorid(int doktorid) {
		this.doktorid = doktorid;
	}

	public int getHastaid() {
		return hastaid;
	}

	public void setHastaid(int hastaid) {
		this.hastaid = hastaid;
	}

	public String getDoktorname() {
		return doktorname;
	}

	public void setDoktorname(String doktorname) {
		this.doktorname = doktorname;
	}

	public String getHastaname() {
		return hastaname;
	}

	public void setHastaname(String hastaname) {
		this.hastaname = hastaname;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}
	
	
	public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException{
		ArrayList<Appointment> list = new ArrayList<>();		
		Appointment obj;
		Connection con = conn.connDb();
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id=" + hasta_id);
			while(rs.next()){
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoktorid(rs.getInt("doktor_id"));
				obj.setDoktorname(rs.getString("doktor_name"));
				obj.setHastaid(rs.getInt("hasta_id"));
				obj.setHastaname(rs.getString("hasta_name"));
				obj.setAppdate(rs.getString("app_date"));
				
				list.add(obj);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
		
	}
	public ArrayList<Appointment> getDoktorList(int doktor_id) throws SQLException{
		ArrayList<Appointment> list = new ArrayList<>();		
		Appointment obj;
		Connection con = conn.connDb();
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doktor_id=" + doktor_id);
			while(rs.next()){
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoktorid(rs.getInt("doktor_id"));
				obj.setDoktorname(rs.getString("doktor_name"));
				obj.setHastaid(rs.getInt("hasta_id"));
				obj.setHastaname(rs.getString("hasta_name"));
				obj.setAppdate(rs.getString("app_date"));
				
				list.add(obj);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
		
	}

	
}

