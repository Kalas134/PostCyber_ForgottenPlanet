package vopkg;

public class CharacterVO {
	
	private Integer charId;
	private String name;
	private String raceId;
	private Integer gender;
	
	private Integer level;
	private Integer experience;
	
	private Integer statPoint;
	private Integer str;
	private Integer con;
	private Integer agi;
	private Integer dex;
	private Integer intel;
	private Integer wis;
	private Integer luk;
	
	public CharacterVO (Integer charId, String name, String raceId, Integer gender,
						Integer level, Integer experience,
						Integer statPoint, Integer str, Integer con, Integer agi,
						Integer dex, Integer intel, Integer wis, Integer luk) {
		this.charId = charId;
		this.name = name;
		this.raceId = raceId;
		this.gender = gender;
		this.level = level;
		this.experience = experience;
		this.statPoint = statPoint;
		this.str = str;
		this.con = con;
		this.agi = agi;
		this.dex = dex;
		this.intel = intel;
		this.wis = wis;
		this.luk = luk;
	}
	
	public Integer getCharId() { return charId; }
	public String getName() { return name; }
	public String getRaceId() { return raceId; }
	public Integer getGender() { return gender; }
	public Integer getLevel() { return level; }
	public Integer getExperience() { return experience; }
	public Integer getStatPoint() { return statPoint; }
	public Integer getStr() { return str; }
	public Integer getCon() { return con; }
	public Integer getAgi() { return agi; }
	public Integer getDex() { return dex; }
	public Integer getIntel() { return intel; }
	public Integer getWis() { return wis; }
	public Integer getLuk() { return luk; }
}
