package com.dao;

import java.util.*;
import java.util.Date;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Reservation;

public class ReservationDao{
	public ArrayList<Reservation> getAllReservations(){
		ArrayList<Reservation> Reservations = new ArrayList<Reservation>();
		PreparedStatement ps = null;
		Reservation r = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Reservations");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int reserve_id = rs.getInt("ReserveId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				Date reserve_date = rs.getDate("ReserveDate");
				int status = rs.getInt("Status");
				r = new Reservation(reserve_id,guest_id,room_id,reserve_date,status);
				Reservations.add(r);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Reservations;
	}
	public void insert(int reserve,String guest,int room,Date reserveDate) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Reservations (ReserveId,GuestId,RoomId,Status,DateReserved) VALUES (reserve,guest,room,0,reserveDate)");
			ps.executeUpdate();
			conn.commit();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public Reservation getGuestReserve(int guestId) {
		Reservation reserve = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reservations WHERE GuestId == guestId");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int reserve_id = rs.getInt("ReserveId");
				String guest_id = rs.getString("GuestID");
				int room_id = rs.getInt("RoomId");
				Date reserve_date = rs.getDate("ReserveDate");
				int reserveStatus = rs.getInt("Status");
				reserve = new Reservation(reserve_id,guest_id,room_id,reserve_date,reserveStatus);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return reserve;
	}
	public void updateColumn(String roomNum,String newData) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = null;
			// needs to be corrected after reservations table is changed
			ps = conn.prepareStatement("UPDATE Reservations SET UserId = ?");
			ps.executeUpdate();
			conn.commit();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}