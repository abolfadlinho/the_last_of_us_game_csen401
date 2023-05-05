package model.world;

import java.util.Random;

public class TrapCell extends Cell {

	private int trapDamage;
	
	public TrapCell() {
		int x [] = {10,20,30};
 		Random r = new Random();
		int result = r.nextInt(3);
		trapDamage = x[result];
		
		
	}

	public int getTrapDamage() {
		return trapDamage;
	}
	


}
