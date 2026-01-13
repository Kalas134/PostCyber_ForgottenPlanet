package systempkg;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnect {
		
		// SQLite JDBC URL
		private static final String URL = "jdbc:sqlite:resource/db/PCFPdb.db";
		
		// instance prevention
		private SqliteConnect() {}
		
		public static Connection getConnection() throws Exception {
			return DriverManager.getConnection(URL);
		}
}
