package systempkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteConnect {
	public static void main(String[] args) {
		
		// SQLite JDBC URL
		String url = "jdbc:sqlite:resource/db/PCFPdb.db";
		
		// 1. test connected
		try (Connection conn = DriverManager.getConnection(url)){
			System.out.println("✅ SQLite DataBase Connected Success.");
			// 2. simple query test
			try (Statement stmt = conn.createStatement()){
				// SQLite Version Checker
				ResultSet rs = stmt.executeQuery("SELECT sqlite_version();");
				if (rs.next()) {
					System.out.println("SQLite Version: " + rs.getString(1));
				}
			}
		} catch (Exception e) {
			System.out.println("❌ SQlite DataBase Connect Fail.");
			e.printStackTrace();
		}
	}
}
