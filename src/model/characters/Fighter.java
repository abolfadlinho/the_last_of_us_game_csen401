package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;


public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}
	
	public void attack() throws NotEnoughActionsException,InvalidTargetException {
		if(super.isSpecialAction()){
			super.setActionsAvailable(super.getActionsAvailable()+1);
			try{
				super.attack();
			}catch(Exception e){
				super.setActionsAvailable(super.getActionsAvailable()-1);
			}
		}else{	
			super.attack();
		}
	}
	
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		if(super.getTarget()==null)
			throw new InvalidTargetException("no target is selected");
		super.useSpecial();
		attack();
	}
}
