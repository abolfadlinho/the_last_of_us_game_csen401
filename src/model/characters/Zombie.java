package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import model.world.*;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import helper.Methods;



public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void attack() throws NotEnoughActionsException,InvalidTargetException {
		if(super.getTarget() instanceof Zombie)
			throw new InvalidTargetException("Friendly fire is off");
		ArrayList<Cell> adj = Methods.getAdjacent(super.getLocation());
		for (int i=0;i<adj.size();i++){
			if(adj.get(i) instanceof CharacterCell && ((CharacterCell)adj.get(i)).getCharacter()!=null)
				super.setTarget(((CharacterCell)adj.get(i)).getCharacter());
		}
		if(super.getTarget()!=null)
			super.attack();
	}
	
	
	public void onCharacterDeath(){
		super.onCharacterDeath();
		for(int i=0;i<Game.zombies.size();i++){
			if(Game.zombies.get(i)==this)
				Game.zombies.remove(i);
		}
		Zombie newZombie = new Zombie();
		Game.zombies.add(newZombie);
		Point rand = Methods.generateRandomEmptyPoint();	
		Game.map[rand.y][rand.x] = new CharacterCell(newZombie);
		ZOMBIES_COUNT--;
		super.setLocation(rand);
	}
}


