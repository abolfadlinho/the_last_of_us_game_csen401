package helper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import model.characters.Character;
import model.characters.Hero;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.Game;
import exceptions.NotEnoughActionsException;

public class Methods {
	public static void checkAvailableActions(Hero h) throws NotEnoughActionsException{
		if(h.getActionsAvailable()<=0) {
			throw new NotEnoughActionsException("No more actions available");
		}
		h.setActionsAvailable(h.getActionsAvailable()-1);
	}
	public static boolean inMap(Point p){
		if (p.x>14||p.x<0||p.y>14||p.y<0)
			return false;
		return true;
	}
	public static ArrayList<Cell> getAdjacent(Point p){
		ArrayList<Cell> adj = new ArrayList<Cell>();
		int x = p.x;
		int y = p.y;
		for (int i=x-1;i<=x+1;i++){
			for(int j=y-1;j<=y+1;y++){
				if(i==x&&j==y)
					continue;
				if(inMap(new Point(x,y)))
					adj.add(Game.map[i][j]);
			}
		}
		return adj;
	}
	public static boolean isAdj(Point p,Character target){
		ArrayList<Cell> adj = getAdjacent(p);
		for(int i=0;i<adj.size();i++){
			if(((CharacterCell)adj.get(i)).getCharacter()==target)
				return true;
		}
		return false;
	}
	public static Point generateRandomPoint(){
		Random random = new Random();
		int x = random.nextInt(15);
		int y = random.nextInt(15);
		return new Point(x,y);
	}
	
	public static Point generateRandomEmptyPoint(){
		Point rand = Methods.generateRandomPoint();
		Cell cell = Game.map[rand.x][rand.y];
		while (cell instanceof CollectibleCell||
				cell instanceof TrapCell||
				(cell instanceof CharacterCell && ((CharacterCell)cell).getCharacter()!=null))
				rand = Methods.generateRandomPoint();
			
		return rand;
	}
}
