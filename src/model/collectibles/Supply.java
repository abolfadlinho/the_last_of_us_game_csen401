package model.collectibles;

import helper.Methods;

import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.characters.Zombie;
public class Supply implements Collectible  {
	
	public Supply() {
		
	}

	public void pickUp(Hero h){
		h.getSupplyInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException,NotEnoughActionsException{
		Methods.checkAvailableActions(h);
		if(h.getSupplyInventory().size()<=0)
			throw new NoAvailableResourcesException("Not enough supplies available");;
		h.getSupplyInventory().remove(0);
	}
}
