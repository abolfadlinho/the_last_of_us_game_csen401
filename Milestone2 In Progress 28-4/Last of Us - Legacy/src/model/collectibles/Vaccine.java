package model.collectibles;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	
	public void pickUp(Hero h) throws NoAvailableResourcesException{
		checkAvailableActions(h);
		h.getVaccineInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException{
		checkAvailableActions(h);
		for (int i=0;i<h.getVaccineInventory().size();i++) {
			if(h.getVaccineInventory().get(i)==this) {
				h.getVaccineInventory().remove(i);
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
