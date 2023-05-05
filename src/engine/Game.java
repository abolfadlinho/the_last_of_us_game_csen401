package engine;

import helper.Methods;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	
	public static Cell [][] map ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	
		
	public static void loadHeroes(String filePath) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
			
			
		}
		br.close();	
	}
	
	public static void startGame(Hero h){
		CollectibleCell col;
		Point p;
		TrapCell trapCell;
		CharacterCell zombieCell;
		for (int i=0;i<5;i++){
			p = Methods.generateRandomEmptyPoint();
			col = new CollectibleCell(new Vaccine());
			map[p.x][p.y] = col;
			p = Methods.generateRandomEmptyPoint();
			col = new CollectibleCell(new Supply());
			map[p.x][p.y] = col;
			p = Methods.generateRandomEmptyPoint();
			trapCell = new TrapCell();
			map[p.x][p.y] = trapCell;
		}
		
		Zombie zombie;
		for (int i=0;i<10;i++){
			p = Methods.generateRandomEmptyPoint();
			zombie = new Zombie();
			zombieCell = new CharacterCell(zombie);
			zombie.setLocation(p);
			map[p.x][p.y] = zombieCell;
		}
		//Load heroes
		for (int i=0;i<availableHeroes.size();i++){
			//Tested it and it actually shifts elements so that no index is empty
			if(availableHeroes.get(i)==h){
				availableHeroes.remove(i);
			}
		}
		heroes.add(h);
		h.setLocation(new Point(0,0));
		map[0][0] = new CharacterCell(h);
	}
	
	public static boolean checkWin(){
		//Check if there is vaccines left
		for (int i=0;i<15;i++){
			for (int j=0;i<15;i++){
				if(map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
						return false;
				}
			} 
		}
		for (int i=0;i<heroes.size();i++){
			if(heroes.get(i).getVaccineInventory().size()>0)
				return false;
		}
		//No vaccines on map
		//Then check for inventory of all heroes for instanceof vaccines, if there is return false
		if (heroes.size()<5)
			return false;
		return true;
	}
	
	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		Cell cell;
		Zombie zombie;
		Character hero;
		for (int x=0;x<15;x++){
			for(int y=0;y<15;y++){
				cell = map[x][y];
				if (cell instanceof CharacterCell){
					if (((CharacterCell)cell).getCharacter() instanceof Zombie){
						ArrayList<Cell> adj = Methods.getAdjacent(new Point(x,y));
						for(int i=0;i<adj.size();i++){
							if(adj.get(i) instanceof CharacterCell && ((CharacterCell)adj.get(i)).getCharacter() != null && !(((CharacterCell)adj.get(i)).getCharacter() instanceof Zombie)){
								zombie = (Zombie)((CharacterCell)cell).getCharacter();
								hero = ((CharacterCell)adj.get(i)).getCharacter();
								zombie.setTarget(hero);
								zombie.attack();
								break;
							}
						}
					}
				}
			}
		}
		Point p = Methods.generateRandomEmptyPoint();
		zombie = new Zombie();
		map[p.x][p.y] = new CharacterCell(zombie);
		zombie.setLocation(p);
	}
	
	public static boolean checkGameOver(){
		if (heroes.size()<=0)
			return true;
		return false;
	}
	
	
}
