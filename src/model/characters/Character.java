package model.characters;
import java.awt.*;
abstract public class Character {
   private String name;
   private Point location;
   private int maxHp;
   private int currentHp;
   private int attackDmg;
   private Character target;
public Character(String name, int maxHp, int attackDmg) {
	super();
	this.name = name;
	this.maxHp = maxHp;
	this.attackDmg = attackDmg;
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
public int getAttackDmg() {
	return attackDmg;
}
public Character getTarget() {
	return target;
}
public void setTarget(Character target) {
	this.target = target;
}
   

}


