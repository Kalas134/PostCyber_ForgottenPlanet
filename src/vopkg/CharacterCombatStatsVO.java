package vopkg;

public class CharacterCombatStatsVO {
	
	private final int hp;
	private final int mp;
	
	private final int atk;
	private final int def;
	
	private final int mag;
	private final int res;
	
	private final double crt;
	private final double crr;
	
	private final double pcr;
	private final double mcr;
	
	private final int spd;
	
	public CharacterCombatStatsVO(
			int hp, int mp, 
			int atk, int def, 
			int mag, int res, 
			double crt, double crr, 
			double pcr, double mcr, 
			int spd
	) {
		this.hp = hp;
		this.mp = mp;
		this.atk = atk;
		this.def = def;
		this.mag = mag;
		this.res = res;
		this.crt = crt;
		this.crr = crr;
		this.pcr = pcr;
		this.mcr = mcr;
		this.spd = spd;
	}

	public int getHp() { return hp; }
	public int getMp() { return mp; }

	public int getAtk() { return atk; }
	public int getDef() { return def; }

	public int getMag() { return mag; }
	public int getRes() { return res; }

	public double getCrt() { return crt; }
	public double getCrr() { return crr; }
	
	public double getPcr() { return pcr; }
	public double getMcr() { return mcr; }

	public int getSpd() { return spd; }
	
}
