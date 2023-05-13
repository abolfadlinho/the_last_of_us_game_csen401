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
		if(actionsAvailable<0)
			this.actionsAvailable = 0;
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
		if(super.getTarget()==null||super.getTarget()==this)
			throw new InvalidTargetException("Cannot attack selected target");
		if(super.getTarget() instanceof Hero)
			throw new InvalidTargetException("Friendly fire is off");
		if(super.getLocation()!=null && !Methods.isAdj(super.getLocation(),super.getTarget())) {
			throw new InvalidTargetException("Out of location");
		}
		super.attack();
		actionsAvailable--;
	}

	public void move(Direction d) throws NotEnoughActionsException,MovementException {
			Methods.checkAvailableActions(this);
			Point loc = super.getLocation();
			Point newLoc;
			int x = (int)loc.x;
			int y = (int) loc.y;
			if(d==Direction.LEFT) {
				newLoc = (new Point(x,y-1));
			}else if(d==Direction.UP) {
				newLoc = (new Point(x+1,y));
			}else if(d==Direction.RIGHT) {;
				newLoc = (new Point(x,y+1));
			}else {
				newLoc = (new Point(x-1,y));
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

			}else if(cell instanceof TrapCell){
				int dmg = ((TrapCell)cell).getTrapDamage();
				this.setCurrentHp(this.getCurrentHp()-dmg);

			}
			Game.map[loc.x][loc.y] = new CharacterCell(null);
			actionsAvailable--;

			if(this.getCurrentHp()>0){
				Game.map[newLoc.x][newLoc.y] = new CharacterCell(this); 
				ArrayList<Cell> adj = Methods.getAdjacent(newLoc);
				for(int i=0;i<adj.size();i++){
					adj.get(i).setVisible(true);
				}
				Game.map[newLoc.x][newLoc.y].setVisible(true);
				super.setLocation(new Point(newLoc.x,newLoc.y));
			}
	}

	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
		if (supplyInventory.size()<=0){
			throw new NoAvailableResourcesException("No available supplies");
		}else{
			supplyInventory.remove(0);
		}
		setSpecialAction(true);
	}

	public void cure()throws NoAvailableResourcesException,InvalidTargetException, NotEnoughActionsException {
		if(super.getTarget()==null){
			throw new InvalidTargetException("No Target Selected");
		}
		if(!(super.getTarget() instanceof Zombie))
			throw new InvalidTargetException("Cannot cure Hero");
		this.getLocation();		
		if(this.getLocation()!=null&&!Methods.isAdj(this.getLocation(),this.getTarget())){
			throw new InvalidTargetException("Target is far away");
		}
		if(this.getVaccineInventory().size()<0)
			throw new NoAvailableResourcesException("Not enough vaccines available");
		if(actionsAvailable<=0){
			throw new NotEnoughActionsException("Not enough actions available");
		}
		actionsAvailable--;
		vaccineInventory.remove(0);
		Random random = new Random();
		int index = random.nextInt(Game.availableHeroes.size());
		Hero newHero = Game.availableHeroes.remove(index);
		Game.heroes.add(newHero);
		newHero.setLocation(this.getTarget().getLocation());
		Game.map[this.getTarget().getLocation().x][this.getTarget().getLocation().y] = new CharacterCell(newHero);
		Game.zombies.remove(this.getTarget());
	}

	public void onCharacterDeath(){
		super.onCharacterDeath();
		Game.heroes.remove(this);
	}


}
