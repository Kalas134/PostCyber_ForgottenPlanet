package servicepkg;

import systempkg.SqliteConnect;
import systempkg.RaceTextXmlLoader;

import vopkg.RaceTextVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;

public class ViewRaceInfo {
	// 2. Race Info print
	
	public static void viewRaceInfo(String raceId, String lang) throws Exception {
		
		String dataSql =
				"SELECT RACE_STR, RACE_CON, RACE_AGI, RACE_DEX, RACE_INT, RACE_WIS, RACE_LUK " +
				"FROM Race_Basic_Stats WHERE RACE_ID = ?";
			
		try (Connection conn = SqliteConnect.getConnection();
			 PreparedStatement ps = conn.prepareStatement(dataSql)) {
			
			ps.setString(1, raceId);
			ResultSet rs = ps.executeQuery();
				
			if (rs.next()) {
				Map<String, RaceTextVO> raceTextMap =
						RaceTextXmlLoader.loadRaceTexts(lang);
					
				RaceTextVO text = raceTextMap.get(raceId);
					
				System.out.println("\n[" + text.getRaceName() + " Stats]");
				System.out.println(text.getRaceDesc() + "\n");
				System.out.println("STR: " + rs.getInt("RACE_STR"));
				System.out.println("CON: " + rs.getInt("RACE_CON"));
				System.out.println("AGI: " + rs.getInt("RACE_AGI"));
				System.out.println("DEX: " + rs.getInt("RACE_DEX"));
				System.out.println("INT: " + rs.getInt("RACE_INT"));
				System.out.println("WIS: " + rs.getInt("RACE_WIS"));
				System.out.println("LUK: " + rs.getInt("RACE_LUK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
