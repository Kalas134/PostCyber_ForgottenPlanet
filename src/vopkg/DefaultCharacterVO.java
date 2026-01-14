package vopkg;

public class DefaultCharacterVO {
	
	private Integer Char_ID;
	private String Char_PresetName;
	private String Char_RaceID;
	private Integer Char_Gender;
	private Integer Char_DefaultLevel;
	private Integer Char_DefAddedSTR;
	private Integer Char_DefAddedCON;
	private Integer Char_DefAddedAGI;
	private Integer Char_DefAddedDEX;
	private Integer Char_DefAddedINT;
	private Integer Char_DefAddedWIS;
	private Integer Char_DefAddedLUK;
	
	public DefaultCharacterVO(Integer Char_ID, String Char_PresetName, String Char_RaceID, Integer Char_Gender,
							  Integer Char_DefaultLevel, Integer Char_DefAddedSTR,
							  Integer Char_DefAddedCON, Integer Char_DefAddedAGI,
							  Integer Char_DefAddedDEX, Integer Char_DefAddedINT,
							  Integer Char_DefAddedWIS, Integer Char_DefAddedLUK) {
		this.Char_ID = Char_ID;
		this.Char_PresetName = Char_PresetName;
		this.Char_RaceID = Char_RaceID;
		this.Char_Gender = Char_Gender;
		this.Char_DefaultLevel = Char_DefaultLevel;
		this.Char_DefAddedSTR = Char_DefAddedSTR;
		this.Char_DefAddedCON = Char_DefAddedCON;
		this.Char_DefAddedAGI = Char_DefAddedAGI;
		this.Char_DefAddedDEX = Char_DefAddedDEX;
		this.Char_DefAddedINT = Char_DefAddedINT;
		this.Char_DefAddedWIS = Char_DefAddedWIS;
		this.Char_DefAddedLUK = Char_DefAddedLUK;
	}
	
	public Integer getChar_ID() { return Char_ID; }
	public String getChar_PresetName() { return Char_PresetName; }
	public String getChar_RaceID() { return Char_RaceID; }
	public Integer getChar_Gender() { return Char_Gender; }
	public Integer getChar_DefaultLevel() { return Char_DefaultLevel; }
	public Integer getChar_DefAddedSTR() { return Char_DefAddedSTR; }
	public Integer getChar_DefAddedCON() { return Char_DefAddedCON; }
	public Integer getChar_DefAddedAGI() { return Char_DefAddedAGI; }
	public Integer getChar_DefAddedDEX() { return Char_DefAddedDEX; }
	public Integer getChar_DefAddedINT() { return Char_DefAddedINT; }
	public Integer getChar_DefAddedWIS() { return Char_DefAddedWIS; }
	public Integer getChar_DefAddedLUK() { return Char_DefAddedLUK; }
}
