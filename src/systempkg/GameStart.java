package systempkg;

import java.util.List;

import vopkg.DefaultCharacterVO;

public class GameStart {
	public static void run(List<DefaultCharacterVO> characters) {
		System.out.println("==Game Start==");
	
	
	// 1. Player load
	
	DefaultCharacterVO player = characters.stream()
			.filter(c -> c.getChar_ID() == 1)
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Player not found"));
	
	// 2. NPC load
	
	List<DefaultCharacterVO> npcs = characters.stream()
			.filter(c -> c.getChar_ID() != 1)
			.toList();
	
	System.out.println("Player Loaded: " + player.getChar_PresetName());
    System.out.println("NPC Count: " + npcs.size());

    // TODO:
    // World init
    // Turn loop
    // Command input
	}
}
