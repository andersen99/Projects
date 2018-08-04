package com.core;
public class Room{
	private int roomId;
	private String guestId;
	private String image;
	public Room(int room, String guest, String path) {
		guestId = guest;
		roomId = room;
        image = null;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String newId) {
		guestId = newId;
	}
	public int getRoomId() {
		return roomId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String path) {
		image = path;
	}
}