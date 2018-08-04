package com.dao;

import java.util.*;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Room;

public class RoomDao{
	public ArrayList<Room> getAllRooms(){
		ArrayList<Room> Rooms = new ArrayList<Room>();
		PreparedStatement ps = null;
		Room r = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Rooms");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				String image = rs.getString("Image");
				r = new Room(room_id,guest_id,image);
				Rooms.add(r);
			}
			rs.close();	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Rooms;
	}
	public ArrayList<Room> getAvailableRooms(){
		ArrayList<Room> Rooms = new ArrayList<Room>();
		PreparedStatement ps = null;
		Room r = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Rooms WHERE GuestId = null");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				String image = rs.getString("Image");
				r = new Room(room_id,null,image);
				Rooms.add(r);
			}
			rs.close();	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Rooms;
	}
}