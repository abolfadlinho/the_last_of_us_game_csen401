//package tests;
//
//import static org.junit.Assert.assertEquals;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.awt.Point;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import org.junit.Test;
//
//import engine.Game;
//import exceptions.InvalidTargetException;
//import exceptions.NotEnoughActionsException;
//import model.characters.Zombie;
//import model.characters.Character;
//import model.characters.Hero;
//import model.collectibles.Collectible;
//import model.collectibles.Supply;
//import model.collectibles.Vaccine;
//import model.world.Cell;
//import model.world.CharacterCell;
//import model.world.CollectibleCell;
//import model.world.TrapCell;
//
//@SuppressWarnings({ "all" })
//
//public class M2PublicTestsMine {
//
//	String characterPath = "model.characters.Character";
//	String collectiblePath = "model.collectibles.Collectible";
//	String vaccinePath = "model.collectibles.Vaccine";
//	String supplyPath = "model.collectibles.Supply";
//	String fighterPath = "model.characters.Fighter";
//
//	String charCell = "model.world.CharacterCell";
//	String cellPath = "model.world.Cell";
//	String collectibleCellPath = "model.world.CollectibleCell";
//	String trapCellPath = "model.world.TrapCell";
//	String enumDirectionPath = "model.characters.Direction";
//
//	String gamePath = "engine.Game";
//	String medicPath = "model.characters.Medic";
//	String explorerPath = "model.characters.Explorer";
//	String heroPath = "model.characters.Hero";
//
//	String gameActionExceptionPath = "exceptions.GameActionException";
//	String invalidTargetExceptionPath = "exceptions.InvalidTargetException";
//	String movementExceptionPath = "exceptions.MovementException";
//	String noAvailableResourcesExceptionPath = "exceptions.NoAvailableResourcesException";
//	String notEnoughActionsExceptionPath = "exceptions.NotEnoughActionsException";
//
//	String zombiePath = "model.characters.Zombie";
//
//	String directionPath = "model.characters.Direction";
//
//	@Test(timeout = 3000)
//	public void testValidMoveRight() throws Exception {
//		int random = (int) (Math.random() * 1000);
//		String nameHero = "Fighter " + random;
//		int maxHpHero = 1000;
//		int attackDmgHero = (int) (Math.random() * 100);
//		int maxActionsHero = (int) (Math.random() * 5) + 100;
//		generateMap();
//		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
//				int.class);
//		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
//		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
//		setLocation.invoke(character, new Point(4, 4));
//		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
//		setActions.invoke(character, maxActionsHero);
//
//		try {
//			Cell[][] m = engine.Game.map;
//			CharacterCell c = new CharacterCell(null);
//			for (int i = -1; i <= 1; i++) {
//				int cx = 4 + i;
//				for (int j = -1; j <= 1; j++) {
//					int cy = 5 + j;
//					if (cy >= 0 && cy <= m.length - 1) {
//						m[cx][cy] = c;
//					}
//				}
//			}
//			m[4][4] = new CharacterCell((Character) character);
//
//			Method getLocation = character.getClass().getMethod("getLocation");
//			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
//			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
//			Point location = (Point) getLocation.invoke(character);
//			int updatedX = (int) location.getX();
//			int updatedY = (int) location.getY();
//
//			assertTrue(
//					"The current location of the character is not updated correctly after moving right. Expected X coorditane "
//							+ 4 + " but was " + updatedX,
//					4 == updatedX);
//
//			assertTrue(
//					"The current location of the character is not updated correctly after moving right. Expected Y coorditane "
//							+ 5 + " but was " + updatedY,
//					5 == updatedY);
//
//		} catch (NoSuchMethodException e) {
//			fail("Hero class should have move method");
//		}
//
//	}
//
//	
//	private void generateMap() {
//		Constructor<?> gameConstructor;
//		Field f = null;
//		try {
//			gameConstructor = Class.forName(gamePath).getConstructor();
//			Object createdGame = gameConstructor.newInstance();
//			Class<? extends Object> curr = createdGame.getClass();
//			f = curr.getDeclaredField("map");
//			f.setAccessible(true);
//			f.set(createdGame, new Cell[15][15]);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//
//	}
//
//	@Test(timeout = 5000)
//	public void testZombieAttackDirections() {
//
//		int maxHp = 1;
//		int attackDamage = 1;
//		int maxActions = 1;
//
//		int zombieXLocation = 3;
//		int zombieYLocation = 3;
//
//		ArrayList<Hero> testCharactersList = null;
//		Class<?> characterClass = null;
//
//		try {
//
//			Class<?> fighterClass = Class.forName(fighterPath);
//			characterClass = Class.forName(characterPath);
//			Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class,
//					int.class);
//
//			testCharactersList = new ArrayList<Hero>();
//
//			for (int i = 0; i < 9; i++) {
//				testCharactersList
//						.add((Hero) constructorFighter.newInstance("Bob " + i, maxHp, attackDamage, maxActions));
//			}
//
//		} catch (Exception e) {
//			fail(e.getCause().getClass()
//					+ " ccuered while trying to create Fighters. Check the fighter, hero and character constuctors!");
//		}
//
//		Object[] array = testCharactersList.toArray();
//		Hero[] testCharacters = new Hero[array.length];
//
//		for (int i = 0; i < 9; i++)
//			testCharacters[i] = (Hero) array[i];
//
//		Object character2 = null;
//
//		try {
//
//			Class<?> zombieClass = Class.forName(zombiePath);
//			Constructor<?> constructorZombie = zombieClass.getConstructor();
//			character2 = constructorZombie.newInstance();
//
//		} catch (Exception e) {
//			fail(e.getCause().getClass()
//					+ " ccuered while trying to create Zombies. Check the zombie and character constuctors!");
//		}
//
//		Class<?> gameClass = null;
//		try {
//			gameClass = Class.forName(gamePath);
//		} catch (Exception e) {
//			fail(e.getCause().getClass() + " ccuered while trying to create a game.");
//		}
//
//		Field mapField = null;
//		Cell[][] tmpMap = null;
//
//		Field heroField = null;
//		ArrayList<Hero> heroList = null;
//		Method setLocation = null;
//
//		Field zombieField = null;
//		ArrayList<Zombie> zombieList = null;
//
//		try {
//
//			setLocation = characterClass.getMethod("setLocation", Point.class);
//			heroField = Game.class.getDeclaredField("heroes");
//			heroList = ((ArrayList<Hero>) heroField.get(gameClass));
//
//			mapField = Game.class.getDeclaredField("map");
//
//			tmpMap = (Cell[][]) mapField.get(gameClass);
//
//			zombieField = Game.class.getDeclaredField("zombies");
//			zombieList = ((ArrayList<Zombie>) zombieField.get(gameClass));
//			heroList.clear();
//			zombieList.clear();
//
//			zombieList.add((Zombie) character2);
//
//			Point location2 = new Point(zombieXLocation, zombieYLocation);
//			setLocation.invoke(character2, location2);
//			tmpMap[zombieXLocation][zombieYLocation] = new CharacterCell((Zombie) character2);
//
//		} catch (Exception e) {
//			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
//		}
//
//		try {
//			Method startGame = gameClass.getMethod("startGame", Hero.class);
//			startGame.invoke(gameClass, testCharacters[0]);
//		} catch (Exception e) {
//			fail(e.getCause().getClass() + " occuered while trying to start a game");
//		}
//
//		Iterator<Zombie> iterator = zombieList.iterator();
//
//		while (iterator.hasNext()) {
//
//			Zombie z = iterator.next();
//
//			if (!z.equals((Zombie) character2)) {
//				Point locationZ = z.getLocation();
//
//				((CharacterCell) Game.map[locationZ.x][locationZ.y]).setCharacter(null);
//				iterator.remove();
//			}
//		}
//
//		int charCount = 1;
//		for (int i = -1; i <= 1; i++) {
//			int cx = zombieXLocation + i;
//			for (int j = -1; j <= 1; j++) {
//				int cy = zombieYLocation + j;
//
//				if (!(cx == zombieXLocation && cy == zombieYLocation)) {
//					Point location2 = new Point(cx, cy);
//
//					try {
//						setLocation.invoke(testCharacters[charCount], location2);
//					} catch (Exception e) {
//						fail(e.getCause().getClass() + " occurred while trying to set location of Fighters");
//					}
//
//					tmpMap[cx][cy] = new CharacterCell(testCharacters[charCount]);
//					heroList.add(testCharacters[charCount]);
//					charCount++;
//				}
//			}
//		}
//
//		for (int i = 0; i < 8; i++) {
//
//			try {
//				Method endTurn = gameClass.getMethod("endTurn");
//				endTurn.invoke(gameClass);
//			} catch (Exception e) {
//				fail(e.getCause().getClass() + " ccuered while trying to end turn, check the Zombies attack!");
//			}
//		}
//
//		boolean isAllDead = heroList.size() <= 1;
//
//		assertEquals("All 8 heros around Zombie should be dead after attacking all of them", isAllDead, true);
//	}
//
//}