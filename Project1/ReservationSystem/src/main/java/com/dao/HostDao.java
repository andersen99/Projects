package com.dao;

import java.util.*;
import java.sql.*;
import com.util.ConnectionUtil;
import com.core.Guest;
import com.core.Host;

public class HostDao{
	public ArrayList<Host> getAllHosts(){
		ArrayList<Host> Hosts = new ArrayList<Host>();
		PreparedStatement ps = null;
		Host h = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			ps = conn.prepareStatement("SELECT * FROM Hosts");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String host_id = rs.getString("HostId");
				String host_passwd = rs.getString("HostPword");
				String first_name = rs.getString("FirstName");
				String last_name = rs.getString("LastName");
				h = new Host(host_id,host_passwd,first_name,last_name);
				Hosts.add(h);
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Hosts;
	}
	public Host getHost(String hostId) {
		Host host = null;
		try(Connection conn = ConnectionUtil.getConnection()){
	
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Hosts WHERE HostId = ?");
			ps.setString(1,hostId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String host_id = rs.getString("HostId");
				String host_passwd = rs.getString("HostPword");
				String first_name = rs.getString("FirstName");
				String last_name = rs.getString("LastName");
				host = new Host(host_id,host_passwd,first_name,last_name);
			}
			else System.out.println("Not found in table?");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return host;
	}
}