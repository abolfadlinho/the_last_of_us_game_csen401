package model.world;
import model.collectibles.*;

public abstract class Building extends Cell {
	
	private Collectible loot;
	

	protected Building(Collectible loot) {
		this.loot = loot;
	}
	
	public Collectible getLoot() {
		return loot;
	}
}
