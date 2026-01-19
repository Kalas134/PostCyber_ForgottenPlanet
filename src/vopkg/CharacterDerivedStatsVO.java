package vopkg;

public class CharacterDerivedStatsVO {
	
	private final CharacterCombatStatsVO combat;
	private final CharacterSurvivalStatsVO survival;
	
	public CharacterDerivedStatsVO(
			CharacterCombatStatsVO combat, 
			CharacterSurvivalStatsVO survival
	) {
		this.combat = combat;
		this.survival = survival;
	}

	public CharacterCombatStatsVO getCombat() {
		return combat;
	}

	public CharacterSurvivalStatsVO getSurvival() {
		return survival;
	}
	
}
