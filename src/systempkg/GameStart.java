package systempkg;

import java.util.List;

import vopkg.CharacterVO;

public class GameStart {
	public static void run(List<CharacterVO> characters) {
		System.out.println("==Game Start==");
	
	
	// 1. Player load
	
	CharacterVO player = characters.stream()
			.filter(c -> c.getCharId() == 1)
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Player not found"));
	
	// 2. NPC load
	
	List<CharacterVO> npcs = characters.stream()
			.filter(c -> c.getCharId() != 1)
			.toList();
	
	System.out.println("Player Loaded: " + player.getName());
    System.out.println("NPC Count: " + npcs.size());

    // TODO:
    // World init
    // Turn loop
    // Command input
	}
}
