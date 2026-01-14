package vopkg;

public class RaceTextVO {
	private String RACE_ID;
	private String RaceName;
	private String RaceDesc;
	
	public RaceTextVO(String RACE_ID, String RaceName, String RaceDesc) {
		this.RACE_ID = RACE_ID;
		this.RaceName = RaceName;
		this.RaceDesc = RaceDesc;
	}
	
	public String getRACE_ID() { return RACE_ID; }
	public String getRaceName() { return RaceName; }
	public String getRaceDesc() { return RaceDesc; }
}
