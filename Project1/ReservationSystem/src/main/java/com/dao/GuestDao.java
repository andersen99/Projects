package com.dao;

import java.util.*;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Guest;

public class GuestDao{
	public ArrayList<Guest> getAllGuests(){
		ArrayList<Guest> Guests = new ArrayList<Guest>();
		PreparedStatement ps = null;
		Guest g = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Guests");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String guest_id = rs.getString("GuestId");
				String guest_passwd = rs.getString("GuestPword");
				String first_name = rs.getString("FirstName");
				String last_name = rs.getString("LastName");
				g = new Guest(guest_id,guest_passwd,first_name,last_name);
				Guests.add(g);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Guests;
	}
	public Guest getGuest(String guestId) {
		Guest guest = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Guests WHERE GuestId = ?");
			ps.setString(1, guestId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String guest_id = rs.getString("GuestId");
				String guest_passwd = rs.getString("GuestPword");
				String first_name = rs.getString("FirstName");
				String last_name = rs.getString("LastName");
				guest = new Guest(guest_id,guest_passwd,first_name,last_name);
			}
			else System.out.println("Not found in table?");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return guest;
	}
	public void updateGuest(String newData, String guestId, String column) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			if(column.equals("userId"))	ps = conn.prepareStatement("UPDATE Guests SET GuestId = ? WHERE GuestId = ?");
			if(column.equals("userPword"))ps =conn.prepareStatement("UPDATE Guests SET GuestPword = ? WHERE GuestId = ?");
			if(column.equals("fName"))	ps = conn.prepareStatement("UPDATE Guests SET FirstName = ? WHERE GuestId = ?");
			if(column.equals("lName"))	ps = conn.prepareStatement("UPDATE Guests SET LastName = ? WHERE GuestId = ?");
			ps.setString(1,newData);
			ps.setString(2,guestId);
			ps.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}