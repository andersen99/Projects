package com.core;
public class Host{
	private String hostId;
	private String hostPasswd;
	private String fName;
	private String lName;
	public Host(String host,String pWord,String first,String last) {
		hostId = host;
		hostPasswd = pWord;
		fName = first;
		lName = last;
	}
	public String getHostId() {
		return hostId;
	}
	public String getHostPword() {
		return hostPasswd;
	}
	public String getName() {
		return fName + " " + lName;
	}
}