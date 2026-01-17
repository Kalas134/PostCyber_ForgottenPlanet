package daopkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import systempkg.SqliteConnect;
import vopkg.DefaultCharacterVO;

public class DefaultCharacterLoader {
	
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
			Connection conn = SqliteConnect.getConnection();
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

	public List<DefaultCharacterVO> loadAllDefaultCharacters() {
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
				ORDER BY Char_ID
				""";
		
		List<DefaultCharacterVO> characters = new java.util.ArrayList<>();
		
		try (
			Connection conn = SqliteConnect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				DefaultCharacterVO character = new DefaultCharacterVO(
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
				
				characters.add(character);
			}
			
			if (characters.isEmpty()) {
				throw new RuntimeException("No default Characters found in DB");
			}
			
			return characters;
		} catch (Exception e) {
			throw new RuntimeException("Failed to load default characters", e);
		}
	}
}
