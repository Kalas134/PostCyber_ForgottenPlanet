package daopkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vopkg.DefaultCharacterVO;

public class DefaultCharacterLoader {
	
	private final String dbUrl;
	
	public DefaultCharacterLoader(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	private Connection connect() throws Exception {
		return DriverManager.getConnection(dbUrl);
	}
	
	public DefaultCharacterVO loadDefaultPlayer() {
		String sql = """
				SELECT 
					Char_ID, 
					Char_PresetName, 
					Char_RaceID, 
					Char_Gender, 
					Char_DefaultLevel, 
					Char_DefAddedSTR, 
					Char_DefAddedCON, 
					Char_DefAddedAGI, 
					Char_DefAddedDEX, 
					Char_DefAddedINT, 
					Char_DefAddedWIS, 
					Char_DefAddedLUK 
				FROM Character_Default_Level 
				WHERE Char_ID = 1
				""";
		try (
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			if (!rs.next()) {
				throw new RuntimeException(
						"Default player (Char_ID = 1) not found in DB");
			}
			
			return new DefaultCharacterVO(
					rs.getInt("Char_ID"),
					rs.getString("Char_PresetName"),
					rs.getString("Char_RaceID"),
					rs.getInt("Char_Gender"),
					rs.getInt("Char_DefaultLevel"),
					rs.getInt("Char_DefAddedSTR"),
					rs.getInt("Char_DefAddedCON"),
					rs.getInt("Char_DefAddedAGI"),
					rs.getInt("Char_DefAddedDEX"),
					rs.getInt("Char_DefAddedINT"),
					rs.getInt("Char_DefAddedWIS"),
					rs.getInt("Char_DefAddedLUK")
			);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load default player", e);
		}
	}
}
