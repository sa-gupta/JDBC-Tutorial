package com.cg.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class JdbcUpdate {

	public static void main(String[] args) {
		ReadDbDetails rdb = new  ReadDbDetails();
		Map<String, String> map = rdb.getDbProp();
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName(map.get("DRIVER"));
			String query = 
					"update employee set desig=?,mobile=? where id=?";
			Connection conn = DriverManager.getConnection(map.get("URL"),map.get("USER"),map.get("PASSWORD"));
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter Your <ID>");
			int id = scan.nextInt();
			System.out.println("Enter <DESIG> <MOBILE>");
			stmt.setString(1, scan.next());
			stmt.setLong(2, scan.nextLong());
			stmt.setInt(3, id);
			int rows = stmt.executeUpdate();
			scan.close();
			if(rows>0) {
				System.out.println("Data Updated");
			}else
				System.out.println("Failed to Update");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
