package systempkg;

public class LevelExpCalculator {
	
	private static final int BASE_EXP = 100;
	private static final double EXP_EXPONENT = 1.6;
	private static final int STAT_POINT_PER_LEVEL = 5;
	
	public static int getRequiredExp(int level) {
		return (int) (BASE_EXP * Math.pow(level, EXP_EXPONENT));
	}
	
	public static LevelUpResult calculateLevelUp(int currentLevel, int currentExp) {
		int level = currentLevel;
		int exp = currentExp;
		int gainedStatPoints = 0;
		boolean leveledUp = false;
		
		while (exp >= getRequiredExp(level)) {
			exp -= getRequiredExp(level);
			level++;
			gainedStatPoints += STAT_POINT_PER_LEVEL;
			leveledUp=true;
		}
		return new LevelUpResult(level, exp, gainedStatPoints, leveledUp);
	}
	
	public static int getTotalExpForLevel(int level) {
		int total = 0;
		for (int lv = 1; lv < level; lv++) {
			total += getRequiredExp(lv);
		}
		return total;
	}
}
