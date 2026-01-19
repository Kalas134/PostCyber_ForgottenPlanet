package vopkg;

public class RaceBasicStatsVO {
	
	private String raceId;
	private int str;
	private int con;
	private int agi;
	private int dex;
	private int intel;
	private int wis;
	private int luk;
	
	public RaceBasicStatsVO(
			String raceId, 
			int str, int con, int agi, int dex, 
			int intel, int wis, int luk
	) {
		this.raceId = raceId;
		this.str = str;
		this.con = con;
		this.agi = agi;
		this.dex = dex;
		this.intel = intel;
		this.wis = wis;
		this.luk = luk;
	}
	
	public String getRaceId() { return raceId; }
	public int getStr() { return str; }
	public int getCon() { return con; }
	public int getAgi() { return agi; }
	public int getDex() { return dex; }
	public int getIntel() { return intel; }
	public int getWis() { return wis; }
	public int getLuk() { return luk; }
}
