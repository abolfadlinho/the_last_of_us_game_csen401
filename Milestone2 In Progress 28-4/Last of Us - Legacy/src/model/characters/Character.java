package model.characters;

import java.awt.Point;

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

	public void setTarget(Character target) throws InvalidTargetException{
		int xT = (int) target.getLocation().getX();
		int yT = (int) target.getLocation().getY();
		int xC = (int) location.getX();
		int yC = (int) location.getY();
		if(xT<xC-1||xT>xC+1||yT<xC-1||yT>xC+1) {
			throw new InvalidTargetException("Target is not with range");
		}
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
		if(currentHp < 0) {
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
			throw new InvalidTargetException();
		//Added because of hero class
		Character c = this.getTarget();
		c.setCurrentHp(c.getCurrentHp()-this.getAttackDmg());
		c.defend(this);
	}
	
	public void defend(Character c) {
		c.setCurrentHp(c.getCurrentHp()-(this.getAttackDmg()/2));
	}
	
	public void onCharacterDeath() {
		this.location = new Point(-1,-1);
	}
}
