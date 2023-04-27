package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
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
	
	
	
		
	public static void loadHeroes(String filePath)  throws IOException {
		
		
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
		Random rn = new Random();
		//225 = 15*15
		int[][] done = new int[225][2];
		//Reserved for hero start?
		done[0][0] = 0;
		done[0][1] = 0;
		// make helper method of generate position
		for (int i=0;i<5;i++){
			int x = rn.nextInt(6);
			int y = rn.nextInt(6);
			int[]temp = {x,y};
			while (contains(done,temp)){
				x = rn.nextInt(6);
				y = rn.nextInt(6);
				temp[0] = x;
				temp[1] = y;
			}
			TrapCell tc = new TrapCell();
			map[x][y] = tc;
			done[i][0] = x;
			done[i][1] = y;
		}
		
		for (int i=5;i<15;i++){
			int x = rn.nextInt(6);
			int y = rn.nextInt(6);
			int[]temp = {x,y};
			while (contains(done,temp)){
				x = rn.nextInt(6);
				y = rn.nextInt(6);
				temp[0] = x;
				temp[1] = y;
			}
			Zombie z = new Zombie();
			z.setLocation(new Point(x,y));
			CharacterCell cc = new CharacterCell(z);
			map[x][y] = cc;
			done[i][0] = x;
			done[i][1] = y;
		}
		
		for (int i=0;i<availableHeroes.size();i++){
			//Tested it and it actually shifts elements so that no index is empty
			if(availableHeroes.get(i)==h){
				availableHeroes.remove(i);
			}
		}
		heroes.add(h);
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
		//No vaccines on map
		//Then check for inventory of all heroes for instanceof vaccines, if there is return false
		if (availableHeroes.size()<5)
			//heroes or available heroes?
			return false;
		return true;
	}
	
	public static boolean checkGameOver(){
		if (availableHeroes.size()<=0)
			return true;
		return false;
	}
	
	public static boolean contains(int[][] arr,int[] xy){
		for (int i=0;i<arr.length;i++){
			if(arr[0][0]==xy[0]&&arr[0][1]==xy[1]){
				return true;
			}
		}
		return false;
	}
}
