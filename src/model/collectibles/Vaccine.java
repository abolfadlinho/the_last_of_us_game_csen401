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
@SuppressWarnings("unused")

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	
	public void pickUp(Hero h){
		h.getVaccineInventory().add(this);
	}
	
	public void use(Hero h) throws NoAvailableResourcesException,NotEnoughActionsException,InvalidTargetException{
		if(h == null)
			throw new InvalidTargetException("Cannot apply to null");
		h.cure();
	}
}
