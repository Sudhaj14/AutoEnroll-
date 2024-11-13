package com.vms.database;

import java.sql.*;

public class DBConnection {
	private static final String URL="jdbc:mysql://localhost:3306/VehicleManagementSystem";
	private static final String USER="root";
	private static final String PASSWORD="Root@123";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
}