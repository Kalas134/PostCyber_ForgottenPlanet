package main;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import testbuild.CharacterXmlGenerator;
import testbuild.SaveSlotGenerator;

import systempkg.PlayerCustomize;

import vopkg.DefaultCharacterVO;

import daopkg.DefaultCharacterLoader;
import daopkg.RaceInfoDAO;

public class ConsoleMain {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		ConsoleMain main = new ConsoleMain();
		main.run();
	}
	
	private void run() {
		while (true) {
			printMainMenu();
			int choice = inputInt("메뉴 선택");
			
			switch (choice) {
				case 1 -> startNewGame();
				case 2 -> loadGame();
				case 3 -> showOption();
				case 0 -> exitGame();
				default -> System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	private void printMainMenu() {
		System.out.println("\n===============");
		System.out.println("Post-Cyber : Forgotten Planet");
		System.out.println("===============");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Option");
		System.out.println("0. Exit");
		System.out.println("===============");
	}
	
	private void startNewGame() {
		System.out.println("[New Game Starting...]");
		
		try {
			// 1. SaveSlotGenerator
			Path saveRoot = Path.of("save");
			Path slotDir = SaveSlotGenerator.createNewSaveSlot(saveRoot);
			
			System.out.println("Save Slot Created : " + slotDir);
			
			// 2. DefaultCharacterLoader
			DefaultCharacterLoader loader = new DefaultCharacterLoader();
		//	DefaultCharacterVO basePlayer = loader.loadDefaultPlayer(); << 1명만 로드
			
		//	전체 디폴트 캐릭터 로드 (Player + NPC)
			
			List<DefaultCharacterVO> defaultCharacters =
					loader.loadAllDefaultCharacters();
			
			// 3. PlayerCustomize
			System.out.print("Player Name? ▶ ");
			String name = sc.nextLine();
			
			List<String> raceIds = RaceInfoDAO.loadRaceIds();
			
			System.out.println("Race Select");
			for (int i = 0; i < raceIds.size(); i++) {
				System.out.println((i + 1) + ". " + raceIds.get(i));
			}
			int raceChoice;
			while (true) {
				raceChoice = inputInt("Race Number");
				if (raceChoice >=1 && raceChoice <= raceIds.size()) break;
				System.out.println("Wrong selected.");
			}
			
			String selectRaceId = raceIds.get(raceChoice - 1);
			// 여기에 종족 정보 올릴 것
		
			System.out.print("Gender?(0=none, 1=Male, 2=Female) ▶ ");
			Integer gender = inputInt("Gender");
			
			List<DefaultCharacterVO> finalCharacters = new java.util.ArrayList<>();
			for (DefaultCharacterVO ch : defaultCharacters) {
				if (ch.getChar_ID() == 1) {
					DefaultCharacterVO customizedPlayer =
							PlayerCustomize.apply(ch, name, selectRaceId, gender);
					finalCharacters.add(customizedPlayer);
				} else {
					finalCharacters.add(ch);
				}
			}
		
			// 4. CharacterXmlGenerator
			Path characterXml = slotDir.resolve("characters.xml");
			
			CharacterXmlGenerator.generateNewGameCharacterXml(
					characterXml, 
					finalCharacters
					);
			
			System.out.println("Character XML Saved.");
			
			// 5. GameStart
			System.out.println("Game Start!");
			// TODO: GameStart.run(characterXml);
			
		} catch (Exception e) {
			System.out.println("New Game Error");
			e.printStackTrace();
		}
	}
	
	private void loadGame() {
		System.out.println("[Load Game]");
		// TODO:
		// 1. SaveSlot 선택
		// 2. SaveXmlLoader (XML → VO)
		// 3. GameStart
	}
	
	private void showOption() {
		System.out.println("[Option]");
		// TODO:
		// Sound / Language / Difficulty Level
	}
	
	private void exitGame() {
		System.out.println("Game Exit");
		sc.close();
		System.exit(0);
	}
	
	private int inputInt (String label) {
		while (true) {
			try {
                System.out.print(label + " ▶ ");
                return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
			}
		}
	}
}
