package vopkg;

public class CharacterFinalStatsVO {
	
	// Basic Character Info
	private final String name;
	private final String raceName;
	private final int level;
	private final int exp;
	private final int statPoint;
	
	// final Stats Sum
	private final int finalStr;
	private final int finalCon;
	private final int finalAgi;
	private final int finalDex;
	private final int finalInt;
	private final int finalWis;
	private final int finalLuk;
	
	public CharacterFinalStatsVO(
			String name,
			String raceName,
			int level,
			int exp,
			int statPoint,
			int finalStr,
			int finalCon,
			int finalAgi,
			int finalDex,
			int finalInt,
			int finalWis,
			int finalLuk
	) {
		this.name = name;
		this.raceName = raceName;
		this.level = level;
		this.exp = exp;
		this.statPoint = statPoint;
		this.finalStr = finalStr;
		this.finalCon = finalCon;
		this.finalAgi = finalAgi;
		this.finalDex = finalDex;
		this.finalInt = finalInt;
		this.finalWis = finalWis;
		this.finalLuk = finalLuk;
	}

	public String getName() { return name; }
	public String getRaceName() { return raceName; }
	public int getLevel() { return level; }
	public int getExp() { return exp; }
	public int getStatPoint() { return statPoint; }
	
	public int getFinalStr() { return finalStr; }
	public int getFinalCon() { return finalCon; }
	public int getFinalAgi() { return finalAgi; }
	public int getFinalDex() { return finalDex; }
	public int getFinalInt() { return finalInt; }
	public int getFinalWis() { return finalWis; }
	public int getFinalLuk() { return finalLuk; }
}
