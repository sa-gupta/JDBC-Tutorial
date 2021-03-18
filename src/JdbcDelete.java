package com.cg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class JdbcDelete {

	public static void main(String[] args) {
		ReadDbDetails rdb = new  ReadDbDetails();
		Map<String, String> map = rdb.getDbProp();
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName(map.get("DRIVER"));
			String query = 
					"delete from employee where id=?";
			Connection conn = DriverManager.getConnection(map.get("URL"),map.get("USER"),map.get("PASSWORD"));
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter <ID> to Delete");
			stmt.setInt(1, scan.nextInt());
			int rows = stmt.executeUpdate();
			System.out.println("Do you really want to delete.  y/n ");
			String opt = scan.next();
			scan.close();
//			System.out.println(opt == "Y");
//			if(rows>0) {
//				if(opt == "y") {
//					conn.commit();
//					System.out.println("Row Deleted");
//				}else {
//					conn.rollback();
//					conn.commit();
//				}
//				
//			}else {
//				System.out.println("Failed to Delete");
//				conn.commit();
//			}
			
			if(opt.equals("y")) {
				if(rows>0) {
					System.out.println("Row Deleted.");
					conn.commit();
				}else
					System.out.println("Failed to delete.");
			}else if(opt.equals("n")) {
				conn.rollback();
			}else
				System.out.println("else block");
				
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
