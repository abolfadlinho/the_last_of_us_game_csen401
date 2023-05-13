package model.collectibles;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;



public class Supply implements Collectible  {
	
	public Supply() {
		
	}

	public void pickUp(Hero h){
		h.getSupplyInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException,NotEnoughActionsException, InvalidTargetException {
		if(h == null)
			throw new InvalidTargetException("Cannot apply to null");
		h.useSpecial();
	}
}
