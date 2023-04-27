package model.characters;



public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void onCharacterDeath(){
		super.onCharacterDeath();
		ZOMBIES_COUNT--;
	}
}


