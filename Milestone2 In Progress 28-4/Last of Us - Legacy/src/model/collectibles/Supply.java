package model.collectibles;

import java.util.ArrayList;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
public class Supply implements Collectible  {
	
	public Supply() {
		
	}

	public void pickUp(Hero h) throws NoAvailableResourcesException{
		checkAvailableActions(h);
		h.getSupplyInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException{
		checkAvailableActions(h);
		for (int i=0;i<h.getSupplyInventory().size();i++) {
			if(h.getSupplyInventory().get(i)==this) {
				h.getSupplyInventory().remove(i);
				break;
			}
		}
	}
	
	public static void checkAvailableActions(Hero h) throws NoAvailableResourcesException{
		if(h.getActionsAvailable()<=0) {
			throw new NoAvailableResourcesException("No more actions available");
		}
	}
}
