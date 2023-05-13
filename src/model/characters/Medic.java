package model.characters;

import helper.Methods;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;



public class Medic extends Hero {	

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
	}
	
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		if(super.getTarget() instanceof Zombie)
			throw new InvalidTargetException("Cannot heal zombie");
		if(this.getLocation()!=null&&!Methods.isAdj(super.getLocation(),super.getTarget()))
			throw new InvalidTargetException("Target out of range");
		if(super.getTarget()==null){
			super.setTarget(this);
		}
		super.useSpecial();
		super.getTarget().setCurrentHp(getMaxHp());
		
	}
}
