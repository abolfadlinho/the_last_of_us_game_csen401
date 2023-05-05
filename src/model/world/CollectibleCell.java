package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell {

	private Collectible collectible;
	
	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}

}
