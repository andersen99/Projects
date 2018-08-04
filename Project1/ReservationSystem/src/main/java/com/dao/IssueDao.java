package com.dao;

import java.util.*;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Issue;

public class IssueDao{
	public ArrayList<Issue> getAllIssues(){
		ArrayList<Issue> Issues = new ArrayList<Issue>();
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
				iss = new Issue(issue_id,guest_id,room_id,issue_content);
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