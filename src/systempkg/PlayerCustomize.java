package systempkg;

import vopkg.DefaultCharacterVO;

public class PlayerCustomize {
	
	private static final Integer PLAYER_ID = 1;
	
	public static DefaultCharacterVO apply(
			DefaultCharacterVO base, 
			String newName, 
			String newRaceID, 
			Integer newGender
	) {
		if (base == null) {
			throw new IllegalArgumentException("Base character VO is null");
		}
		
		if (!PLAYER_ID.equals(base.getChar_ID())) {
			return base;
		}
		
		return new DefaultCharacterVO(
				base.getChar_ID(), 
				newName,
				newRaceID, 
				newGender,
				base.getChar_DefaultLevel(), 
				base.getChar_DefAddedSTR(), 
				base.getChar_DefAddedCON(), 
				base.getChar_DefAddedAGI(), 
				base.getChar_DefAddedDEX(), 
				base.getChar_DefAddedINT(), 
				base.getChar_DefAddedWIS(), 
				base.getChar_DefAddedLUK()
		);
	}
}
