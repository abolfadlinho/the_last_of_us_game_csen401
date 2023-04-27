package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;


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
			checkAvailableActions();
			super.attack();
		}
		
		public void move(Direction d) throws NotEnoughActionsException,MovementException{
			checkAvailableActions();
			Point loc = super.getLocation();
			int x = (int)loc.getX();
			int y = (int) loc.getY();
			if(d==Direction.LEFT) {
				if(x==0)
					throw new MovementException();
				super.setLocation(new Point(x-1,y));
			}else if(d==Direction.UP) {
				if(y==5)
					throw new MovementException();
				super.setLocation(new Point(x,y+1));
			}else if(d==Direction.RIGHT) {
				if(x==5)
					throw new MovementException();
				super.setLocation(new Point(x+1,y));
			}else {
				if(y==0)
					throw new MovementException();
				super.setLocation(new Point(x,y-1));
			}
		}
		
		public void checkAvailableActions() throws NotEnoughActionsException{
			if(actionsAvailable<=0) {
				throw new NotEnoughActionsException("No more actions available");
			}else {
				actionsAvailable--;
			}
		}
		
		public void useSpecial() throws NoAvailableResourcesException{
			//All uses Supply
			if (supplyInventory.size()<=0){
				throw new NoAvailableResourcesException();
			}else{
				supplyInventory.remove(supplyInventory.size());
			}
		}
		
		public void cure()throws NoAvailableResourcesException,InvalidTargetException{
			if(super.getTarget() instanceof Zombie){
				if (vaccineInventory.size()<=0){
					throw new NoAvailableResourcesException();
				}else{
					vaccineInventory.remove(vaccineInventory.size());
					super.getTarget().onCharacterDeath();
				}
			}else{
				throw new InvalidTargetException();
			}
		}
		
		public void checkEmptyCell() throws MovementException{
			
		}
}
