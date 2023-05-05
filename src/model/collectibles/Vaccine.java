package model.collectibles;

import helper.Methods;

import java.awt.Point;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import engine.Game;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	
	public void pickUp(Hero h){
		h.getVaccineInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException,NotEnoughActionsException,InvalidTargetException{
		if(h.getVaccineInventory().size()<=0)
			throw new NoAvailableResourcesException("Not enough vaccines available");
		if(!(h.getTarget() instanceof Zombie))
				throw new InvalidTargetException("Target cannot be cured");
		Methods.checkAvailableActions(h);
		h.getVaccineInventory().remove(0);
	}
}
