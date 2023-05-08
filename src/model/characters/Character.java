package model.characters;

import helper.Methods;

import java.awt.Point;





import java.util.ArrayList;

import model.world.Cell;
import model.world.CharacterCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;


public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target){
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp <= 0) {
			this.currentHp = 0;
			onCharacterDeath();
		}
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public void attack() throws NotEnoughActionsException,InvalidTargetException{	
		if(target==this)
			throw new InvalidTargetException("Cannot attack selected target");
//		if(target.getLocation().x<location.x-1||target.getLocation().y<location.y-1||
//		target.getLocation().x>location.x+1||target.getLocation().y>location.y+1)
			//Added because of hero class
		Character c = this.getTarget();
		c.setCurrentHp(c.getCurrentHp()-this.getAttackDmg());
		c.defend(this);
		if(c.getCurrentHp()<0)
			target = null;
	}
	
	public void defend(Character c){
		setTarget(c);
		target.setCurrentHp(target.getCurrentHp()-(this.getAttackDmg()/2));
	}
	
	public void onCharacterDeath() {
		Game.map[this.location.x][this.location.y] = new CharacterCell(null);
		
	}
}
