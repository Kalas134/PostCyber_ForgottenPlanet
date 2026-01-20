package systempkg;

import vopkg.CharacterCombatStatsVO;
import vopkg.CharacterSurvivalStatsVO;
import utilpkg.NumberUtil;

public class CharacterDeriverdStatsCalculator {
	
	static final double BASE_CRIT_MULT = 1.5;
	static final double MATH_STAT_RATE = 0.02;
	static final double LUK_RATE = 0.005;
	static final double CRIT_MULT_MAX = 2.5;
	
	static final double CURVE_POWER = 0.65;
	
	public static CharacterCombatStatsVO calculateCombat(
			int str, int con, int agi, int dex, int intel, int wis, int luk
	) {	
		
		int hp = (con * 5) + str;
		int mp = (wis * 4) + (intel * 2);
		
		int atk = (str * 2) + dex;
		int def = (con * 2) + str;
		
		int mag = (intel * 2) + wis;
		int res = (wis * 2) + con;
		
		double base_crt = NumberUtil.round2((dex * 0.5) + (luk * 0.7));
		double crt = NumberUtil.applySoftCurve(base_crt, 75.0, CURVE_POWER);
		double base_crr = NumberUtil.round2((wis * 0.5) + (con * 0.3));
		double crr = NumberUtil.applySoftCurve(base_crr, 75.0, CURVE_POWER);
		
		double pcr = BASE_CRIT_MULT
				   + ((str + dex) * MATH_STAT_RATE)
				   + (luk * LUK_RATE);
		double mcr = BASE_CRIT_MULT
				   + ((intel + wis) * MATH_STAT_RATE)
				   + (luk * LUK_RATE);
		
		int spd = (agi * 2) + dex;
		
		return new CharacterCombatStatsVO(
				hp, mp, 
				atk, def, 
				mag, res,
				crt, crr,
				pcr, mcr, 
				spd
		);
	}
	
	public static CharacterSurvivalStatsVO calculateSurvival(
			int str, int con, int agi, int dex, int intel, int wis, int luk
	) {
		int stamina = (str * 2) + (con * 3) + agi;
		int carryCap = (str * 2) + con + 20;
		
		int hungerRes = (con * 2) + wis;
		int healRate = (wis * 2) + con;
		
		int immnueStr = (con * 2) + (wis * 2) + luk;
		int craftingEff = (dex * 2) + intel + wis;
		
		double envTolerance = ((wis * 2) + (con * 2) + (str * 0.5));
		double scavenging = ((luk * 2) + intel + agi);
		double instinct = ((luk * 1.5) + (agi * 1.5) + wis);
		
		return new CharacterSurvivalStatsVO(
				stamina, 
				carryCap, 
				hungerRes, 
				healRate, 
				immnueStr, 
				craftingEff, 
				envTolerance, 
				scavenging, 
				instinct
		);
	}
}
