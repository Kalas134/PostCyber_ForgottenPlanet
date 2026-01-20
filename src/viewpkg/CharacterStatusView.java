package viewpkg;

import java.util.List;
import vopkg.CharacterFinalStatsVO;
import vopkg.CharacterCombatStatsVO;
import vopkg.CharacterSurvivalStatsVO;

import systempkg.CharacterDeriverdStatsCalculator;


public class CharacterStatusView {
	public static void printCharacters(List<CharacterFinalStatsVO> characters) {
		System.out.println("===CHARACTER STATUS LIST===");
		
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
		
		System.out.println("<[ BASE STATS ]>");
		System.out.println("STR: " + vo.getFinalStr());
		System.out.println("CON: " + vo.getFinalCon());
		System.out.println("AGI: " + vo.getFinalAgi());
		System.out.println("DEX: " + vo.getFinalDex());
		System.out.println("INT: " + vo.getFinalInt());
		System.out.println("WIS: " + vo.getFinalWis());
		System.out.println("LUK: " + vo.getFinalLuk());
		
		System.out.println("\n<[ DERIVERD STATS ]>");
		
		// DERIVERD STATS Calculate
		
		CharacterCombatStatsVO combat =
				CharacterDeriverdStatsCalculator.calculateCombat(
						vo.getFinalStr(), 
						vo.getFinalCon(), 
						vo.getFinalAgi(), 
						vo.getFinalDex(), 
						vo.getFinalInt(), 
						vo.getFinalWis(), 
						vo.getFinalLuk()
				);
		CharacterSurvivalStatsVO survival =
				CharacterDeriverdStatsCalculator.calculateSurvival(
						vo.getFinalStr(), 
						vo.getFinalCon(), 
						vo.getFinalAgi(), 
						vo.getFinalDex(), 
						vo.getFinalInt(), 
						vo.getFinalWis(), 
						vo.getFinalLuk()
				);
		
		// DERIVERD STATS Print
		System.out.println("[ COMBAT STATS ]");
		System.out.println("HP: " + combat.getHp());
		System.out.println("MP: " + combat.getMp());
		System.out.println("ATK: " + combat.getAtk());
		System.out.println("DEF: " + combat.getDef());
		System.out.println("MAG: " + combat.getMag());
		System.out.println("RES: " + combat.getRes());
		System.out.println("CRT: " + combat.getCrt() + "%");
		System.out.println("CRR: " + combat.getCrr() + "%");
		System.out.println("P.CRIT MULT: x" + combat.getPcr());
		System.out.println("M.CRIT MULT: x" + combat.getMcr());
		System.out.println("SPD: " + combat.getSpd());
		
		System.out.println("[ SURVIVAL STATS ]");
		System.out.println("Stamina: " + survival.getStamina());
	    System.out.println("Carry Cap: " + survival.getCarryCapacity());
	    System.out.println("Hunger Res: " + survival.getHungerResistance());
	    System.out.println("Heal Rate: " + survival.getHealRate());
	    System.out.println("Immune Str: " + survival.getImmuneStrength());
	    System.out.println("Crafting Eff: " + survival.getCraftingEfficiency());
	    System.out.println("Env Tolerance: " + survival.getEnviromentTolerance());
	    System.out.println("Scavenging: " + survival.getScavenging());
	    System.out.println("Instinct: " + survival.getInstinct());
	}
}
