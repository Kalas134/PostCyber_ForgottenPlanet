package daopkg;

import systempkg.SqliteConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import vopkg.RaceBasicStatsVO;

public class RaceInfoDAO {
	
	// 1. Race Index
	public static List<String> loadRaceIds()throws Exception {
		List<String> raceIds = new ArrayList<>();
		
		String listSql = "SELECT RACE_ID FROM Race_Basic_Stats ORDER BY RACE_ID";
		
		try (Connection conn = SqliteConnect.getConnection();
			 PreparedStatement ps = conn.prepareStatement(listSql);
			 ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				raceIds.add(rs.getString("Race_ID"));
			}
		}
		return raceIds;
	}
	
	// 2. Load Race Stats
	
	public static RaceBasicStatsVO loadRaceStats(String raceId) throws Exception {
		String sql = 
				"SELECT RACE_STR, RACE_CON, RACE_AGI, RACE_DEX, " +
				"RACE_INT, RACE_WIS, RACE_LUK" +
				"FROM RACE_Basic_Stats WHERE RACE_ID = ?";

		try (Connection conn = SqliteConnect.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, raceId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new RaceBasicStatsVO(
					raceId, 
					rs.getInt("RACE_STR"), 
					rs.getInt("RACE_CON"), 
					rs.getInt("RACE_AGI"), 
					rs.getInt("RACE_DEX"), 
					rs.getInt("RACE_INT"), 
					rs.getInt("RACE_WIS"), 
					rs.getInt("RACE_LUK")
				);
			}
		}
		throw new RuntimeException("Race not Found: " + raceId);
	}
}
