package com.core;
public class Issue{
	private int issueId;
	private String guestId;
	private int roomId;
	private String issueContent;
	private boolean isResolved;
	public Issue(int issue,String guest,int room,String content) {
		issueId = issue;
		guestId = guest;
		roomId = room;
		issueContent = content;
		isResolved = false;
	}
	public int getIssueId() {
		return issueId;
	}
	public String getGuestId() {
		return guestId;
	}
	public int getRoomId() {
		return roomId;
	}
	public boolean getResolved() {
		return isResolved;
	}
	public String getContent() {
		return issueContent;
	}
}