package engine;

import java.util.ArrayList;

import model.characters.*;
import model.world.*;

import java.io.*;

public class Game {
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	
	public static void loadHeroes(String filePath) throws Exception {
		//BufferedReader reader = null;
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		while((line = reader.readLine()) != null) {
			String[] row = line.split(",");
			switch(row[1]) {
				case "FIGH":
					availableHeroes.add(new Fighter(row[0], Integer.parseInt(row[2]), Integer.parseInt(row[4]), Integer.parseInt(row[3])));
					break;
				case "MED" :
					availableHeroes.add(new Medic(row[0], Integer.parseInt(row[2]), Integer.parseInt(row[4]), Integer.parseInt(row[3])));
					break;
				case "EXP" :
						availableHeroes.add(new Explorer(row[0], Integer.parseInt(row[2]), Integer.parseInt(row[4]), Integer.parseInt(row[3])));
						break;
					default:
						break;
				}
			}
			reader.close();
		}
}
