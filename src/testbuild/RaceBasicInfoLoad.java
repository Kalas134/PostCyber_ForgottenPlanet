package testbuild;

import systempkg.SqliteConnect;
import systempkg.RaceTextXmlLoader;

import vopkg.RaceTextVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// After, Change to "RaceInFoDAO"
public class RaceBasicInfoLoad {
	public static void main(String[] args) {
		List<String> RACE_IDs = new ArrayList<>();
		// 1. Race Index output
		String listSql = "SELECT RACE_ID FROM Race_Basic_Stats ORDER BY RACE_ID";
		
		try (Connection conn = SqliteConnect.getConnection();
			 PreparedStatement ps = conn.prepareStatement(listSql);
			 ResultSet rs = ps.executeQuery()) {
			System.out.println("===Race Index===");
			int index = 1;
			while (rs.next()) {
				String RaceID = rs.getString("RACE_ID");
				RACE_IDs.add(RaceID);
				System.out.println(index + ". " + RaceID);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		if(RACE_IDs.isEmpty()) {
			System.out.println("There is no race data.");
			return;
		}
		
		// 2. User output
		Scanner sc = new Scanner(System.in);
		System.out.println("Selecte Race number: ");
		int choice = sc.nextInt();
		
		if (choice < 1 || choice > RACE_IDs.size()) {
			System.out.println("It's a wrong choice.");
			return;
		}
		
		String selectedRaceID = RACE_IDs.get(choice-1);
		
		// 3. Selected Race Stats output
		
		String dataSql =
				"SELECT RACE_STR, RACE_CON, RACE_AGI, RACE_DEX, RACE_INT, RACE_WIS, RACE_LUK " +
				"FROM Race_Basic_Stats WHERE RACE_ID = ?";
		
		try (Connection conn = SqliteConnect.getConnection();
			 PreparedStatement ps = conn.prepareStatement(dataSql)) {
			
			ps.setString(1, selectedRaceID);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Map<String, RaceTextVO> raceTextMap =
						RaceTextXmlLoader.loadRaceTexts("ko_KR");
				
				RaceTextVO text = raceTextMap.get(selectedRaceID);
				
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
