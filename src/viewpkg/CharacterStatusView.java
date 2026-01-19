package viewpkg;

import java.util.List;
import vopkg.CharacterFinalStatsVO;

public class CharacterStatusView {
	public static void printCharacters(List<CharacterFinalStatsVO> characters) {
		System.out.println("===CHARACTER BASE STATUS LIST===");
		
		for (CharacterFinalStatsVO vo : characters) {
			printSingleCharacter(vo);
			System.out.println("---------------------");
		}
	}
	private static void printSingleCharacter(CharacterFinalStatsVO vo) {
		System.out.println("Name: " + vo.getName());
		System.out.println("Race: " + vo.getRaceName());
		System.out.println("Level: " + vo.getLevel());
		System.out.println("EXP: " + vo.getExp());
		System.out.println("SP: " + vo.getStatPoint() +"\n");
		
		System.out.println("[ STATS ]");
		System.out.println("STR: " + vo.getFinalStr());
		System.out.println("CON: " + vo.getFinalCon());
		System.out.println("AGI: " + vo.getFinalAgi());
		System.out.println("DEX: " + vo.getFinalDex());
		System.out.println("INT: " + vo.getFinalInt());
		System.out.println("WIS: " + vo.getFinalWis());
		System.out.println("LUK: " + vo.getFinalLuk());
	}
}
