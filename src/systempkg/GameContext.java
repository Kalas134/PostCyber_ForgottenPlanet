package systempkg;

import vopkg.CharacterFinalStatsVO;
import vopkg.CharacterDerivedStatsVO;

public class GameContext {
	
	// Game Progress
	private boolean inGame = false;
	
	// Now Character
	private CharacterFinalStatsVO CharacterBaseStats;
	private CharacterDerivedStatsVO CharacterDerivedStats;
	
	// Save Slot
	private int currentSaveSlot = -1;
	
	// getter / setter
	public boolean isinGame() { return inGame; }
	public void setInGame(boolean inGame) { this.inGame = inGame; }
	
	public CharacterFinalStatsVO getCharacterBaseStats() { return CharacterBaseStats; }
	public void setCharacterBaseStats(CharacterFinalStatsVO CharacterBaseStats) { 
		this.CharacterBaseStats = CharacterBaseStats;
		}
	
	public CharacterDerivedStatsVO getCharacterDerivedStats() { return CharacterDerivedStats; }
	public void setCharacterDerivedStats(CharacterDerivedStatsVO CharacterDerivedStats) {
		this.CharacterDerivedStats = CharacterDerivedStats;
	}
	
	public int getCurrentSaveSlot() {
		return currentSaveSlot;
	}
	public void setCurrentSaveSlot(int currentSaveSlot) {
		this.currentSaveSlot = currentSaveSlot;
	}
}
