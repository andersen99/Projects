package com.dao;

import java.util.*;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Issue;

public class IssueDao{
	public ArrayList<Issue> getAllIssues(){
		ArrayList<Issue> issues = new ArrayList<Issue>();
		PreparedStatement ps = null;
		Issue iss = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Issues");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int issue_id = rs.getInt("IssueId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				String issue_content = rs.getString("IssueContent");
				String issue_reply = rs.getString("IssueReply");
				int resolved = rs.getInt("Resolved");
				iss = new Issue(issue_id,guest_id,room_id,issue_content,issue_reply,resolved);
				issues.add(iss);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return issues;
	}
	public void insertIssue(String guestId,int roomId,String content) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = null;
			ps = conn.prepareStatement("INSERT INTO Issues (IssueId,GuestId,RoomId,IssueContent,IssueReply,Resolved) VALUES (Iss_Seq.nextval,?,?,?,'',0)");
			ps.setString(1, guestId);
			ps.setInt(2, roomId);
			ps.setString(3,content);
			ps.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public Issue getIssue(int issueId) {
		Issue iss = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Issues");
			ps.setInt(1, issueId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int issue_id = rs.getInt("IssueId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				String issue_content = rs.getString("IssueContent");
				String issue_reply = rs.getString("IssueReply");
				int resolved = rs.getInt("Resolved");
				iss = new Issue(issue_id,guest_id,room_id,issue_content,issue_reply,resolved);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return iss;
	}
	public void replyIssue(String reply,int issueId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = null;
			ps = conn.prepareStatement("UPDATE Issues SET IssueReply = ? WHERE IssueId = ?");
			ps.setString(1, reply);
			ps.setInt(2,issueId);
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("UPDATE Issues SET Resolved = 1 WHERE IssueId = ?");
			ps2.setInt(1, issueId);
			ps2.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public ArrayList<Issue> getGuestIssues(String guestId){
		ArrayList<Issue> Issues = new ArrayList<Issue>();
		PreparedStatement ps = null;
		Issue iss = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Issues WHERE GuestId = ?");
			ps.setString(1, guestId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int issue_id = rs.getInt("IssueId");
				String guest_id = rs.getString("GuestId");
				int room_id = rs.getInt("RoomId");
				String issue_content = rs.getString("IssueContent");
				String issue_reply = rs.getString("IssueReply");
				int resolved = rs.getInt("Resolved");
				iss = new Issue(issue_id,guest_id,room_id,issue_content,issue_reply,resolved);
				Issues.add(iss);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Issues;
	}
}