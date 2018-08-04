package com.core;
public class Reply{
	private int replyId;
	private int issueId;
	private int hostId;
	public Reply(int reply,int issue,int host) {
		replyId = reply;
		issueId = issue;
		hostId = host;
	}
	public int getReplyId() {
		return replyId;
	}
	public int getIssueId() {
		return issueId;
	}
	public int getHostId() {
		return hostId;
	}
}