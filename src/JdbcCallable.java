package com.cg.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Scanner;

public class JdbcCallable {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReadDbDetails rdb = new ReadDbDetails();
		Map<String,String> mapDetails = rdb.getDbProp();
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			Class.forName(mapDetails.get("DRIVER"));
			String query = "call get_allowance(?,?,?)";

			conn = DriverManager.getConnection
					(mapDetails.get("URL"), mapDetails.get("USER"), mapDetails.get("PASSWORD"));
			stmt = conn.prepareCall(query);
			System.out.println("Enter <ID> to get Allowance");
			int id = scan.nextInt();
			stmt.setInt(1,id);
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.registerOutParameter(3, Types.VARCHAR);
			stmt.executeUpdate();
			int allowance = stmt.getInt(2);
			String msg = stmt.getString(3);
			if(msg.equals("noerror")) {
			   System.out.println("Employee with id "+id+" gets allowance: "+allowance);
			}
			else 
				System.out.println(msg);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) { try {conn.close();} catch (SQLException e) {}}
			scan.close();
		}
	}

}
