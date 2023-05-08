package model.characters;

import helper.Methods;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;



public class Medic extends Hero {
	//Heal amount  attribute - quiz idea
	

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
	}
	
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		if(super.getTarget() instanceof Zombie)
			throw new InvalidTargetException("Cannot heal zombie");
		if(!Methods.isAdj(super.getLocation(),super.getTarget()))
			throw new InvalidTargetException("Target out of range");
		if(super.getTarget()==null)
			throw new InvalidTargetException("no target is selected");
		super.useSpecial();
		super.getTarget().setCurrentHp(getMaxHp());
	}
}
