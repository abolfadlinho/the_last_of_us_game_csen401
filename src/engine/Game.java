package engine;

import helper.Methods;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {

	public static Cell [][] map;
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
		for(int x=0;x<15;x++){
			for(int y=0;y<15;y++)
				map[x][y] = new CharacterCell(null);
		}
		
		availableHeroes.remove(h);
		heroes.add(h);
		h.setLocation(new Point(0,0));
		map[0][0] = new CharacterCell(h);
		
		CollectibleCell col;
		Point p;
		TrapCell trapCell;
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
		CharacterCell zombieCell;
		Zombie zombie;
		for (int i=0;i<10;i++){
			p = Methods.generateRandomEmptyPoint();
			zombie = new Zombie();
			zombies.add(zombie);
			zombieCell = new CharacterCell(zombie);
			zombie.setLocation(p);
			map[p.x][p.y] = zombieCell;
		}
		
		map[0][0].setVisible(true);
		map[0][1].setVisible(true);
		map[1][0].setVisible(true);
		map[1][1].setVisible(true);
	}

	public static boolean checkWin(){
		for (int i=0;i<15;i++){
			for (int j=0;j<15;j++){
				if(map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
					{
						return false;
					}
				}
			} 
		}
		for (int i=0;i<heroes.size();i++){
			if(heroes.get(i).getVaccineInventory().size()>0)
				return false;
		}
		if (heroes.size()<5)
			return false;
		return true;
	}

	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		Zombie zombie;
		ArrayList<Cell> adj;
		for(int i=0;i<zombies.size();i++){
			zombies.get(i).attack();
		}
		Point p;
		if(zombies.size()<10){
			p = Methods.generateRandomEmptyPoint();
			zombie = new Zombie();
			zombies.add(zombie);
			map[p.x][p.y] = new CharacterCell(zombie);
			zombie.setLocation(p);
		}
		for(int x=0;x<15;x++){
			for(int y=0;y<15;y++){
				map[x][y].setVisible(false);
			}
		}
		for (int i=0;i<heroes.size();i++){
			heroes.get(i).setActionsAvailable(heroes.get(i).getMaxActions());
			heroes.get(i).setTarget(null);
			heroes.get(i).setSpecialAction(false);
			p = heroes.get(i).getLocation();
			adj = Methods.getAdjacent(p);
			for (int j=0;j<adj.size();j++){
				adj.get(j).setVisible(true);
			}
			map[heroes.get(i).getLocation().x][heroes.get(i).getLocation().y].setVisible(true);
		}
	}

	public static boolean checkGameOver(){
		boolean vaccineOnMap = false;
		for (int i=0;i<15;i++){
			for (int j=0;j<15;j++){
				if(map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
					{
						vaccineOnMap =  true;
						break;
					}
				}
			} 
		}
		boolean vaccineInInv = false;
		for (int i=0;i<heroes.size();i++){
			if(heroes.get(i).getVaccineInventory().size()>0){
				vaccineInInv = true;
				break;
			}
		}
		
		if(!vaccineInInv && !vaccineOnMap && heroes.size()<5)
			return true;
		
		if (availableHeroes.size()<=0)
			return true;
		if (heroes.size()<=0)
			return true;
		return false;
	}
}
