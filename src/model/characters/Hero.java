package model.characters;

import helper.Methods;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}

		public boolean isSpecialAction() {
			return specialAction;
		}

		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}

		public int getActionsAvailable() {
			return actionsAvailable;
		}

		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}

		public int getMaxActions() {
			return maxActions;
		}

		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}

		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}
		
		public void attack() throws NotEnoughActionsException,InvalidTargetException {
			if(super.getTarget() instanceof Hero)
				throw new InvalidTargetException();
			Methods.checkAvailableActions(this);
			try{
				super.attack();
			}catch(Exception e){
				System.out.print(e.getMessage());
				actionsAvailable++;
			}
		}
		
		public void move(Direction d) throws NotEnoughActionsException,MovementException{
			Methods.checkAvailableActions(this);
			Point loc = super.getLocation();
			Point newLoc = loc;
			int x = (int)loc.getX();
			int y = (int) loc.getY();
			if(d==Direction.LEFT) {
				newLoc = (new Point(x-1,y));
			}else if(d==Direction.UP) {
				newLoc = (new Point(x,y+1));
			}else if(d==Direction.RIGHT) {
				newLoc = (new Point(x+1,y));
			}else {
				newLoc = (new Point(x,y-1));
			}
			if(!Methods.inMap(newLoc))
				throw new MovementException("Movement is outside the boundary");
			Cell cell = Game.map[newLoc.x][newLoc.y];
			if(cell instanceof CharacterCell &&
					((CharacterCell)cell).getCharacter()!=null){
				throw new MovementException("There is a character there");
			}
			if(cell instanceof CollectibleCell){
				Collectible col =  ((CollectibleCell)(cell)).getCollectible();
				col.pickUp(this);
				Game.map[newLoc.x][newLoc.y] = new CharacterCell(this);
			}
			if(cell instanceof TrapCell){
				int dmg = ((TrapCell)cell).getTrapDamage();
				super.setCurrentHp(super.getCurrentHp()-dmg);
			}
			ArrayList<Cell> adj = Methods.getAdjacent(newLoc);
			for(int i=0;i<adj.size();i++){
				adj.get(i).setVisible(true);
			}
			super.setLocation(newLoc);
		}
		
		public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
			//All uses Supply
			setSpecialAction(true);
			if (supplyInventory.size()<=0){
				throw new NoAvailableResourcesException("No available supplies");
			}else{
				supplyInventory.get(0).use(this);
			}
		}
		
		public void cure()throws NoAvailableResourcesException,InvalidTargetException, NotEnoughActionsException{
			vaccineInventory.get(0).use(this);
			Zombie zombie = (Zombie) this.getTarget();
			Random random = new Random();
			int index = random.nextInt(Game.availableHeroes.size());
			Hero newHero = Game.availableHeroes.remove(index);
			newHero.setLocation(zombie.getLocation());
			//Revise what happens when zombie is cured
			Game.map[zombie.getLocation().x][zombie.getLocation().y] = new CharacterCell(newHero);
			zombie.setLocation(new Point(-1,-1));
			Zombie.ZOMBIES_COUNT--;
		}
		
		public void onCharacterDeath(){
			super.onCharacterDeath();
			ArrayList<Vaccine> temp = new ArrayList<Vaccine>();
			// DO WE PUT SUPPLIES
			for (int i=0;i<Game.heroes.size();i++){
				if (Game.heroes.get(i)==this){
					for (int j=0;j<this.vaccineInventory.size();j++){
						temp.add(this.vaccineInventory.get(i));
					}
					Game.heroes.remove(i);
				}
			}
		}
}
