package model.collectibles;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;

public interface Collectible {
	public void pickUp(Hero h);
	
	public void use(Hero h) throws NoAvailableResourcesException,NotEnoughActionsException,InvalidTargetException;
}
