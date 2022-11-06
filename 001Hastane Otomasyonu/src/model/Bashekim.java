package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User{
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;;
	PreparedStatement preparestatement = null;
	public Bashekim() {
		
	}	
	
	public ArrayList<User> getDoktorList() throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		
		
		User obj;
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM public.user where type ='Doktor'");
			while(rs.next()){
				obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("password"),rs.getString("name"),rs.getString("type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<User> getClinicDoktorList(int clinic_id) throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		
		
		User obj;
		try {
			
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
		public boolean addDoctor(String tcno, String password, String name) throws SQLException {
			String query = "INSERT INTO public.user" + "(tcno, password, name, type) VALUES" + "(?,?,?,?)";
			boolean key = false;
			
			try {
				st = con.createStatement();
				preparestatement = con.prepareStatement(query);
				
				preparestatement.setString(1, tcno);
				preparestatement.setString(2, password);
				preparestatement.setString(3, name);
				preparestatement.setString(4,"Doktor");
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

		public boolean deleteDoctor(int id) throws SQLException {
			String query = "DELETE FROM public.user WHERE id=?";
			boolean key = false;
			
			try {
				st = con.createStatement();
				preparestatement = con.prepareStatement(query);
				preparestatement.setInt(1, id);
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
		public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
			String query = "UPDATE public.user SET name = ?, tcno =?, password =? WHERE id=?";
			boolean key = false;
			
			try {
				st = con.createStatement();
				preparestatement = con.prepareStatement(query);
				preparestatement.setString(1, name);
				preparestatement.setString(2, tcno);
				preparestatement.setString(3, password);
				preparestatement.setInt(4, id);
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
		public boolean addworker(int user_id, int clinic_id) throws SQLException {
			String query = "INSERT INTO worker" + "(user_id, clinic_id) VALUES" + "(?,?)";
			boolean key = false;
			int count = 0;
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM worker where clinic_id=" + clinic_id + "AND user_id=" + user_id);
				while(rs.next()) {
					count++;
					
				}
				if(count == 0) {
					preparestatement = con.prepareStatement(query);
					preparestatement.setInt(1, user_id);
					preparestatement.setInt(2, clinic_id);
					preparestatement.executeUpdate();
				}
				
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
