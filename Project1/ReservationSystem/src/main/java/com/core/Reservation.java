package com.core;

import java.util.Date;

public class Reservation{
	private int reserveId;
	private String guestId;
	private int roomId;
	private int status;
	private Date reserveDate;
	public Reservation(int reserve, String guest,int room,Date rDate,int status) {
		reserveId = reserve;
		guestId = guest;
		roomId = room;
		reserveDate = rDate;
        this.status = status;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setguestId(String newId) {
		guestId = newId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int newStatus) {
		status = newStatus;
	}
	public int getRoomId() {
		return roomId;
	}
	public int getReserveId() {
		return reserveId;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
}