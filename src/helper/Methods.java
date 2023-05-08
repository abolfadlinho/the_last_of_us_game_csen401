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
		for (int x1=x-1;x1<=x+1;x1++){
			for(int y1=y-1;y1<=y+1;y1++){
				if(x1==x&&y1==y){
					//continue;
				}else if(inMap(new Point(x1,y1))){
					adj.add(Game.map[x1][y1]);
				}
			}
		}
		return adj;
	}
	public static boolean isAdj(Point p,Character target){
//		ArrayList<Cell> adj = getAdjacent(p);
//		for(int i=0;i<adj.size();i++){
//			if(adj.get(i)instanceof CharacterCell)
//				if(((CharacterCell)adj.get(i)).getCharacter()==target)
//					return true;
//		}
//		return false;
		if(target.getLocation().x<p.x-1||target.getLocation().y<p.y-1||
				target.getLocation().x>p.x+1||target.getLocation().y>p.y+1)
			return false;
		return true;
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
				(cell instanceof CharacterCell && ((CharacterCell)cell).getCharacter()!=null)){
			rand = Methods.generateRandomPoint();
			cell = Game.map[rand.x][rand.y];
		}
		return rand;
	}
}
