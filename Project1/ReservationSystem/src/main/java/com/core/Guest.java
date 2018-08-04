package com.core;
public class Guest{
	private String guestId;
	private String guestPasswd;
	private String fName;
	private String lName;
	public Guest(String guest,String pWord,String first,String last) {
		guestId = guest;
		guestPasswd = pWord;
		fName = first;
		lName = last;
	}
	public String getName() {
		return fName + " " + lName;
	}
	public String getGuestId() {
		return guestId;
	}
	public String getGuestPword() {
		return guestPasswd;
	}
	public void setPword(String pWord) {
		guestPasswd = pWord;
	}
}