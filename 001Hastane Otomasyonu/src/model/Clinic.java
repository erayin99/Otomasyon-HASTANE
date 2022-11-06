package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class Clinic {
	
	private int cid;
	private String cname;
	DBConnection conn = new DBConnection();
	
	Statement st = null;
	ResultSet rs = null;;
	PreparedStatement preparestatement = null;
	public Clinic() {
		
	}
	
	
	public Clinic(int cid, String cname) {
		super();
		
		this.cid = cid;
		this.cname = cname;
	}
	public ArrayList<Clinic> getList() throws SQLException{
		ArrayList<Clinic> list = new ArrayList<>();		
		Clinic obj;
		Connection con = conn.connDb();
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM public.clinic");
			while(rs.next()){
				obj = new Clinic();
				obj.setCid(rs.getInt("cid"));
				obj.setCname(rs.getString("cname"));
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
	
	public boolean addClinic(String cname) throws SQLException {
		String query = "INSERT INTO public.clinic" + "(cname) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparestatement = con.prepareStatement(query);
			preparestatement.setString(1, cname);
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
	public boolean deleteClinic(int cid) throws SQLException {
		String query = "DELETE FROM public.clinic WHERE cid=?";
		boolean key = false;
		Connection con = conn.connDb();
		
		try {
			st = con.createStatement();
			preparestatement = con.prepareStatement(query);
			preparestatement.setInt(1, cid);
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

	public boolean updateClinic(int cid, String cname) throws SQLException {
		String query = "UPDATE public.clinic SET cname =?  WHERE cid=?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparestatement = con.prepareStatement(query);;
			preparestatement.setString(1, cname);
			preparestatement.setInt(2, cid);
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
	public Clinic getFetch(int cid) {
		Connection con = conn.connDb();
		Clinic c = new Clinic();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic where cid=" + cid);
			while(rs.next()){
				c.setCid(rs.getInt("cid"));
				c.setCname(rs.getString("cname"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
		
		
	}
	public ArrayList<User> getClinicDoktorList(int clinic_id) throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		
		
		User obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.tcno,u.password,u.name,u.type from worker w LEFT JOIN public.user u ON w.user_id= u.id where clinic_id=" + clinic_id);
			while(rs.next()){
				obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("password"),rs.getString("name"),rs.getString("type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}	
	
	public int getCid() {
		return cid;
	}
	


	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}
	
	

}
