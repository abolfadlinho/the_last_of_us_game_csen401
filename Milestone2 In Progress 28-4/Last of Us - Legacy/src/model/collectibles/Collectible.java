package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public interface Collectible {
	public void pickUp(Hero h) throws NoAvailableResourcesException;
	
	public void use(Hero h) throws NoAvailableResourcesException;
}
