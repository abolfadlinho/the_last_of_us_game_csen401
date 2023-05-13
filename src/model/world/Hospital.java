package model.world;
import model.collectibles.*;

public class Hospital extends Building {
	
	public Hospital() {
		super(new Vaccine());
	}
}
