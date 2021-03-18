package com.cg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSelect {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521";
		String user = "capgemini";
		String password = "cap123";
		String query = "select * from employee";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Established...");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
					while(rs.next()) {
						System.out.printf("%-5s %-12s %-12s %-12s %-12s %-5s",
								rs.getInt(1),rs.getString(2),
								rs.getString(3),rs.getLong(4),
								rs.getDate(5),rs.getInt(6));
						System.out.println();
					}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	}

}
