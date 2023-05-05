package model.characters;

import java.awt.Point;

import model.world.*;
import engine.Game;
import helper.Methods;



public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void onCharacterDeath(){
		super.onCharacterDeath();
		Zombie newZombie = new Zombie();
		Point rand = Methods.generateRandomEmptyPoint();	
		Game.map[rand.x][rand.y] = new CharacterCell(newZombie);
		ZOMBIES_COUNT--;
		super.setLocation(rand);
	}
}


