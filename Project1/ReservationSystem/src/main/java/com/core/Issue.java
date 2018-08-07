package com.core;
public class Issue{
	private int issueId;
	private String guestId;
	private int roomId;
	private String issueContent;
	private String issueReply;
	private int isResolved;
	public Issue(int issue,String guest,int room,String content,String reply,int resolve) {
		issueId = issue;
		guestId = guest;
		roomId = room;
		issueContent = content;
		issueReply = reply;
		isResolved = resolve;
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
	public int getResolved() {
		return isResolved;
	}
	public String getContent() {
		return issueContent;
	}
	public String getReply() {
		return issueReply;
	}
}