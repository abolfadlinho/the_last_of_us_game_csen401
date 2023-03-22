package model.world;

public class TrapCell extends Cell {
	private int trapDamage;
	
	public TrapCell() {
		this.trapDamage = ((int) (Math.random() * 3 + 1)) * 10;
	}

	public int getTrapDamage() {
		return trapDamage;
	}
}
