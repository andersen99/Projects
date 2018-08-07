package com.dao;

import java.util.*;
import java.util.Date;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Reservation;

public class ReservationDao{
	public ArrayList<Reservation> getAllReservations(){
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		PreparedStatement ps = null;
		Reservation r = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Reservations");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int reserve_id = rs.getInt("ReserveId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				//Date reserve_date = rs.getDate("ReserveDate");
				Date reserve_date = null;
				int status = rs.getInt("Status");
				r = new Reservation(reserve_id,guest_id,room_id,reserve_date,status);
				reservations.add(r);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return reservations;
	}
	public ArrayList<Reservation> getGuestReservations(String guestId) {
		Reservation reserve = null;
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reservations WHERE GuestId = ?");
			ps.setString(1, guestId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int reserve_id = rs.getInt("ReserveId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				//Date reserve_date = rs.getDate("ReserveDate");
				Date reserve_date = null;
				int reserveStatus = rs.getInt("Status");
				reserve = new Reservation(reserve_id,guest_id,room_id,reserve_date,reserveStatus);
				reservations.add(reserve);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return reservations;
	}
	public void insertReservation(int roomNum,String guestId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = null;
			ps = conn.prepareStatement("INSERT INTO Reservations (ReserveId,GuestId,RoomId,Status) VALUES (Res_Seq.nextval,?,?,0)");
			ps.setString(1, guestId);
			ps.setInt(2, roomNum);
			ps.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void updateReservation(int newStatus,int reserveNo) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("UPDATE Reservations SET Status = ? WHERE ReserveId = ?");
			ps.setInt(1,newStatus);
			ps.setInt(2,reserveNo);
			ps.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public Reservation getReservation(int reserveId) {
		PreparedStatement ps = null;
		Reservation res = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Reservations WHERE ReserveId = ?");
			ps.setInt(1,reserveId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int reserve_id = rs.getInt("ReserveId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				//Date reserve_date = rs.getDate("ReserveDate");
				Date reserve_date = null;
				int reserveStatus = rs.getInt("Status");
				res = new Reservation(reserve_id,guest_id,room_id,reserve_date,reserveStatus);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
}