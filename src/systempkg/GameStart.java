package systempkg;

import java.util.List;
import java.util.ArrayList;

import vopkg.CharacterVO;
import vopkg.CharacterFinalStatsVO;
import viewpkg.CharacterStatusView;

public class GameStart {
	public static void run(List<CharacterVO> characters) {
		System.out.println("==Game Start==");
	
		// 출력 검사
	    
	    List<CharacterFinalStatsVO> finalStatsList = new ArrayList<>();
	    
	    for (CharacterVO vo : characters) {
	    	try {
	    		finalStatsList.add(CharacterStatsService.buildFinalStats(vo)
	    		);
	    	} catch (Exception e) {
	    		throw new RuntimeException("Failed to build stats for " + vo.getName(), e);
	    	}
	    }
	    CharacterStatusView.printCharacters(finalStatsList);
	
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
