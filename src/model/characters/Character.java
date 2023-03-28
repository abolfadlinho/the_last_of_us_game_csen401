package model.characters;
import java.awt.*;

public abstract class Character {
	private String name;
   	private Point location;
   	private int maxHp;
   	private int currentHp;
   	private int attackDmg;
   	private Character target;
   
	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.currentHp = maxHp;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
	}
	
	public String getName() {
		return name;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location){
		this.location = location;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public int getCurrentHp() {
		return currentHp;
	}
	
	public void setCurrentHp(int currentHp){
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}
	
	public int getAttackDmg() {
		return attackDmg;
	}
	
	public Character getTarget() {
		return target;
	}
	
	public void setTarget(Character target){
		this.target = target;
	}
}


