//package tests;
//
//import static org.junit.Assert.assertEquals;
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
//public class M2PublicTestsManyTargets {
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
//
//private void resetGameStatics() throws Exception {
//	Field fd = Class.forName(gamePath).getDeclaredField("zombies");
//	fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());
//
//	fd = Class.forName(gamePath).getDeclaredField("heroes");
//	fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());
//
//	fd = Class.forName(gamePath).getDeclaredField("map");
//	fd.setAccessible(true);
//
//	Constructor<?> charCellConst = Class.forName(charCell).getConstructor(Class.forName(characterPath));
//	Constructor<?> zombieConst = Class.forName(zombiePath).getConstructor();
//
//	Field fd2 = Class.forName(charCell).getDeclaredField("character");
//	fd2.setAccessible(true);
//
//	Object[][] map = ((Object[][]) fd.get(null));
//
//	Method setChar = Class.forName(charCell).getMethod("setCharacter", Class.forName(characterPath));
//	Object o = null;
//	fd = Class.forName(cellPath).getDeclaredField("isVisible");
//	fd.setAccessible(true);
//	for (int i = 0; i < map.length; i++) {
//		for (int j = 0; j < map[i].length; j++) {
//			map[i][j] = charCellConst.newInstance(o);
//			fd.set(map[i][j], false);
//
//		}
//	}
//
//	fd2 = Class.forName(zombiePath).getDeclaredField("ZOMBIES_COUNT");
//	fd2.setAccessible(true);
//	fd2.set((int) fd2.get(null), 11);
//	loadChampions();
//
//}
//
//private void loadChampions()
//		throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
//		InvocationTargetException, NoSuchMethodException, SecurityException, IOException, NoSuchFieldException {
//
//	PrintWriter csvWriter = new PrintWriter("test_heros.csv");
//
//	ArrayList<Object> heros = new ArrayList<Object>();
//	Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
//			int.class, int.class);
//	Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
//			int.class, int.class);
//	Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
//			int.class);
//
//	for (int i = 0; i < 7; i++) {
//		int maxHp = ((int) (Math.random() * 100) + 10);
//		int dmg = ((int) (Math.random() * 200) + 10);
//		int actions = ((int) (Math.random() * 5) + 5);
//		String name = "Hero_" + i;
//
//		String type = "";
//		if (i == 0 || i == 4) {
//			type = "FIGH";
//			Object createdHero = fighterConstructor.newInstance(name, maxHp, dmg, actions);
//			heros.add(createdHero);
//
//		} else {
//			if (i % 2 == 0) {
//				type = "MED";
//				Object createdHero = medicsConstructor.newInstance(name, maxHp, dmg, actions);
//				heros.add(createdHero);
//
//			} else {
//				type = "EXP";
//				Object createdHero = explorerConstructor.newInstance(name, maxHp, dmg, actions);
//				heros.add(createdHero);
//
//			}
//		}
//		String line = name + "," + type + "," + maxHp + "," + actions + "," + dmg;
//		csvWriter.println(line);
//	}
//
//	csvWriter.flush();
//	csvWriter.close();
//
//	Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
//	fd.setAccessible(true);
//	fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());
//
//	Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
//	m.invoke(null, "test_heros.csv");
//
//}
//
//
//private Object createMedic() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
//InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//
//Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
//	int.class);
//int maxHp = ((int) (Math.random() * 100) + 10);
//int dmg = ((int) (Math.random() * 2) + 10);
//int actions = ((int) (Math.random() * 5) + 5);
//String name = "Hero_";
//
//return medicsConstructor.newInstance(name, maxHp, dmg, actions);
//
//}
//
//private Object createExplorer() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
//InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
//	int.class, int.class);
//int maxHp = ((int) (Math.random() * 100) + 10);
//int dmg = ((int) (Math.random() * 5) + 5);
//int actions = ((int) (Math.random() * 5) + 5);
//String name = "Hero_";
//return explorerConstructor.newInstance(name, maxHp, dmg, actions);
//
//}
//
//private Object createZombie() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
//InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//Constructor<?> zombieConstructor = Class.forName(zombiePath).getConstructor();
//return zombieConstructor.newInstance();
//}
//
//private Object createVaccine() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
//InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//Constructor<?> vaccineConstructor = Class.forName(vaccinePath).getConstructor();
//return vaccineConstructor.newInstance();
//}
//
//private Object createSupply() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
//InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//Constructor<?> supplyConstructor = Class.forName(supplyPath).getConstructor();
//return supplyConstructor.newInstance();
//}
//
//private void placeRandomObjectOnMap(Constructor<?> cellConstructor, Object obj)
//		throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
//		NoSuchFieldException, SecurityException, ClassNotFoundException, NoSuchMethodException {
//
//	Field fd = Class.forName(gamePath).getDeclaredField("map");
//	fd.setAccessible(true);
//	Object[][] map = ((Object[][]) fd.get(null));
//
//	Method getChar = Class.forName(charCell).getMethod("getCharacter");
//	int x, y;
//	do {
//		x = ((int) (Math.random() * map.length));
//		y = ((int) (Math.random() * map[x].length));
//	} while (map[x][y] != null
//			&& (((map[x][y].getClass().equals(Class.forName(charCell)) && getChar.invoke(map[x][y]) != null))
//					|| (map[x][y].getClass().equals(Class.forName(collectibleCellPath)))
//					|| (map[x][y].getClass().equals(Class.forName(trapCellPath)))));
//
//	map[x][y] = cellConstructor.newInstance(obj);
//	if (obj.getClass().equals(Class.forName(explorerPath)) || obj.getClass().equals(Class.forName(fighterPath))
//			|| obj.getClass().equals(Class.forName(medicPath))
//			|| obj.getClass().equals(Class.forName(zombiePath))) {
//		fd = Class.forName(characterPath).getDeclaredField("location");
//		fd.setAccessible(true);
//		fd.set(obj, new Point(x, y));
//	}
//}
//
//@Test(timeout = 10000)
//	public void testEndTurnResetZombieTargetMany() throws Exception {
//		resetGameStatics();
//		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
//		fd.setAccessible(true);
//		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
//		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
//		ArrayList<Object> zombiesToUse = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//
//			Object h1 = createMedic();
//			placeRandomObjectOnMap(charCellConstructor, h1);
//
//			Object z2 = createZombie();
//			zombies.add(z2);
//			zombiesToUse.add(z2);
//			placeRandomObjectOnMap(charCellConstructor, z2);
//
//			fd = Class.forName(characterPath).getDeclaredField("target");
//			fd.setAccessible(true);
//			fd.set(z2, h1);
//		}
//
//		Method m = Class.forName(gamePath).getMethod("endTurn");
//
//		try {
//			m.invoke(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
//				fail(e.getClass() + " Occured but shouldnt end turn");
//			if (e.getClass().equals(Class.forName(notEnoughActionsExceptionPath)))
//				fail(e.getClass() + " occured but shouldnt in end turn");
//			if (e.getClass().equals(Class.forName(noAvailableResourcesExceptionPath)))
//				fail(e.getClass() + " occured but shouldnt in end turn");
//
//			fail("Null pointer exception occured make sure to handle null targets scenario in end turn");
//		}
//		fd = Class.forName(characterPath).getDeclaredField("target");
//		fd.setAccessible(true);
//		for (Object z : zombiesToUse) {
//
//			assertTrue("After the player ends their turn all zombies' targets should be reset", fd.get(z) == null);
//		}
//	}
//}