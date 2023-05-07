package model.characters;

import helper.Methods;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;


public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
	}
	
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		super.useSpecial();
		for (int x=0;x<=14;x++){
			for (int y=0;y<=14;y++){
				Game.map[y][x].setVisible(true);
			}
		}
	}
}
