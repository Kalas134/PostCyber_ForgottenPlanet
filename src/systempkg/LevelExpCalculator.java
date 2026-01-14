package systempkg;

public class LevelExpCalculator {
	
	public static int getRequiredExp(int level) {
		int base = 100;
		double exponent = 1.6;
		return (int) (base * Math.pow(level, exponent));
	}
	
	public static int getTotalExpForLevel(int level) {
		int total = 0;
		for (int lv = 1; lv < level; lv++) {
			total += getRequiredExp(lv);
		}
		return total;
	}
	
}
