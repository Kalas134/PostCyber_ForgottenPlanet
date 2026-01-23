package systempkg;

import java.util.List;

import viewpkg.CharacterStatusView;

import java.util.ArrayList;

import vopkg.CharacterVO;
import vopkg.CharacterFinalStatsVO;

public class GameStartData {
	public static List<CharacterFinalStatsVO> run(List<CharacterVO> characters) throws Exception {
		System.out.println("==Game Start==");
	
		// 출력 검사
	    
	    List<CharacterFinalStatsVO> finalStatsList = new ArrayList<>();
	    
	    for (CharacterVO vo : characters) {
	    	finalStatsList.add(CharacterStatsService.buildFinalStats(vo)
	    	);
	    }
	    
//	    CharacterStatusView.printCharacters(finalStatsList);
	    return finalStatsList;
	/*
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
		*/
	}
}
