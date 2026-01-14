package systempkg;

public class LevelUpResult {
	public final int newLevel;
	public final int remainingExp;
	public final int gainedStatPoints;
	public final boolean leveledUp;
	
	public LevelUpResult(int newLevel, int remainingExp, int gainedStatPoints, boolean leveledUp) {
		this.newLevel = newLevel;
		this.remainingExp = remainingExp;
		this.gainedStatPoints = gainedStatPoints;
		this.leveledUp = leveledUp;
	}
}
