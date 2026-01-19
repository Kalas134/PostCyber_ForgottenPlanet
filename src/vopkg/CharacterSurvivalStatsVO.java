package vopkg;

public class CharacterSurvivalStatsVO {
	
	private final int stamina;
	private final int carryCapacity;
	
	private final int hungerResistance;
	private final int healRate;
	
	private final int immuneStrength;
	private final int craftingEfficiency;
	
	private final double enviromentTolerance;
	private final double scavenging;
	private final double instinct;
	
	public CharacterSurvivalStatsVO(
			int stamina, int carryCapacity, 
			int hungerResistance, int healRate, 
			int immuneStrength, int craftingEfficiency,
			double enviromentTolerance, double scavenging, double instinct
	) {
		this.stamina = stamina;
		this.carryCapacity = carryCapacity;
		
		this.hungerResistance = hungerResistance;
		this.healRate = healRate;
		
		this.immuneStrength = immuneStrength;
		this.craftingEfficiency = craftingEfficiency;
		
		this.enviromentTolerance = enviromentTolerance;
		this.scavenging = scavenging;
		this.instinct = instinct;
	}

	public int getStamina() {
		return stamina;
	}

	public int getCarryCapacity() {
		return carryCapacity;
	}

	public int getHungerResistance() {
		return hungerResistance;
	}

	public int getHealRate() {
		return healRate;
	}

	public int getImmuneStrength() {
		return immuneStrength;
	}

	public int getCraftingEfficiency() {
		return craftingEfficiency;
	}

	public double getEnviromentTolerance() {
		return enviromentTolerance;
	}

	public double getScavenging() {
		return scavenging;
	}

	public double getInstinct() {
		return instinct;
	}
	
	
}
