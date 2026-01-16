package vopkg;

public class RaceIndexVO {
	private final String raceId;
	private final String raceName;
	
	public RaceIndexVO(String raceId, String raceName) {
		this.raceId = raceId;
		this.raceName = raceName;
	}
	
	public String getRaceId() { return raceId; }
	public String getRaceName() { return raceName; }
}
