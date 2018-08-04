package com.util;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		//Properties prop = new Properties();
		//InputStream in = new FileInputStream("../../../ReservationSystem/connection.properties");
		//prop.load(in);
		
		//String url = prop.getProperty("url");
		//String user = prop.getProperty("user");
		//String password = prop.getProperty("password");
		String url = "jdbc:oracle:thin:@instance-oracle-1.cqgxggprasjz.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "banders";
		String password = "blackclover";
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(url, user, password);
	}
}