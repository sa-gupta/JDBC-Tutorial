package com.cg.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class JdbcInsert {

	public static void main(String[] args) {
		ReadDbDetails rdb = new  ReadDbDetails();
		Map<String, String> map = rdb.getDbProp();
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName(map.get("DRIVER"));
			String query = 
					"insert into employee (ID,NAME,DESIG,MOBILE,DOJ,DEPT)values(?,?,?,?,?,?)";
			Connection conn = DriverManager.getConnection(map.get("URL"),map.get("USER"),map.get("PASSWORD"));
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter <ID> <NAME> <DESIG> <MOBILE> <DOJ> <DEPT>");
			stmt.setInt(1, scan.nextInt());
			stmt.setString(2, scan.next());
			stmt.setString(3, scan.next());
			stmt.setLong(4, scan.nextLong());
			stmt.setDate(5, Date.valueOf(scan.next()));
			stmt.setInt(6, scan.nextInt());
			int rows = stmt.executeUpdate();
			scan.close();
			if(rows>0) {
				System.out.println("Data inserted");
			}else
				System.out.println("Failed to insert");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
