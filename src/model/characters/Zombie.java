package model.characters;
public class Zombie extends Character{
   private static int ZOMBIES_COUNT;
public Zombie(String name, int maxHp, int attackDmg){
	super(name, maxHp, attackDmg);
}
public static int getZOMBIES_COUNT() {
	return ZOMBIES_COUNT;
}

public static void setZOMBIES_COUNT(int zOMBIES_COUNT) {
	ZOMBIES_COUNT = zOMBIES_COUNT;
}

}

