package com.cg.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class JdbcFunctionCallable {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReadDbDetails rdb = new ReadDbDetails();
		Map<String, String> mapDetails = rdb.getDbProp();
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			Class.forName(mapDetails.get("DRIVER"));
			String query = "call get_city(?)";

			conn = DriverManager.getConnection(mapDetails.get("URL"), mapDetails.get("USER"),
					mapDetails.get("PASSWORD"));
			stmt = conn.prepareCall(query);
			System.out.println("Enter <ID> to get city he/she lives in");
			int eid = scan.nextInt();
			stmt.setInt(1, eid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString(1));
			} else
				System.out.println("No data available");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
			scan.close();
		}

	}

}
