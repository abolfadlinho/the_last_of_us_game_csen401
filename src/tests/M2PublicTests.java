package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Zombie;
import model.characters.Character;
import model.characters.Hero;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

@SuppressWarnings({ "all" })

public class M2PublicTests {

	String characterPath = "model.characters.Character";
	String collectiblePath = "model.collectibles.Collectible";
	String vaccinePath = "model.collectibles.Vaccine";
	String supplyPath = "model.collectibles.Supply";
	String fighterPath = "model.characters.Fighter";

	String charCell = "model.world.CharacterCell";
	String cellPath = "model.world.Cell";
	String collectibleCellPath = "model.world.CollectibleCell";
	String trapCellPath = "model.world.TrapCell";
	String enumDirectionPath = "model.characters.Direction";

	String gamePath = "engine.Game";
	String medicPath = "model.characters.Medic";
	String explorerPath = "model.characters.Explorer";
	String heroPath = "model.characters.Hero";

	String gameActionExceptionPath = "exceptions.GameActionException";
	String invalidTargetExceptionPath = "exceptions.InvalidTargetException";
	String movementExceptionPath = "exceptions.MovementException";
	String noAvailableResourcesExceptionPath = "exceptions.NoAvailableResourcesException";
	String notEnoughActionsExceptionPath = "exceptions.NotEnoughActionsException";

	String zombiePath = "model.characters.Zombie";

	String directionPath = "model.characters.Direction";

	@Test(timeout = 3000)
	public void testMoveExistsHero() {
		Method m = null;
		try {
			m = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
		} catch (NoSuchMethodException e) {
			assertTrue("Class \"Hero\" should contain a method called \"move\" that takes a direction as a parameter.",
					false);
		} catch (SecurityException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.characters should contain a class called Hero.", false);
		}
		assertTrue("Method \"move\" in class \"Hero\" should be void.", m.getReturnType().equals(void.class));

	}

	@Test(timeout = 3000)
	public void testAttackExistsHero() {
		Method m = null;
		try {
			m = Class.forName(heroPath).getDeclaredMethod("attack");
		} catch (NoSuchMethodException e) {
			assertTrue("Class \"Hero\" should contain a method called \"attack\" that takes no parameter.", false);
		} catch (SecurityException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.characters should contain a class called Hero.", false);
		}
		assertTrue("Method \"attack\" in class \"Hero\" should be void.", m.getReturnType().equals(void.class));

	}

	@Test(timeout = 3000)
	public void testUseSpecialExistsHero() {
		Method m = null;
		try {
			m = Class.forName(heroPath).getDeclaredMethod("useSpecial");
		} catch (NoSuchMethodException e) {
			assertTrue("Class \"Hero\" should contain a method called \"useSpecial\" that takes no parameter.", false);
		} catch (SecurityException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.characters should contain a class called Hero.", false);
		}
		assertTrue("Method \"useSpecial\" in class \"Hero\" should be void.", m.getReturnType().equals(void.class));

	}

	@Test(timeout = 3000)
	public void testInvalidBorderMoveDown() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(0, 2));

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "DOWN"));
			fail("Trying to move Down beyond a border, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move beyond a border, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidBorderMoveLeft() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(3, 0));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "LEFT"));
			fail("Trying to move UP beyond a border, an exception should be thrown");
			;

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move beyond a border, an exception should be thrown");
				;

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidBorderMoveRight() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 14));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			fail("Trying to move Right beyond a border, an exception should be thrown");
			;

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move beyond a border, an exception should be thrown");
				;

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidMoveRightCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[4][5] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			fail("Trying to move Right to an occupied cell, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move Right to an occupied cell, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidMoveUPCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[5][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "UP"));
			fail("Trying to move Up to an occupied cell, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move Up to an occupied cell, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidMoveDownCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "DOWN"));
			fail("Trying to move Down to an occupied cell, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(movementExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move Down to an occupied cell, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a MovementException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testMoveUpTrapCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Game.heroes = new ArrayList<>();
		Game.heroes.add((Hero) character);
		Cell[][] m = engine.Game.map;
		CharacterCell c = new CharacterCell(null);
		for (int i = -1; i <= 1; i++) {
			int cx = 5 + i;
			for (int j = -1; j <= 1; j++) {
				int cy = 4 + j;
				if (cy >= 0 && cy <= m.length - 1) {
					m[cx][cy] = c;
				}
			}
		}
		m[4][4] = new CharacterCell((Character) character);

		try {

			TrapCell t = new TrapCell();
			m[5][4] = t;
			int trap = t.getTrapDamage();
			Method getHP = character.getClass().getMethod("getCurrentHp");
			int currentHP1 = (int) getHP.invoke(character);
			int expected = currentHP1 - trap;

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "UP"));

			int currentHP2 = (int) getHP.invoke(character);

			assertTrue("The current HP of the character is not updated correctly after moving to a trap cell. Expected"
					+ expected + " but was " + currentHP2 + ".", expected == currentHP2);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testMoveLeftTrapCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Game.heroes = new ArrayList<>();
		Game.heroes.add((Hero) character);
		Cell[][] m = engine.Game.map;
		CharacterCell c = new CharacterCell(null);
		for (int i = -1; i <= 1; i++) {
			int cx = 4 + i;
			for (int j = -1; j <= 1; j++) {
				int cy = 3 + j;
				if (cy >= 0 && cy <= m.length - 1) {
					m[cx][cy] = c;
				}
			}
		}
		m[4][4] = new CharacterCell((Character) character);

		try {

			TrapCell t = new TrapCell();
			m[4][3] = t;
			int trap = t.getTrapDamage();
			Method getHP = character.getClass().getMethod("getCurrentHp");
			int currentHP1 = (int) getHP.invoke(character);
			int expected = currentHP1 - trap;

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "LEFT"));

			int currentHP2 = (int) getHP.invoke(character);

			assertTrue("The current HP of the character is not updated correctly after moving to a trap cell. Expected"
					+ expected + " but was " + currentHP2 + ".", expected == currentHP2);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testMoveRightTrapCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Game.heroes = new ArrayList<>();
		Game.heroes.add((Hero) character);
		Cell[][] m = engine.Game.map;
		CharacterCell c = new CharacterCell(null);
		for (int i = -1; i <= 1; i++) {
			int cx = 4 + i;
			for (int j = -1; j <= 1; j++) {
				int cy = 5 + j;
				if (cy >= 0 && cy <= m.length - 1) {
					m[cx][cy] = c;
				}
			}
		}
		m[4][4] = new CharacterCell((Character) character);

		try {

			TrapCell t = new TrapCell();
			m[4][5] = t;
			int trap = t.getTrapDamage();
			Method getHP = character.getClass().getMethod("getCurrentHp");
			int currentHP1 = (int) getHP.invoke(character);
			int expected = currentHP1 - trap;

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));

			int currentHP2 = (int) getHP.invoke(character);

			assertTrue("The current HP of the character is not updated correctly after moving to a trap cell. Expected"
					+ expected + " but was " + currentHP2 + ".", expected == currentHP2);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUP() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 5 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 4 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "UP"));
			Point location = (Point) getLocation.invoke(character);
			int updatedX = (int) location.getX();
			int updatedY = (int) location.getY();

			assertTrue(
					"The current location of the character is not updated correctly after moving up. Expected X coorditane "
							+ 5 + " but was " + updatedX,
					5 == updatedX);

			assertTrue(
					"The current location of the character is not updated correctly after moving up. Expected Y coorditane "
							+ 4 + " but was " + updatedY,
					4 == updatedY);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveLeft() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 3 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "LEFT"));
			Point location = (Point) getLocation.invoke(character);
			int updatedX = (int) location.getX();
			int updatedY = (int) location.getY();

			assertTrue(
					"The current location of the character is not updated correctly after moving left. Expected X coorditane "
							+ 4 + " but was " + updatedX,
					4 == updatedX);

			assertTrue(
					"The current location of the character is not updated correctly after moving left. Expected Y coorditane "
							+ 3 + " but was " + updatedY,
					3 == updatedY);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveRight() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Point location = (Point) getLocation.invoke(character);
			int updatedX = (int) location.getX();
			int updatedY = (int) location.getY();

			assertTrue(
					"The current location of the character is not updated correctly after moving right. Expected X coorditane "
							+ 4 + " but was " + updatedX,
					4 == updatedX);

			assertTrue(
					"The current location of the character is not updated correctly after moving right. Expected Y coorditane "
							+ 5 + " but was " + updatedY,
					5 == updatedY);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testInvalidMoveNoActionPoints() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 0);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "DOWN"));
			fail("Trying to move Down with no enough action points, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		} catch (InvocationTargetException e) {
			try {

				if (!(Class.forName(notEnoughActionsExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to move Down with no enough action points, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a notEnoughActionsException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateActionsAvailable() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Method getActionsAvailable = character.getClass().getMethod("getActionsAvailable");
			int actions = (int) getActionsAvailable.invoke(character);

			assertTrue("The available actions of the character is not updated correctly after moving. Expected " + 50
					+ " but was " + actions, 50 == actions);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateVisibility() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = null;
		try {
			m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

		try {
			Cell cell = m[4][4];
			Method isVisible = cell.getClass().getMethod("isVisible");
			boolean visible = (boolean) isVisible.invoke(cell);

			assertTrue("The visibility of cells should be updated after moving. Expected visibility = true"
					+ " but was false ", true == visible);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have isVisible method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveCollectibleCellVacine() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = null;
		try {
			m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			m[4][5] = new CollectibleCell((Collectible) new Vaccine());

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> v = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);

			assertTrue("The vaccine inventory arraylist should be updated after moving to a collectible cell. "
					+ "Expected arraylist of size 1" + " but was 0 ", v.size() > 0);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have getVaccineInventory method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveCollectibleCellSupply() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = null;
		try {
			m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			m[4][5] = new CollectibleCell((Collectible) new Supply());

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

		try {
			Method getSupplyInventory = character.getClass().getMethod("getSupplyInventory");
			ArrayList<Supply> v = (ArrayList<Supply>) getSupplyInventory.invoke(character);

			assertTrue("The supply inventory arraylist should be updated after moving to a collectible cell. "
					+ "Expected arraylist of size 1" + " but was 0 ", v.size() > 0);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have getSupplyInventory method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateOldCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Cell old = (CharacterCell) m[4][4];
			Method getCharacter = old.getClass().getMethod("getCharacter");
			Character oldChar = (Character) getCharacter.invoke(old);

			assertTrue(
					"The old cell is not updated correctly after moving. Expected empty cell but was occupied with a character",
					oldChar == null);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateNewCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 1000;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Cell newC = (CharacterCell) m[4][5];
			Method getCharacter = newC.getClass().getMethod("getCharacter");
			Character newChar = (Character) getCharacter.invoke(newC);
			assertTrue("The new cell is not updated correctly after moving. The cell should contain the new character.",
					newChar == (Character) character);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateOldCellDead() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, 0, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Cell old = (CharacterCell) m[4][4];
			Method getCharacter = old.getClass().getMethod("getCharacter");
			Character oldChar = (Character) getCharacter.invoke(old);

			assertTrue(
					"The old cell is not updated correctly after moving to a trap cell. Expected empty cell but was occupied with a character",
					oldChar == null);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateNewCellDead() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, 0, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);

		try {
			Cell[][] m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method getLocation = character.getClass().getMethod("getLocation");
			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));
			Cell newC = (CharacterCell) m[4][5];
			Method getCharacter = newC.getClass().getMethod("getCharacter");
			Character newChar = (Character) getCharacter.invoke(newC);

			assertTrue(
					"The new cell  is not updated correctly after moving to a trap cell. Expected empty cell but was occupied.",
					newChar == null);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

	}

	@Test(timeout = 3000)
	public void testValidMoveUpdateVisibilityDead() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = 51;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, 0, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = null;
		try {
			m = engine.Game.map;
			CharacterCell c = new CharacterCell(null);
			for (int i = -1; i <= 1; i++) {
				int cx = 4 + i;
				for (int j = -1; j <= 1; j++) {
					int cy = 5 + j;
					if (cy >= 0 && cy <= m.length - 1) {
						m[cx][cy] = c;
					}
				}
			}
			m[4][4] = new CharacterCell((Character) character);

			Method m3 = Class.forName(heroPath).getDeclaredMethod("move", Class.forName(directionPath));
			m3.invoke(character, Enum.valueOf((Class<Enum>) Class.forName(directionPath), "RIGHT"));

		} catch (NoSuchMethodException e) {
			fail("Hero class should have move method");
		}

		try {
			Cell cell = m[4][4];
			Method isVisible = cell.getClass().getMethod("isVisible");
			boolean visible = (boolean) isVisible.invoke(cell);

			assertTrue("The visibility of cells should not be updated as the hero is dead. Expected visibility = true"
					+ " but was false ", false == visible);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have isVisible method");
		}

	}

	@Test(timeout = 3000)
	public void testInvalidAttackNoTarget() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("attack");
			m3.invoke(character);
			fail("Trying to attack with no target, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have attack method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to attack with no target, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a invalidTargetExceptionPath class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidAttackInvalidTargetNotAdjacent() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		setLocation.invoke(character2, new Point(0, 0));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("attack");
			m3.invoke(character);
			fail("Trying to attack an invalid target that is not adjacent to you, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have attack method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to attack an invalid target that is not adjacent to you, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a notEnoughActionsException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidAttackInvalidTargetNotZombie() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Object character2 = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("attack");
			m3.invoke(character);
			fail("Trying to attack an invalid target that is not a zombie, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have attack method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to attack an invalid target that is not a zombie, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a notEnoughActionsException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testValidAttackCallSuper() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = 100;
		int attackDmgHero = 20;
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method getAttackDmg = character.getClass().getMethod("getAttackDmg");
		int attackDamage = (int) getAttackDmg.invoke(character);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		Cell[][] m = engine.Game.map;
		setLocation.invoke(character, new Point(4, 4));
		m[4][4] = new CharacterCell((Character) character);
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		Method setCurrentHp = character.getClass().getMethod("setCurrentHp", int.class);
		setCurrentHp.invoke(character2, 100);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method getCurrentHp = character.getClass().getMethod("getCurrentHp");
			int targetHP1 = (int) getCurrentHp.invoke(character2);
			Method m3 = Class.forName(heroPath).getDeclaredMethod("attack");
			m3.invoke(character);
			int targetHP = (int) getCurrentHp.invoke(character2);

			assertTrue("Attack method of Character class has to be used in Hero class with super keyword .",
					targetHP == targetHP1 - attackDamage);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have attack method");

		}

	}

	@Test(timeout = 3000)
	public void testValidUseSpecialUpdateSupplyInventory() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		try {
			Method getSupplyInventory = character.getClass().getMethod("getSupplyInventory");
			ArrayList<Supply> inventory1 = (ArrayList<Supply>) getSupplyInventory.invoke(character);
			inventory1.add(new Supply());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("useSpecial");
			m3.invoke(character);

			ArrayList<Supply> inventory2 = (ArrayList<Supply>) getSupplyInventory.invoke(character);

			assertTrue(
					"The used supply should be removed from the hero's inventory. Expected "
							+ "empty arraylist but the arraylist size was " + inventory2.size(),
					0 == inventory2.size());

		} catch (NoSuchMethodException e) {
			fail("Hero class should have useSpecial method");

		}

	}

	@Test(timeout = 3000)
	public void testValidUseSpecialSetSpecialAction() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		Method getSupplyInventory = character.getClass().getMethod("getSupplyInventory");
		ArrayList<Supply> inventory1 = (ArrayList<Supply>) getSupplyInventory.invoke(character);
		inventory1.add(new Supply());

		try {
			Method setSpecialAction = character.getClass().getMethod("setSpecialAction", boolean.class);
			setSpecialAction.invoke(character, false);
		} catch (NoSuchMethodException e) {
			fail("Hero class should have setSpecialAction method.");

		}

		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("useSpecial");
			m3.invoke(character);
		} catch (NoSuchMethodException e) {
			fail("Hero class should have useSpecial method.");

		}
		try {
			Method isSpecialActionM = character.getClass().getMethod("isSpecialAction");
			Boolean isSpecialAction = (Boolean) isSpecialActionM.invoke(character);

			assertTrue("The boolean specialAction should be set to true. Expected true but was false",
					true == isSpecialAction);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have isSpecialAction method.");

		}

	}

	@Test(timeout = 3000)
	public void testInvalidCureNotEnoughActions() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character2, new Point(4, 5));
		setLocation.invoke(character, new Point(4, 4));
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 0);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);
			fail("Trying to cure a zombie with no enough actions, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(notEnoughActionsExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to cure a zombie with no enough actions, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a notEnoughActionsException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidCureNoTarget() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		setLocation.invoke(character2, new Point(4, 5));
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);
			fail("Trying to cure a zombie with no target, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to cure a zombie with no target, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a notEnoughActionsException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidCureNotAdjacentZombie() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		setLocation.invoke(character2, new Point(14, 14));
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);
			fail("Trying to cure a not adjacent zombie, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to cure a not adjacent zombie, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be an InvalidTargetException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testInvalidCureNotZombie() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character2 = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character2, new Point(4, 5));
		setLocation.invoke(character, new Point(4, 4));
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);

		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, maxActionsHero);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);
			fail("Trying to cure a character that is not a zombie, an exception should be thrown");

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(invalidTargetExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to cure a character that is not a zombie, an exception should be thrown");

			} catch (ClassNotFoundException e1) {

				fail("There should be a InvalidTargetException class");
			}

		}

	}

	@Test(timeout = 3000)
	public void testValidCureUpdateVaccineInventory() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Object character3 = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Game.heroes = new ArrayList<>();
		Game.availableHeroes = new ArrayList<>();
		Game.heroes.add((Hero) character);
		Game.heroes.add((Hero) character3);
		Game.availableHeroes.add((Hero) character);
		Game.availableHeroes.add((Hero) character3);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);

			ArrayList<Vaccine> inventory2 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);

			assertTrue(
					"The used vaccine should be removed from the hero's inventory. Expected "
							+ "empty arraylist but the arraylist size was " + inventory2.size(),
					0 == inventory2.size());

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");

		}

	}

	@Test(timeout = 3000)
	public void testValidCureUpdateActionsAvailable() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Object character3 = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Game.heroes = new ArrayList<>();
		Game.availableHeroes = new ArrayList<>();
		Game.heroes.add((Hero) character);
		Game.heroes.add((Hero) character3);
		Game.availableHeroes.add((Hero) character);
		Game.availableHeroes.add((Hero) character3);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		try {
			Method getVaccineInventory = character.getClass().getMethod("getVaccineInventory");
			ArrayList<Vaccine> inventory1 = (ArrayList<Vaccine>) getVaccineInventory.invoke(character);
			inventory1.add(new Vaccine());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("cure");
			m3.invoke(character);

			Method getActionsAvailable = character.getClass().getMethod("getActionsAvailable");
			int actions = (int) getActionsAvailable.invoke(character);

			assertTrue(
					"The available actions of the character is not updated correctly after curing a zombie. Expected "
							+ 50 + " but was " + actions,
					50 == actions);

		} catch (NoSuchMethodException e) {
			fail("Hero class should have cure method");

		}

	}

	private void generateMap() {
		Constructor<?> gameConstructor;
		Field f = null;
		try {
			gameConstructor = Class.forName(gamePath).getConstructor();
			Object createdGame = gameConstructor.newInstance();
			Class<? extends Object> curr = createdGame.getClass();
			f = curr.getDeclaredField("map");
			f.setAccessible(true);
			f.set(createdGame, new Cell[15][15]);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Test(timeout = 5000)
	public void testZombieAttackDirections() {

		int maxHp = 1;
		int attackDamage = 1;
		int maxActions = 1;

		int zombieXLocation = 3;
		int zombieYLocation = 3;

		ArrayList<Hero> testCharactersList = null;
		Class<?> characterClass = null;

		try {

			Class<?> fighterClass = Class.forName(fighterPath);
			characterClass = Class.forName(characterPath);
			Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class,
					int.class);

			testCharactersList = new ArrayList<Hero>();

			for (int i = 0; i < 9; i++) {
				testCharactersList
						.add((Hero) constructorFighter.newInstance("Bob " + i, maxHp, attackDamage, maxActions));
			}

		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Fighters. Check the fighter, hero and character constuctors!");
		}

		Object[] array = testCharactersList.toArray();
		Hero[] testCharacters = new Hero[array.length];

		for (int i = 0; i < 9; i++)
			testCharacters[i] = (Hero) array[i];

		Object character2 = null;

		try {

			Class<?> zombieClass = Class.forName(zombiePath);
			Constructor<?> constructorZombie = zombieClass.getConstructor();
			character2 = constructorZombie.newInstance();

		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Zombies. Check the zombie and character constuctors!");
		}

		Class<?> gameClass = null;
		try {
			gameClass = Class.forName(gamePath);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to create a game.");
		}

		Field mapField = null;
		Cell[][] tmpMap = null;

		Field heroField = null;
		ArrayList<Hero> heroList = null;
		Method setLocation = null;

		Field zombieField = null;
		ArrayList<Zombie> zombieList = null;

		try {

			setLocation = characterClass.getMethod("setLocation", Point.class);
			heroField = Game.class.getDeclaredField("heroes");
			heroList = ((ArrayList<Hero>) heroField.get(gameClass));

			mapField = Game.class.getDeclaredField("map");

			tmpMap = (Cell[][]) mapField.get(gameClass);

			zombieField = Game.class.getDeclaredField("zombies");
			zombieList = ((ArrayList<Zombie>) zombieField.get(gameClass));
			heroList.clear();
			zombieList.clear();

			zombieList.add((Zombie) character2);

			Point location2 = new Point(zombieXLocation, zombieYLocation);
			setLocation.invoke(character2, location2);
			tmpMap[zombieXLocation][zombieYLocation] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		try {
			Method startGame = gameClass.getMethod("startGame", Hero.class);
			startGame.invoke(gameClass, testCharacters[0]);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " occuered while trying to start a game");
		}

		Iterator<Zombie> iterator = zombieList.iterator();

		while (iterator.hasNext()) {

			Zombie z = iterator.next();

			if (!z.equals((Zombie) character2)) {
				Point locationZ = z.getLocation();

				((CharacterCell) Game.map[locationZ.x][locationZ.y]).setCharacter(null);
				iterator.remove();
			}
		}

		int charCount = 1;
		for (int i = -1; i <= 1; i++) {
			int cx = zombieXLocation + i;
			for (int j = -1; j <= 1; j++) {
				int cy = zombieYLocation + j;

				if (!(cx == zombieXLocation && cy == zombieYLocation)) {
					Point location2 = new Point(cx, cy);

					try {
						setLocation.invoke(testCharacters[charCount], location2);
					} catch (Exception e) {
						fail(e.getCause().getClass() + " occurred while trying to set location of Fighters");
					}

					tmpMap[cx][cy] = new CharacterCell(testCharacters[charCount]);
					heroList.add(testCharacters[charCount]);
					charCount++;
				}
			}
		}

		for (int i = 0; i < 8; i++) {

			try {
				Method endTurn = gameClass.getMethod("endTurn");
				endTurn.invoke(gameClass);
			} catch (Exception e) {
				fail(e.getCause().getClass() + " ccuered while trying to end turn, check the Zombies attack!");
			}
		}

		boolean isAllDead = heroList.size() <= 1;

		assertEquals("All 8 heros around Zombie should be dead after attacking all of them", isAllDead, true);
	}

	@Test(timeout = 5000)
	public void testZombieAttackOutOfBoundsMax() {

		int maxHp = 1;
		int attackDamage = 1;
		int maxActions = 1;

		ArrayList<Hero> testCharactersList = null;
		Class<?> characterClass = null;

		try {

			Class<?> fighterClass = Class.forName(fighterPath);
			characterClass = Class.forName(characterPath);
			Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class,
					int.class);

			testCharactersList = new ArrayList<Hero>();

			for (int i = 0; i < 8; i++) {
				testCharactersList
						.add((Hero) constructorFighter.newInstance("Bob " + i, maxHp, attackDamage, maxActions));
			}

		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Fighters. Check the fighter, hero and character constuctors!");
		}

		Object[] array = testCharactersList.toArray();
		Hero[] testCharacters = new Hero[array.length];

		for (int i = 0; i < 8; i++)
			testCharacters[i] = (Hero) array[i];

		Object character2 = null;

		try {

			Class<?> zombieClass = Class.forName(zombiePath);
			Constructor<?> constructorZombie = zombieClass.getConstructor();
			character2 = constructorZombie.newInstance();

		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Zombies. Check the zombie and character constuctors!");
		}

		Class<?> gameClass = null;
		try {
			gameClass = Class.forName(gamePath);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to create a game.");
		}

		Field mapField = null;
		Cell[][] tmpMap = null;

		Field heroField = null;
		ArrayList<Hero> heroList = null;
		Method setLocation = null;

		Field zombieField = null;
		ArrayList<Zombie> zombieList = null;

		try {

			setLocation = characterClass.getMethod("setLocation", Point.class);
			heroField = Game.class.getDeclaredField("heroes");
			heroList = ((ArrayList<Hero>) heroField.get(gameClass));

			mapField = Game.class.getDeclaredField("map");

			tmpMap = (Cell[][]) mapField.get(gameClass);

			zombieField = Game.class.getDeclaredField("zombies");
			zombieList = ((ArrayList<Zombie>) zombieField.get(gameClass));
			heroList.clear();
			zombieList.clear();

			zombieList.add((Zombie) character2);

			int zombieXLocation = tmpMap.length - 1;
			int zombieYLocation = tmpMap[0].length - 1;

			Point location2 = new Point(zombieXLocation, zombieYLocation);
			setLocation.invoke(character2, location2);
			tmpMap[zombieXLocation][zombieYLocation] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Point location2 = new Point(1, 1);

		try {
			setLocation.invoke(testCharacters[0], location2);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to set location of Fighters");
		}

		try {
			Method startGame = gameClass.getMethod("startGame", Hero.class);
			startGame.invoke(gameClass, testCharacters[0]);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to start a game");
		}

		for (int i = 0; i < 8; i++) {

			Iterator<Zombie> iterator = zombieList.iterator();

			while (iterator.hasNext()) {

				Zombie z = iterator.next();

				if (!z.equals((Zombie) character2)) {
					Point locationZ = z.getLocation();

					((CharacterCell) Game.map[locationZ.x][locationZ.y]).setCharacter(null);
					iterator.remove();
				}
			}

			try {
				Method endTurn = gameClass.getMethod("endTurn");
				endTurn.invoke(gameClass);

			} catch (Exception e) {
				fail(e.getCause().getClass() + " Make sure the zombie doesn't attack out of bounds of the map!");
			}
		}

		assertTrue(true);
	}

	@Test(timeout = 1000)
	public void testFighterAttackZombieUpdatesCurrentHP() {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = (int) (Math.random() * 30) + 1;
		int maxActions = 1;

		Point location1 = new Point(3, 3);
		Point location2 = new Point(4, 3);

		Class<?> characterClass = null;
		Object character1 = null;

		try {
			Class<?> fighterClass = Class.forName(fighterPath);
			characterClass = Class.forName(characterPath);
			Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class,
					int.class);

			character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Fighters. Check the fighter, hero and character constuctors!");
		}

		Object character2 = null;
		try {
			Class<?> zombieClass = Class.forName(zombiePath);
			Constructor<?> constructorZombie = zombieClass.getConstructor();
			character2 = constructorZombie.newInstance();
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " ccuered while trying to create Zombies. Check the zombie and character constuctors!");
		}

		try {
			Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
			setTargetMethod.invoke(character1, character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to assign a target to a character");
		}

		try {
			Method setLocation = characterClass.getMethod("setLocation", Point.class);
			setLocation.invoke(character1, location1);
			setLocation.invoke(character2, location2);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to assign a location to a character");
		}

		Class<?> gameClass = null;
		try {
			gameClass = Class.forName(gamePath);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to create a game.");
		}

		try {
			Field myField = Game.class.getDeclaredField("map");
			Cell[][] tmpMap = (Cell[][]) myField.get(gameClass);

			tmpMap[3][3] = new CharacterCell((Character) character1);
			tmpMap[4][3] = new CharacterCell((Character) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		try {
			Method attackMethod = characterClass.getMethod("attack");
			attackMethod.invoke(character1);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to attack a character");
		}

		try {
			Method getCurrentHpMethod = characterClass.getMethod("getCurrentHp");
			int expectedHp = 40 - attackDamage;
			int actualHp = (int) getCurrentHpMethod.invoke(character2);
			assertEquals(expectedHp, actualHp);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " ccuered while trying to get HP of a character");
		}

	}

	@Test(timeout = 1000)
	public void testFighterSpecialActionResets() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 1000;
		int attackDamage = 1;
		int maxActions = (int) (Math.random() * 10) + 2;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Class<?> gameClass = Class.forName(gamePath);
		Field heroField = null;
		try {

			heroField = Game.class.getDeclaredField("heroes");
		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while getting Heros array list");
		}
		((ArrayList<Hero>) heroField.get(gameClass)).clear();

		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;

		try {

			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);
			Method setLocation = characterClass.getMethod("setLocation", Point.class);

			Field zombieField = Game.class.getDeclaredField("zombies");
			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);

			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = fighterClass.getMethod("attack");
		Method setSpecialActionMethod = fighterClass.getMethod("setSpecialAction", boolean.class);

		setSpecialActionMethod.invoke(character1, true);

		try {
			for (int i = 0; i < maxActions; i++) {
				attackMethod.invoke(character1);
			}
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " occurred while trying to attack without enough action points as a fighter using their special action");
		}

		Method endTurn = gameClass.getMethod("endTurn");
		endTurn.invoke(gameClass);

		Method getSpecialActionMethod = fighterClass.getMethod("isSpecialAction");
		boolean check = (boolean) getSpecialActionMethod.invoke(character1);
		assertFalse("Special action should rest on end turn if it was previously activaed", check);
	}

	@Test(timeout = 1000)
	public void testZombieDeath() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = 40;
		int maxActions = 1;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);

		Class<?> gameClass = Class.forName(gamePath);

		Cell[][] tmpMap = null;
		Field zombieField = null;

		try {

			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);

			Method startGame = gameClass.getMethod("startGame", Hero.class);
			startGame.invoke(gameClass, character1);

			for (int i = 0; i <= 2; i++) {
				for (int j = 0; j <= 2; j++) {
					if (i == 0 && j == 0)
						continue;
					tmpMap[i][j] = new CharacterCell(null);
				}
			}

			zombieField = Game.class.getDeclaredField("zombies");

			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);
			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = characterClass.getMethod("attack");
		attackMethod.invoke(character1);

		boolean isDead = ((CharacterCell) tmpMap[1][1]).getCharacter() == null;
		isDead = isDead && !((ArrayList<Zombie>) zombieField.get(gameClass)).contains(character2);
		assertEquals("The Zombie is considered dead if it nolonger exists on the map and in the Zombies array ", isDead,
				true);
	}

	@Test(timeout = 1000)
	public void testFighterAttackZombieUsesActionPoints() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 150;
		int attackDamage = (int) (Math.random() * 5) + 1;
		int maxActions = (int) (Math.random() * 5) + 1;

		int numberOfAttacks = (int) (Math.random() * maxActions) + 1;

		Point location1 = new Point(3, 3);
		Point location2 = new Point(4, 3);

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);
		setLocation.invoke(character1, location1);
		setLocation.invoke(character2, location2);

		Class<?> gameClass = Class.forName(gamePath);

		try {
			Field myField = Game.class.getDeclaredField("map");
			Cell[][] tmpMap = (Cell[][]) myField.get(gameClass);

			tmpMap[3][3] = new CharacterCell((Character) character1);
			tmpMap[4][3] = new CharacterCell((Character) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = characterClass.getMethod("attack");

		for (int i = 0; i < numberOfAttacks; i++) {

			attackMethod.invoke(character1);
		}

		Method getAPMethod = fighterClass.getMethod("getActionsAvailable");
		int actionsAvailable = (int) getAPMethod.invoke(character1);
		assertEquals("The actions available should be reduced by 1 after each attack", maxActions - numberOfAttacks,
				actionsAvailable);
	}

	@Test(timeout = 1000)
	public void testFighterDefenseUpdatesCurrentHP() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 11;
		int attackDamage = (int) (Math.random() * 30) + 1;
		int maxActions = 1;

		Point location1 = new Point(3, 3);
		Point location2 = new Point(4, 3);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character1 = constructorZombie.newInstance();

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character2 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);
		setLocation.invoke(character1, location1);
		setLocation.invoke(character2, location2);

		Class<?> gameClass = Class.forName(gamePath);

		try {
			Field myField = Game.class.getDeclaredField("map");
			Cell[][] tmpMap = (Cell[][]) myField.get(gameClass);

			for (int i = 2; i <= 5; i++) {
				for (int j = 2; j <= 5; j++) {
					tmpMap[i][j] = new CharacterCell(null);
				}
			}

			tmpMap[3][3] = new CharacterCell((Character) character1);
			tmpMap[4][3] = new CharacterCell((Character) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = zombieClass.getMethod("attack");
		attackMethod.invoke(character1);

		Method getCurrentHpMethod = characterClass.getMethod("getCurrentHp");
		int expectedHp = 40 - (attackDamage / 2);
		int actualHp = (int) getCurrentHpMethod.invoke(character1);
		assertEquals(expectedHp, actualHp);
	}

	@Test(timeout = 1000)
	public void testFighterSpecialActionOne() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = 1;
		int maxActions = 1;

		Point location1 = new Point(3, 3);
		Point location2 = new Point(4, 3);

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);
		setLocation.invoke(character1, location1);
		setLocation.invoke(character2, location2);

		Class<?> gameClass = Class.forName(gamePath);

		try {
			Field myField = Game.class.getDeclaredField("map");
			Cell[][] tmpMap = (Cell[][]) myField.get(gameClass);

			tmpMap[3][3] = new CharacterCell((Character) character1);
			tmpMap[4][3] = new CharacterCell((Character) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}


		Method attackMethod = fighterClass.getMethod("attack");
		Method setSpecialActionMethod = fighterClass.getMethod("setSpecialAction", boolean.class);

		setSpecialActionMethod.invoke(character1, true);
		try {
			for (int i = 0; i < 35; i++) {
				attackMethod.invoke(character1);
			}
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " occurred while trying to attack without enough action points as a fighter using their special action");
		}

		Method getCurrentApMethod = fighterClass.getMethod("getActionsAvailable");
		int expectedAp = maxActions;
		int actualAp = (int) getCurrentApMethod.invoke(character1);
		assertEquals(expectedAp, actualAp);
	}

	@Test(timeout = 1000)
	public void testFighterSpecialActionMany() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = 1;
		int maxActions = (int) (Math.random() * 10) + 2;

		Point location1 = new Point(3, 3);
		Point location2 = new Point(4, 3);

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);
		setLocation.invoke(character1, location1);
		setLocation.invoke(character2, location2);

		Class<?> gameClass = Class.forName(gamePath);

		try {
			Field myField = Game.class.getDeclaredField("map");
			Cell[][] tmpMap = (Cell[][]) myField.get(gameClass);

			tmpMap[3][3] = new CharacterCell((Character) character1);
			tmpMap[4][3] = new CharacterCell((Character) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = fighterClass.getMethod("attack");
		Method setSpecialActionMethod = fighterClass.getMethod("setSpecialAction", boolean.class);

		setSpecialActionMethod.invoke(character1, true);
		try {
			for (int i = 0; i < 35; i++) {
				attackMethod.invoke(character1);
			}
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " occurred while trying to attack without enough action points as a fighter using their special action");
		}

		Method getCurrentApMethod = fighterClass.getMethod("getActionsAvailable");
		int expectedAp = maxActions;
		int actualAp = (int) getCurrentApMethod.invoke(character1);
		assertEquals(expectedAp, actualAp);
	}

	@Test(timeout = 1000)
	public void testFighterActionPointsResets() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 1000;
		int attackDamage = 1;
		int maxActions = (int) (Math.random() * 10) + 2;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Class<?> gameClass = Class.forName(gamePath);
		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;
		Field heroField = null;

		try {

			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);
			Method setLocation = characterClass.getMethod("setLocation", Point.class);
			heroField = Game.class.getDeclaredField("heroes");

			Field zombieField = Game.class.getDeclaredField("zombies");
			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);
			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = fighterClass.getMethod("attack");
		Method setSpecialActionMethod = fighterClass.getMethod("setSpecialAction", boolean.class);

		try {
			for (int i = 0; i < maxActions; i++) {
				attackMethod.invoke(character1);
			}
		} catch (Exception e) {
			fail(e.getCause().getClass()
					+ " occurred while trying to attack without enough action points as a fighter using their special action");
		}

		Method endTurn = gameClass.getMethod("endTurn");
		endTurn.invoke(gameClass);

		Method getCurrentApMethod = fighterClass.getMethod("getActionsAvailable");
		int expectedAp = maxActions;
		int actualAp = (int) getCurrentApMethod.invoke(character1);
		assertEquals(expectedAp, actualAp);
	}

	@Test(timeout = 1000)
	public void testFighterDeathOnDefense() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = 1;
		int attackDamage = 1;
		int maxActions = 1;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Class<?> gameClass = Class.forName(gamePath);
		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;
		Field heroField = null;

		try {

			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);
			Method setLocation = characterClass.getMethod("setLocation", Point.class);
			heroField = Game.class.getDeclaredField("heroes");

			Field zombieField = Game.class.getDeclaredField("zombies");
			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);

			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method attackMethod = fighterClass.getMethod("attack");
		attackMethod.invoke(character1);

		boolean isDead = ((CharacterCell) tmpMap[0][0]).getCharacter() == null;
		isDead = !((ArrayList<Hero>) heroField.get(gameClass)).contains(character1);

		assertEquals(
				"The Hero should die if they attack an enemy and it defends dealing more damage than their current HP  ",
				isDead, true);
	}

	@Test(timeout = 1000, expected = exceptions.InvalidTargetException.class)
	public void testFighterAttackOutOfRange() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException, InvalidTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = (int) (Math.random() * 35) + 1;
		int maxActions = 1;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);

		Class<?> gameClass = Class.forName(gamePath);
		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;
		Field zombieField = null;

		try {
			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);

			zombieField = Game.class.getDeclaredField("zombies");

			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);
			Point location2 = new Point(5, 5);
			setLocation.invoke(character2, location2);
			tmpMap[5][5] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		Method getHP = characterClass.getMethod("getCurrentHp");

		try {
			((Hero) character1).attack();
		} catch (NotEnoughActionsException e) {
			fail(e.getCause().getClass() + " Not enough action points should NOT fire");
		}

	}

	@Test(timeout = 1000, expected = exceptions.InvalidTargetException.class)
	public void testFighterAttackFighter() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InstantiationException, InvocationTargetException, InvalidTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = (int) (Math.random() * 35) + 1;
		int maxActions = 1;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Object character2 = constructorFighter.newInstance("Alex", maxHp, attackDamage, maxActions);

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);

		Class<?> gameClass = Class.forName(gamePath);
		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;
		Field heroField = null;

		try {
			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);

			heroField = Game.class.getDeclaredField("heroes");

			((ArrayList<Hero>) heroField.get(gameClass)).add((Hero) character2);

			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Hero) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		try {
			((Hero) character1).attack();
		} catch (NotEnoughActionsException e) {
			fail(e.getCause().getClass() + " Not enough action points should NOT fire");
		}

	}

	@Test(timeout = 1000)
	public void testZombieDeathSpawnsNewZombie() throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InstantiationException, InvocationTargetException {

		int maxHp = (int) (Math.random() * 100) + 10;
		int attackDamage = 40;
		int maxActions = 1;

		Class<?> fighterClass = Class.forName(fighterPath);
		Class<?> characterClass = Class.forName(characterPath);
		Constructor<?> constructorFighter = fighterClass.getConstructor(String.class, int.class, int.class, int.class);
		Object character1 = constructorFighter.newInstance("Bob", maxHp, attackDamage, maxActions);

		Class<?> zombieClass = Class.forName(zombiePath);
		Constructor<?> constructorZombie = zombieClass.getConstructor();
		Object character2 = constructorZombie.newInstance();

		Method setTargetMethod = characterClass.getMethod("setTarget", characterClass);
		setTargetMethod.invoke(character1, character2);

		Method setLocation = characterClass.getMethod("setLocation", Point.class);

		Class<?> gameClass = Class.forName(gamePath);
		Method startGame = gameClass.getMethod("startGame", Hero.class);
		startGame.invoke(gameClass, character1);

		Cell[][] tmpMap = null;
		Field zombieField = null;

		try {

			Field mapField = Game.class.getDeclaredField("map");
			tmpMap = (Cell[][]) mapField.get(gameClass);

			zombieField = Game.class.getDeclaredField("zombies");

			((ArrayList<Zombie>) zombieField.get(gameClass)).add((Zombie) character2);
			Point location2 = new Point(1, 1);
			setLocation.invoke(character2, location2);
			tmpMap[1][1] = new CharacterCell((Zombie) character2);

		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		int firstCount = helperCountZombies();

		Method attackMethod = characterClass.getMethod("attack");
		attackMethod.invoke(character1);

		int secondCount = helperCountZombies();

		assertEquals("The Zombie count should be the same after a zombie is killed", firstCount, secondCount);
	}

	public int helperCountZombies() {

		Cell[][] map = null;

		try {
			Field mapField = Game.class.getDeclaredField("map");
			map = (Cell[][]) mapField.get(Game.class);
		} catch (Exception e) {
			fail(e.getCause().getClass() + " occurred while trying to get Map variable from the Game Class");
		}

		int count = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof CharacterCell)
					if (((CharacterCell) map[i][j]).getCharacter() instanceof Zombie)
						count++;
			}
		}

		return count;
	}

	@Test(timeout = 3000)
	public void testCollectibleIsInterface() {
		try {
			testIsInterface(Class.forName(collectiblePath));
		} catch (Exception e) {
			assertTrue("Package model.collectibles should contain an interface called Collectible.", false);
		}
	}

	@Test(timeout = 3000)
	public void testPickUpMethodInCollectible() {
		try {
			testMethodExistsInClass(Class.forName(collectiblePath), "pickUp", true, void.class,
					Class.forName(heroPath));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseMethodInCollectible() {
		try {
			testMethodExistsInClass(Class.forName(collectiblePath), "use", true, void.class, Class.forName(heroPath));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testPickUpMethodInSupply() {
		try {
			testMethodExistsInClass(Class.forName(supplyPath), "pickUp", true, void.class, Class.forName(heroPath));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseMethodInSupply() {
		try {
			testMethodExistsInClass(Class.forName(supplyPath), "use", true, void.class, Class.forName(heroPath));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseMethodLogicInSupply() {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		try {
			Class<?> classHero = Class.forName(heroPath);
			Field supplyInventory = classHero.getDeclaredField("supplyInventory");
			supplyInventory.setAccessible(true);

			Constructor<?> constructorHero = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
					int.class);
			Object hero = constructorHero.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
			Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
			Object supply = constructorSupply.newInstance();

			Method methodPickUp = Class.forName(supplyPath).getDeclaredMethod("pickUp", Class.forName(heroPath));
			methodPickUp.invoke(supply, hero);

			Method method = Class.forName(supplyPath).getDeclaredMethod("use", Class.forName(heroPath));
			method.invoke(supply, hero);
			ArrayList<?> supplyObject = new ArrayList<>();
			assertEquals("The field supply inventory should not include a supply instance after calling use method",
					supplyObject, supplyInventory.get(hero));
		} catch (NoSuchFieldException e) {
			assertTrue("Class hero should have a field called supplyInventory", false);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.collectibles should contain a class called Supply.", false);
			e.printStackTrace();
		} catch (InstantiationException e) {
			assertTrue("Objects of type hero can not be instantiated.", false);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			assertTrue("Class supply should contain a method called pickUp that takes a hero as a parameter.", false);
			e.printStackTrace();
		} catch (Exception e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testPickUpMethodLogicInVaccine() {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		try {
			Constructor<?> constructorHero = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
					int.class);
			Object hero = constructorHero.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
			Constructor<?> constructorSupply = Class.forName(vaccinePath).getConstructor();
			Object vaccine = constructorSupply.newInstance();
			Method method = Class.forName(vaccinePath).getDeclaredMethod("pickUp", Class.forName(heroPath));
			method.invoke(vaccine, hero);
			Class<?> classHero = Class.forName(heroPath);
			Field vaccineInventory = classHero.getDeclaredField("vaccineInventory");
			vaccineInventory.setAccessible(true);
			ArrayList vaccineObject = new ArrayList<>();
			vaccineObject.add(vaccine);
			assertEquals("The field vaccine inventory should not include a vaccine instance after calling use method",
					vaccineObject, vaccineInventory.get(hero));
		} catch (NoSuchFieldException e) {
			assertTrue("Class hero should have a field called vaccineInventory", false);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.collectibles should contain a class called Supply.", false);
		} catch (InstantiationException e) {
			assertTrue("Objects of type hero can not be instantiated.", false);
		} catch (NoSuchMethodException e) {
			assertTrue("Class vaccine should contain a method called pickUp that takes a hero as a parameter.", false);
		} catch (Exception e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseMethodLogicInVaccine() {
		ArrayList<?> x = setEnvironment();
		for (int i = 0; i < Game.map.length; i++) {
			for (int j = 0; j < Game.map[i].length; j++) {
				Game.map[i][j] = new CharacterCell(null);
			}
		}
		try {
			Class<?> classHero = Class.forName(heroPath);
			Field vaccineInventory = classHero.getDeclaredField("vaccineInventory");
			vaccineInventory.setAccessible(true);

			Constructor<?> constructorVaccine = Class.forName(vaccinePath).getConstructor();
			Object vaccine = constructorVaccine.newInstance();

			Method methodPickUp = Class.forName(vaccinePath).getDeclaredMethod("pickUp", Class.forName(heroPath));
			methodPickUp.invoke(vaccine, x.get(0));

			ArrayList<?> supplyObject = new ArrayList<>();
			Method method = Class.forName(vaccinePath).getDeclaredMethod("use", Class.forName(heroPath));
			method.invoke(vaccine, x.get(0));
			assertEquals("The field vaccine inventory should not include a vaccine instance after using it",
					supplyObject, vaccineInventory.get(x.get(0)));
			assertEquals("You should remove the hero's target from the Zombies array in Game", 0, Game.zombies.size());
			assertEquals("You should randomly get an available hero, remove it from the available heros array.", 2,
					Game.availableHeroes.size());
			assertEquals("You should add the chosen hero to the heros array in Game.", 2, Game.heroes.size());
			assertEquals("The new hero should have its location set to the location he spawned in.", new Point(1, 0),
					Game.heroes.get(1).getLocation());

			String so = ((CharacterCell) Game.map[1][0]).getCharacter().getName();
			String[] ss = new String[] { "XYZ", "ABC", "123" };
			boolean contains = Arrays.stream(ss).anyMatch(so::equals);
			assertEquals(
					"The cell of the target should be assigned the character that was randomly chosen from available heros.",
					true, contains);
		} catch (Exception e) {
			if (e.getMessage().equals(
					"Cannot invoke \"model.characters.Character.getName()\" because the return value of \"model.world.CharacterCell.getCharacter()\" is null")) {
				assertTrue("You should set the character to the cell in location of the target.", false);
			}
			if (e.getMessage().equals(
					"Cannot read field \"x\" because the return value of \"model.characters.Hero.getLocation()\" is null")) {
				assertTrue("You should set the location of the picked available hero to the location of the target.",
						false);
			}
			if (e.getMessage().equals(
					"Cannot read field \"y\" because the return value of \"model.characters.Hero.getLocation()\" is null")) {
				assertTrue("You should set the location of the picked available hero to the location of the target.",
						false);
			}
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseSpecialMethodExistsInExplorer() {
		try {
			Class[] inputs = {};
			testMethodExistsInClass(Class.forName(explorerPath), "useSpecial", true, void.class, inputs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseSpecialMethodLogicInExplorer() {
		try {
			testInvalidUseSpecialNoAvailableResources();
			testValidUseSpecialSetSpecialAction1();
			testValidUseSpecialUpdateSupplyInventory1();
		} catch (Exception e) {
			assertTrue(
					"You have to have correct logic for useSpecial in Hero class as you will use it in useSpecial of Explorer class.",
					false);
			e.printStackTrace();
		}
		try {
			Constructor<?> constructorHero = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object explorerObject = constructorHero.newInstance("HamoudaGamed", 4, 5, 5);
			Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
			Object supply = constructorSupply.newInstance();
			Class<?> classHero = Class.forName(heroPath);
			Field supplyInventory = classHero.getDeclaredField("supplyInventory");
			supplyInventory.setAccessible(true);
			ArrayList<Supply> supplyArray = new ArrayList<>();
			supplyArray.add((Supply) supply);
			supplyInventory.set(explorerObject, supplyArray);
			Method useSpecialMethod = Class.forName(explorerPath).getDeclaredMethod("useSpecial");
			useSpecialMethod.invoke(explorerObject);
			ArrayList<Supply> supplyArrayEmpty = new ArrayList<>();
			assertEquals("useSpecial method of Hero class has to be used in Explorer class with super keyword",
					supplyInventory.get(explorerObject), supplyArrayEmpty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (!checkIfMapIsVisible()) {
				assertTrue("Your whole map should be marked as visible", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseSpecialMethodExistsInMedic() {
		try {
			Class[] inputs = {};
			testMethodExistsInClass(Class.forName(medicPath), "useSpecial", true, void.class, inputs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testUseSpecialMethodLogicInMedic() {
		try {
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("Eminem", 1000, 50, 20);
			Constructor<?> zombieConstructor = Class.forName(zombiePath).getConstructor();
			Object zombieObject = zombieConstructor.newInstance();
			Method setTarget = createdMedics1.getClass().getMethod("setTarget", Character.class);
			setTarget.invoke(createdMedics1, (Zombie) zombieObject);
			Method useSpecialMethod = Class.forName(medicPath).getDeclaredMethod("useSpecial");
			useSpecialMethod.invoke(createdMedics1);
		} catch (Exception e) {
			assertEquals("You should throw an exception of type: InvalidTargetException when the target is a zombie",
					"class exceptions.InvalidTargetException", e.getCause().getClass().toString());
		}
	}

	@Test(timeout = 3000)
	public void testUseSpecialMethodLogicInMedic4() {
		try {
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("Eminem", 1000, 50, 20);
			Constructor<?> zombieConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object zombieObject = zombieConstructor.newInstance("Eminem", 1000, 50, 20);
			Method setTarget = createdMedics1.getClass().getMethod("setTarget", Character.class);
			Method setLocation = createdMedics1.getClass().getMethod("setLocation", Point.class);
			setLocation.invoke(createdMedics1, new Point(4, 0));
			setLocation.invoke(zombieObject, new Point(0, 0));

			setTarget.invoke(createdMedics1, (Character) zombieObject);
			Class<?> classHero = Class.forName(heroPath);
			Field supplyInventory = classHero.getDeclaredField("supplyInventory");
			supplyInventory.setAccessible(true);
			Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
			Object supply = constructorSupply.newInstance();
			ArrayList<Supply> supplyArray = new ArrayList<>();
			supplyArray.add((Supply) supply);
			supplyInventory.set(createdMedics1, supplyArray);
			Method useSpecialMethod = Class.forName(medicPath).getDeclaredMethod("useSpecial");
			useSpecialMethod.invoke(createdMedics1);
			throw new Exception("You have to throw an exception");
		} catch (Exception e) {
			if (e.getClass().toString().equals("class java.lang.reflect.InvocationTargetException")) {
				return;
			}
			if (e.getMessage().equals("You have to throw an exception")) {
				assertTrue("You should throw an exception of type: InvalidTargetException when the target is not close",
						false);
			}
			e.printStackTrace();
		}
	}

	@Test(timeout = 300000)
	public void testUseSpecialMethodLogicInMedic3() {
		try {
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("Eminem", 1000, 50, 20);
			Constructor<?> zombieConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object zombieObject = zombieConstructor.newInstance("Eminem", 1000, 50, 20);
			Method setTarget = createdMedics1.getClass().getMethod("setTarget", Character.class);
			setTarget.invoke(createdMedics1, (Character) zombieObject);
			Method setLocation = createdMedics1.getClass().getMethod("setLocation", Point.class);
			setLocation.invoke(createdMedics1, new Point(0, 0));
			setLocation.invoke(zombieObject, new Point(0, 0));
			Class<?> classHero = Class.forName(heroPath);
			Field supplyInventory = classHero.getDeclaredField("supplyInventory");
			supplyInventory.setAccessible(true);
			Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
			Object supply = constructorSupply.newInstance();
			ArrayList<Supply> supplyArray = new ArrayList<>();
			supplyArray.add((Supply) supply);
			supplyInventory.set(createdMedics1, supplyArray);
			Method useSpecialMethod = Class.forName(medicPath).getDeclaredMethod("useSpecial");
			useSpecialMethod.invoke(createdMedics1);
			Method getCurrentHpMethod = zombieObject.getClass().getMethod("getCurrentHp");
			Object currentHp = getCurrentHpMethod.invoke(zombieObject);
			Method maxHpMethod = zombieObject.getClass().getMethod("getMaxHp");
			Object maxHp = maxHpMethod.invoke(zombieObject);
			assertEquals("You should set the HP of the target properly.", maxHp, currentHp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void testInvalidUseSpecialNoAvailableResources() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap1();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 0);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));
		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("useSpecial");
			m3.invoke(character);
			fail("Trying to use a special action with no available resources, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Hero class should have useSpecial method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(noAvailableResourcesExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to use a special action with no available resources, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be a notEnoughActionsException class");
			}
		}
	}

	@Test(timeout = 3000)
	public void testValidUseSpecialUpdateSupplyInventory1() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap1();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		try {
			Method getSupplyInventory = character.getClass().getMethod("getSupplyInventory");
			ArrayList<Supply> inventory1 = (ArrayList<Supply>) getSupplyInventory.invoke(character);
			inventory1.add(new Supply());
			Method m3 = Class.forName(heroPath).getDeclaredMethod("useSpecial");
			m3.invoke(character);

			ArrayList<Supply> inventory2 = (ArrayList<Supply>) getSupplyInventory.invoke(character);

			assertTrue(
					"The used supply should be removed from the hero's inventory. Expected "
							+ "empty arraylist but the arraylist size was " + inventory2.size(),
					0 == inventory2.size());
		} catch (NoSuchMethodException e) {
			fail("Hero class should have useSpecial method");
		}
	}

	@Test(timeout = 3000)
	public void testValidUseSpecialSetSpecialAction1() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5) + 100;
		generateMap1();
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Method setLocation = character.getClass().getMethod("setLocation", Point.class);
		setLocation.invoke(character, new Point(4, 4));
		Constructor<?> constructor2 = Class.forName(zombiePath).getConstructor();
		Object character2 = constructor2.newInstance();
		Game.zombies = new ArrayList<>();
		Game.zombies.add((Zombie) character2);
		Method setTarget = character.getClass().getMethod("setTarget", Character.class);
		setTarget.invoke(character, (Character) character2);
		setLocation.invoke(character2, new Point(3, 4));
		Method setActions = character.getClass().getMethod("setActionsAvailable", int.class);
		setActions.invoke(character, 51);
		Cell[][] m = engine.Game.map;
		m[3][4] = new CharacterCell(
				(Character) constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero));

		Method getSupplyInventory = character.getClass().getMethod("getSupplyInventory");
		ArrayList<Supply> inventory1 = (ArrayList<Supply>) getSupplyInventory.invoke(character);
		inventory1.add(new Supply());

		try {
			Method setSpecialAction = character.getClass().getMethod("setSpecialAction", boolean.class);
			setSpecialAction.invoke(character, false);
		} catch (NoSuchMethodException e) {
			fail("Hero class should have setSpecialAction method.");
		}

		try {
			Method m3 = Class.forName(heroPath).getDeclaredMethod("useSpecial");
			m3.invoke(character);
		} catch (NoSuchMethodException e) {
			fail("Hero class should have useSpecial method.");
		}
		try {
			Method isSpecialActionM = character.getClass().getMethod("isSpecialAction");
			Boolean isSpecialAction = (Boolean) isSpecialActionM.invoke(character);

			assertTrue("The boolean specialAction should be set to true. Expected true but was false",
					true == isSpecialAction);
		} catch (NoSuchMethodException e) {
			fail("Hero class should have isSpecialAction method.");
		}
	}

	public ArrayList<Object> setEnvironment() {
		try {
			Class<?> classGame = Class.forName(gamePath);
			int random = (int) (Math.random() * 1000);
			String nameHero = "Fighter " + random;
			int maxHpHero = (int) (Math.random() * 100);
			int attackDmgHero = (int) (Math.random() * 100);
			int maxActionsHero = (int) (Math.random() * 5);
			Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
					int.class);
			Object fighter = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
			Constructor<?> constructorCharacterCell = Class.forName(charCell)
					.getConstructor(Class.forName(characterPath));
			Object charCell = constructorCharacterCell.newInstance(fighter);

			Constructor<?> constructorZombie = Class.forName(zombiePath).getConstructor();
			Object zombieObject = constructorZombie.newInstance();

			ArrayList<Object> medics = new ArrayList<Object>();
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("ABC", 1000, 50, 20);
			Object createdMedics2 = medicsConstructor.newInstance("123", 5000, 500, 100);
			Object createdMedics3 = medicsConstructor.newInstance("XYZ", 50, 5, 1);
			medics.add(createdMedics1);
			medics.add(createdMedics2);
			medics.add(createdMedics3);

			Field availableHeros = classGame.getDeclaredField("availableHeroes");
			availableHeros.setAccessible(true);
			availableHeros.set((ArrayList<Object>) availableHeros.get(null), new ArrayList<>());
			availableHeros.set((ArrayList<Object>) availableHeros.get(null), medics);
			((Zombie) zombieObject).setLocation(new Point(1, 0));
			for (Hero h : Game.availableHeroes) {
				h.setTarget((Zombie) zombieObject);
			}
			Field gameMap = classGame.getDeclaredField("map");
			gameMap.setAccessible(true);
			Cell[][] map = new Cell[5][6];
			map[0][5] = (Cell) charCell;
			gameMap.set(gameMap.get(null), map);

			ArrayList<Object> zombiesArray = new ArrayList<Object>();
			zombiesArray.add(zombieObject);
			Field zombies = classGame.getDeclaredField("zombies");
			zombies.setAccessible(true);
			zombies.set(zombies.get(null), zombiesArray);

			ArrayList<Object> returns = new ArrayList<Object>();
			returns.add(medics.get(0));
			returns.add(zombieObject);
			returns.add(charCell);
			return returns;
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void generateMap1() {
		Constructor<?> gameConstructor;
		Field f = null;
		try {
			gameConstructor = Class.forName(gamePath).getConstructor();
			Object createdGame = gameConstructor.newInstance();
			Class<? extends Object> curr = createdGame.getClass();
			f = curr.getDeclaredField("map");
			f.setAccessible(true);
			Cell[][] map = new Cell[15][15];
			int random = (int) (Math.random() * 1000);
			String nameHero = "Fighter " + random;
			int maxHpHero = (int) (Math.random() * 100);
			int attackDmgHero = (int) (Math.random() * 100);
			int maxActionsHero = (int) (Math.random() * 5);
			Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
					int.class);
			Object fighter = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
			Constructor<?> constructorCharacterCell = Class.forName(charCell)
					.getConstructor(Class.forName(characterPath));
			constructorCharacterCell.newInstance(fighter);

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = (Cell) constructorCharacterCell.newInstance(fighter);
				}
			}

			f.set(createdGame, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private boolean checkIfMapIsVisible() {
		for (int i = 0; i < Game.map.length; i++) {
			for (int j = 0; j < Game.map[i].length; j++) {
				if (!Game.map[i][j].isVisible()) {
					return false;
				}
			}
		}
		return true;
	}

	private void testClassImplementsInterface(Class<?> aClass, Class<?> iClass) {
		assertTrue("Class \"" + aClass.getSimpleName() + "\" should implement \"" + iClass.getSimpleName()
				+ "\" interface.", iClass.isAssignableFrom(aClass));
	}

	private void testIsInterface(Class<?> aClass) {
		assertTrue("\"" + aClass.getSimpleName() + "\" is an interface.", Modifier.isInterface(aClass.getModifiers()));
	}

	private void testMethodExistsInClass(Class aClass, String methodName, boolean implementedMethod, Class returnType,
			Class... inputTypes) {
		Method[] methods = aClass.getDeclaredMethods();
		if (implementedMethod) {
			assertTrue("The " + methodName + " method in class " + aClass.getSimpleName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The " + methodName + " method in class " + aClass.getSimpleName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}
		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(
					aClass.getSimpleName() + " class should have " + methodName + " method that takes no parameters.",
					found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes " + inputsList
					+ " parameter(s).", found);
		}
		assertTrue("incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(returnType));
	}

	private Object createFighter() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);

		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 4) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		return fighterConstructor.newInstance(name, maxHp, dmg, actions);

	}

	private Object createMedic() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 2) + 10);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		return medicsConstructor.newInstance(name, maxHp, dmg, actions);

	}

	private Object createExplorer() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
				int.class, int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 5) + 5);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";
		return explorerConstructor.newInstance(name, maxHp, dmg, actions);

	}

	private Object createZombie() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> zombieConstructor = Class.forName(zombiePath).getConstructor();
		return zombieConstructor.newInstance();
	}

	private Object createVaccine() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> vaccineConstructor = Class.forName(vaccinePath).getConstructor();
		return vaccineConstructor.newInstance();
	}

	private Object createSupply() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> supplyConstructor = Class.forName(supplyPath).getConstructor();
		return supplyConstructor.newInstance();
	}

	private void resetGameStatics() throws Exception {
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Constructor<?> charCellConst = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> zombieConst = Class.forName(zombiePath).getConstructor();

		Field fd2 = Class.forName(charCell).getDeclaredField("character");
		fd2.setAccessible(true);

		Object[][] map = ((Object[][]) fd.get(null));

		Method setChar = Class.forName(charCell).getMethod("setCharacter", Class.forName(characterPath));
		Object o = null;
		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = charCellConst.newInstance(o);
				fd.set(map[i][j], false);

			}
		}

		fd2 = Class.forName(zombiePath).getDeclaredField("ZOMBIES_COUNT");
		fd2.setAccessible(true);
		fd2.set((int) fd2.get(null), 11);
		loadChampions();

	}

	private void loadChampions()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException, NoSuchFieldException {

		PrintWriter csvWriter = new PrintWriter("test_heros.csv");

		ArrayList<Object> heros = new ArrayList<Object>();
		Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);

		for (int i = 0; i < 7; i++) {
			int maxHp = ((int) (Math.random() * 100) + 10);
			int dmg = ((int) (Math.random() * 200) + 10);
			int actions = ((int) (Math.random() * 5) + 5);
			String name = "Hero_" + i;

			String type = "";
			if (i == 0 || i == 4) {
				type = "FIGH";
				Object createdHero = fighterConstructor.newInstance(name, maxHp, dmg, actions);
				heros.add(createdHero);

			} else {
				if (i % 2 == 0) {
					type = "MED";
					Object createdHero = medicsConstructor.newInstance(name, maxHp, dmg, actions);
					heros.add(createdHero);

				} else {
					type = "EXP";
					Object createdHero = explorerConstructor.newInstance(name, maxHp, dmg, actions);
					heros.add(createdHero);

				}
			}
			String line = name + "," + type + "," + maxHp + "," + actions + "," + dmg;
			csvWriter.println(line);
		}

		csvWriter.flush();
		csvWriter.close();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

		Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
		m.invoke(null, "test_heros.csv");

	}

	private boolean checkRandomPlaces2(String className)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		boolean check = true;

		int countTrue = 0;

		ArrayList<String> allStrings = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			allStrings.add(helperBoard(className));
		}
		for (int i = 0; i < allStrings.size(); i++) {
			String s1 = allStrings.get(i);
			for (int j = 0; j < allStrings.size(); j++) {
				String s2 = allStrings.get(j);
				if (arePermutation(s1, s2))
					countTrue++;

			}
		}
		if (countTrue / 670 > 0.4)
			check = false;
		return check;
	}

	private boolean checkRandomPlaces(String className) throws Exception {
		ArrayList<String> allStrings = new ArrayList<>();
		for (int l = 0; l < 10; l++) {
			resetGameStatics();
			Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));

			m.invoke(null, createFighter());
			Field fd2 = Class.forName(gamePath).getDeclaredField("map");
			fd2.setAccessible(true);

			Object[][] map = (Object[][]) fd2.get(null);
			String s = "";

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(charCell))
							&& className.equals(zombiePath)) {
						Method m2 = Class.forName(charCell).getMethod("getCharacter");
						if (((m2.invoke(map[i][j]) != null))
								&& (m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))) {
							s += i + "" + j;
						}
					} else {
						if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(trapCellPath))
								&& className.equals(trapCellPath)) {
							if (((map[i][j] != null)) && (map[i][j].getClass().equals(Class.forName(className)))) {
								s += i + "" + j;
							}
						} else {
							if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(collectibleCellPath))) {
								Method m2 = Class.forName(collectibleCellPath).getMethod("getCollectible");
								if (((m2.invoke(map[i][j]) != null))
										&& (m2.invoke(map[i][j]).getClass().equals(Class.forName(className)))) {
									s += i + "" + j;
								}
							}
						}
					}

				}
			}

			allStrings.add(s);
		}
		String s1 = "", s2 = "";
		int count = 0;
		for (int i = 0; i < allStrings.size(); i++) {
			s1 = allStrings.get(i);

			for (int j = 0; j < allStrings.size(); j++) {
				if (i != j) {
					s2 = allStrings.get(j);
					if (findSimilarity(s1, s2) > 0.5)
						count++;

				}

			}
		}

		return count < 10;
	}

	private String helperBoard(String className)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));

		m.invoke(null, createFighter());

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		String s = "";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (((map[i][j] != null)) && (map[i][j].getClass().equals(Class.forName(className)))) {
					s += i + "" + j;
				}

			}
		}
		return s;
	}

	private static boolean arePermutation(String str1, String str2) {

		int n1 = str1.length();
		int n2 = str2.length();

		if (n1 != n2)
			return false;
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();

		Arrays.sort(ch1);
		Arrays.sort(ch2);

		for (int i = 0; i < n1; i++)
			if (ch1[i] != ch2[i])
				return false;

		return true;
	}

	private static int getLevenshteinDistance(String X, String Y) {
		int m = X.length();
		int n = Y.length();

		int[][] T = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			T[i][0] = i;
		}
		for (int j = 1; j <= n; j++) {
			T[0][j] = j;
		}

		int cost;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : 1;
				T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1), T[i - 1][j - 1] + cost);
			}
		}

		return T[m][n];
	}

	private static double findSimilarity(String x, String y) {
		if (x == null || y == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		double maxLength = Double.max(x.length(), y.length());
		if (maxLength > 0) {
			return (maxLength - getLevenshteinDistance(x, y)) / maxLength;
		}
		return 1.0;
	}

	private void placeRandomObjectOnMap(Constructor<?> cellConstructor, Object obj)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException, SecurityException, ClassNotFoundException, NoSuchMethodException {

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		Method getChar = Class.forName(charCell).getMethod("getCharacter");
		int x, y;
		do {
			x = ((int) (Math.random() * map.length));
			y = ((int) (Math.random() * map[x].length));
		} while (map[x][y] != null
				&& (((map[x][y].getClass().equals(Class.forName(charCell)) && getChar.invoke(map[x][y]) != null))
						|| (map[x][y].getClass().equals(Class.forName(collectibleCellPath)))
						|| (map[x][y].getClass().equals(Class.forName(trapCellPath)))));

		map[x][y] = cellConstructor.newInstance(obj);
		if (obj.getClass().equals(Class.forName(explorerPath)) || obj.getClass().equals(Class.forName(fighterPath))
				|| obj.getClass().equals(Class.forName(medicPath))
				|| obj.getClass().equals(Class.forName(zombiePath))) {
			fd = Class.forName(characterPath).getDeclaredField("location");
			fd.setAccessible(true);
			fd.set(obj, new Point(x, y));
		}
	}

	@Test(timeout = 10000)
	public void testMapLengthGameStartGame() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));

		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		assertTrue("The Game map dimension is incorrect", map.length == 15 && map[0].length == 15);
	}

	@Test(timeout = 10000)
	public void testStartGame10Zombies2() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));

		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int countZombies = 0;
		Method m2 = Class.forName(charCell).getMethod("getCharacter");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(charCell))) {
					if (m2.invoke(map[i][j]) != null
							&& m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))
						countZombies++;
				}
			}
		}
		assertTrue("The game map should be initalized with the correct number of zombies", countZombies > 0);
	}

	@Test(timeout = 10000)
	public void testStartGameZombiesInArrayList() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> actualZombies = (ArrayList<Object>) fd.get(null);
		assertTrue("The zombies should be added to the zombie ArrayList", actualZombies.size() == 10);
	}


	@Test(timeout = 100000)
	public void testStartGameVaccinesRandomlyPlaced() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		m.invoke(null, fighter);

		assertTrue("Vaccines should be randomly placed on the map", checkRandomPlaces(vaccinePath));
	}

	@Test(timeout = 100000)
	public void testStartGameSuppliesRandomlyPlaced() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		m.invoke(null, fighter);

		assertTrue("Supplies should be randomly placed on the map", checkRandomPlaces(supplyPath));
	}

	@Test(timeout = 10000)
	public void testZombiesLocationSet() throws Exception {
		resetGameStatics();
		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> actualZombies = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);

		Method m2 = Class.forName(charCell).getMethod("getCharacter");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(charCell))
						&& (m2.invoke(map[i][j]) != null
								&& m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))) {
					Object z = m2.invoke(map[i][j]);
					Method m3 = Class.forName(zombiePath).getMethod("getLocation");
					Point loc = (Point) m3.invoke(z);
					assertFalse("zombies' locations should be set when starting the game", loc == null);
					assertTrue("Zombies' locations should be set correctly when starting the game",
							loc.equals(new Point(i, j)));

				}

			}
		}

	}

	@Test(timeout = 10000)
	public void testStartGameVaccines() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();

		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		Method m2 = Class.forName(collectibleCellPath).getMethod("getCollectible");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(collectibleCellPath))) {
					if (m2.invoke(map[i][j]) != null
							&& m2.invoke(map[i][j]).getClass().equals(Class.forName(vaccinePath)))
						count++;
				}
			}
		}

		assertTrue("The game map should be initalized with the correct number of vaccines", count == 5);
	}

	@Test(timeout = 10000)
	public void testStartGameSupplyies2() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");

		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		Method m2 = Class.forName(collectibleCellPath).getMethod("getCollectible");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(collectibleCellPath))) {
					if (m2.invoke(map[i][j]) != null
							&& m2.invoke(map[i][j]).getClass().equals(Class.forName(supplyPath)))
						count++;
				}
			}
		}
		assertFalse("The game map should be initalized with the correct number of vaccines", count != 5);
	}

	@Test(timeout = 10000)
	public void testStartGameHeroAtCorrectLocation() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Method m2 = Class.forName(charCell).getMethod("getCharacter");
		Object o = m2.invoke(((Object[][]) fd.get(null))[0][0]);
		try {

			assertTrue("The hero should be placed correctly on the map", o.equals(fighter));
		} catch (NullPointerException e) {
			fail("Make sure to intialize the map correctly Null pointer exception occured when searching for the starting hero!");
		}

	}

	@Test(timeout = 10000)
	public void testStartGameHeroAtCorrectLocationInClassHero() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Method m2 = Class.forName(charCell).getMethod("getCharacter");
		Object o = m2.invoke(((Object[][]) fd.get(null))[0][0]);

		m2 = Class.forName(heroPath).getMethod("getLocation");
		Point loc = (Point) m2.invoke(o);
		try {

			assertFalse("The hero location should be updated correctly when starting the game",
					loc.x != 0 || loc.y != 0);
		} catch (NullPointerException e) {
			fail("Hero Location should not be null!");
		}
	}

	@Test(timeout = 10000)
	public void testStartGameHero() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		heros = (ArrayList<Object>) fd.get(null);
		assertTrue("Starting Hero is not present in the heros list", heros.contains(fighter));
	}

	@Test(timeout = 10000)
	public void testStartGameHero2() throws Exception {
		resetGameStatics();
		Object fighter = createFighter();
		Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		heros.add(fighter);

		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		m.invoke(null, fighter);

		fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		heros = (ArrayList<Object>) fd.get(null);
		assertTrue("Starting Hero should  be removed from the availableHeroes list", !heros.contains(fighter));
	}


	@Test(timeout = 10000)
	public void testStartGameEmptyCells() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		m.invoke(null, fighter);

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				assertFalse("All empty cells should be initalized as empty charachter cells not null",
						map[i][j] == null);
				if (map[i][j] != null && (map[i][j].getClass().equals(Class.forName(collectibleCellPath)))
						|| (map[i][j].getClass().equals(Class.forName(trapCellPath)))) {
					count++;
				}
			}
		}
		assertFalse("The map should be initalized with the correct number of collectibles and traps, "
				+ "any other cell should be an empty charachter cell", count > 15);
		;

	}

	@Test(timeout = 10000)
	public void testStartGameHeroCellVisibility() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		m.invoke(null, fighter);

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		assertTrue("Hero cell should be visible when starting the game", (boolean) fd.get(map[0][0]));

	}

	@Test(timeout = 10000)
	public void testStartGameAdjacentCellsVisibility() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		m.invoke(null, fighter);

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		assertTrue("Hero's adjacent cells should be visible when starting the game", (boolean) fd.get(map[0][1]));

	}

	@Test(timeout = 10000)
	public void testStartGameAdjacentCellsVisibility3() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		m.invoke(null, fighter);

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		assertTrue("Hero's adjacent cells should be visible when starting the game", (boolean) fd.get(map[1][1]));

	}

	@Test(timeout = 10000)
	public void testStartGameCellsVisibility() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		Object fighter = createFighter();
		m.invoke(null, fighter);

		Field fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Object[][] map = (Object[][]) fd.get(null);
		int count = 0;
		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == 0 && j == 0)
					continue;
				if (!(i == 0 && j == 1) && !(i == 1 && j == 0) && !(i == 1 && j == 1))
					assertFalse(
							"Other than the hero's cell and their adjacent cells, all other cells should NOT be visible when starting the game",
							(boolean) fd.get(map[i][j]));
			}
		}

	}

	@Test(timeout = 10000)
	public void testStartGameWin() throws Exception {
		resetGameStatics();
		Object fighter = createFighter();

		Method m = Class.forName(gamePath).getMethod("startGame", Class.forName(heroPath));
		m.invoke(null, fighter);
		m = Class.forName(gamePath).getMethod("checkWin");

		assertTrue("You should not win when starting the game!", !(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testWinGame() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		heros.add(h1);
		Object h2 = createFighter();
		heros.add(h2);
		Object h3 = createMedic();
		heros.add(h3);
		Object h4 = createMedic();
		heros.add(h4);
		Object h5 = createMedic();
		heros.add(h5);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		((Object[][]) fd.get(null))[0][0] = charCellConstructor.newInstance(h1);
		((Object[][]) fd.get(null))[0][1] = charCellConstructor.newInstance(h2);
		((Object[][]) fd.get(null))[0][2] = charCellConstructor.newInstance(h3);
		((Object[][]) fd.get(null))[0][3] = charCellConstructor.newInstance(h4);
		((Object[][]) fd.get(null))[0][4] = charCellConstructor.newInstance(h5);

		Method m = Class.forName(gamePath).getMethod("checkWin");
		assertTrue("Player should win when all conditions are satisfied!", (boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testWinGame2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		heros.add(h1);
		Object h2 = createFighter();
		heros.add(h2);
		Object h3 = createMedic();
		heros.add(h3);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);

		Method m = Class.forName(gamePath).getMethod("checkWin");
		assertTrue("Player can not win with less than 5 heros alive", !(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testWinGameVaccines() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		heros.add(h1);
		Object h2 = createFighter();
		heros.add(h2);
		Object h3 = createMedic();
		heros.add(h3);
		Object h4 = createMedic();
		heros.add(h4);
		Object h5 = createMedic();
		heros.add(h5);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> collectibleCellConstructor = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);
		placeRandomObjectOnMap(charCellConstructor, h4);
		placeRandomObjectOnMap(charCellConstructor, h5);
		placeRandomObjectOnMap(collectibleCellConstructor, createVaccine());

		Method m = Class.forName(gamePath).getMethod("checkWin");
		assertTrue("Player should not win when still remains uncollected vaccines", !(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testWinGameUnusedVaccines() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		heros.add(h1);

		Field fd2 = Class.forName(heroPath).getDeclaredField("vaccineInventory");
		fd2.setAccessible(true);
		ArrayList<Object> vaccines = (ArrayList<Object>) fd2.get(h1);
		vaccines.add(createVaccine());

		Object h2 = createFighter();
		heros.add(h2);
		Object h3 = createMedic();
		heros.add(h3);
		Object h4 = createMedic();
		heros.add(h4);
		Object h5 = createMedic();
		heros.add(h5);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);
		placeRandomObjectOnMap(charCellConstructor, h4);
		placeRandomObjectOnMap(charCellConstructor, h5);

		Method m = Class.forName(gamePath).getMethod("checkWin");
		assertTrue("Player should not win when they still have unused vaccines", !(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOver() throws Exception {
		resetGameStatics();

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertTrue("The game should end when all the heros are defeated", (boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOverRemainingHerosAndZombies() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		Object h2 = createFighter();
		Object h3 = createMedic();
		heros.add(h1);
		heros.add(h2);
		heros.add(h3);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);

		placeRandomObjectOnMap(charCellConstructor, createZombie());

		fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
		zombies.add(createZombie());

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertTrue("The game should end when collecting and using all the vaccines but still uncured zombies",
				(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOverRemainingVaccines() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		Object h2 = createFighter();
		Object h3 = createMedic();
		heros.add(h1);
		heros.add(h2);
		heros.add(h3);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> collectibleCellConstructor = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);
		placeRandomObjectOnMap(collectibleCellConstructor, createVaccine());

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertFalse("The game should not end with vaccines left to collect", (boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOverRemainingSupplies() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		Object h2 = createFighter();
		Object h3 = createMedic();
		heros.add(h1);
		heros.add(h2);
		heros.add(h3);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> collectibleCellConstructor = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);
		placeRandomObjectOnMap(collectibleCellConstructor, createSupply());

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertTrue(
				"The game should end when all vaccines are collected and used even if there still exist uncollected supplies",
				(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOverUnusedVaccines() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		Object h2 = createFighter();
		Object h3 = createMedic();
		heros.add(h1);
		heros.add(h2);
		heros.add(h3);

		Field fd2 = Class.forName(heroPath).getDeclaredField("vaccineInventory");
		fd2.setAccessible(true);
		ArrayList<Object> vaccines = (ArrayList<Object>) fd2.get(h2);
		vaccines.add(createVaccine());

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> collectibleCellConstructor = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertTrue("The game should not end with unused vaccines left with any hero", !(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testGameOverNoMoreAvailableHeros() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Object h1 = createFighter();
		Object h2 = createFighter();
		Object h3 = createMedic();
		heros.add(h1);
		heros.add(h2);
		heros.add(h3);

		fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
		fd.setAccessible(true);
		fd.set(null, new ArrayList<>());
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, h2);
		placeRandomObjectOnMap(charCellConstructor, h3);

		Method m = Class.forName(gamePath).getMethod("checkGameOver");
		assertTrue("The game should end when you cant have more available heros to add and no vaccines to use",
				(boolean) m.invoke(null));
	}

	@Test(timeout = 10000)
	public void testEndTurnResetTarget() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Object h1 = createMedic();
		Object z2 = createZombie();

		heros.add(h1);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(h1, z2);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		placeRandomObjectOnMap(charCellConstructor, h1);
		placeRandomObjectOnMap(charCellConstructor, z2);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		assertTrue("After the player ends their turn all heros' targets should be reset", fd.get(h1) == null);
	}

	@Test(timeout = 10000)
	public void testEndTurnResetSpecialAction() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Object h1 = createMedic();
		heros.add(h1);

		fd = Class.forName(heroPath).getDeclaredField("specialAction");
		fd.setAccessible(true);
		fd.set(h1, true);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		placeRandomObjectOnMap(charCellConstructor, h1);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(heroPath).getDeclaredField("specialAction");
		fd.setAccessible(true);
		assertTrue("After the player ends their turn all heros' targets should be reset",
				(boolean) fd.get(h1) == false);
	}

	@Test(timeout = 10000)
	public void testEndTurnCellVisibilityCorner() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);

		Object h1 = createMedic();
		heros.add(h1);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		int x = 0;
		int y = 0;

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(x, y));

		map[x][y] = charCellConstructor.newInstance(h1);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ((i <= x + 1 && j <= y + 1) && (i >= x - 1 && j >= y - 1)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {

					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnCellVisibilityCorner2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);

		Object h1 = createMedic();
		heros.add(h1);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		int x = 0;
		int y = 13;

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(x, y));

		map[x][y] = charCellConstructor.newInstance(h1);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ((i <= x + 1 && j <= y + 1) && (i >= x - 1 && j >= y - 1)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {

					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnCellVisibilityCorner3() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);

		Object h1 = createMedic();
		heros.add(h1);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		int x = 13;
		int y = 0;

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(x, y));

		map[x][y] = charCellConstructor.newInstance(h1);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ((i <= x + 1 && j <= y + 1) && (i >= x - 1 && j >= y - 1)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {

					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnCellVisibilityCorner4() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);

		Object h1 = createMedic();
		heros.add(h1);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		int x = 13;
		int y = 14;

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(x, y));

		map[x][y] = charCellConstructor.newInstance(h1);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ((i <= x + 1 && j <= y + 1) && (i >= x - 1 && j >= y - 1)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {

					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnResetActionsMany() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);

		ArrayList<Object> usedHeros = new ArrayList<>();
		ArrayList<Integer> maxActionsList = new ArrayList<>();
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		for (int i = 0; i < 5; i++) {

			int maxHp = ((int) (Math.random() * 100) + 10);
			int dmg = ((int) (Math.random() * 200) + 10);
			int maxActions = ((int) (Math.random() * 10) + 10);
			String name = "Hero_";
			int newActions = ((int) (Math.random() * 5) + 5);

			Object h1 = fighterConstructor.newInstance(name, maxHp, dmg, maxActions);

			placeRandomObjectOnMap(charCellConstructor, h1);

			heros.add(h1);
			usedHeros.add(h1);

			fd = Class.forName(heroPath).getDeclaredField("actionsAvailable");
			fd.setAccessible(true);
			fd.set(h1, newActions);
			maxActionsList.add(maxActions);

		}

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(heroPath).getDeclaredField("actionsAvailable");
		fd.setAccessible(true);
		for (int i = 0; i < usedHeros.size(); i++) {

			assertTrue("After the player ends their turn all hero's actions should be reset",
					(int) fd.get(usedHeros.get(i)) == maxActionsList.get(i));
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnResetSpecialActionMany() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		ArrayList<Object> herosUsed = new ArrayList<>();

		for (int i = 0; i < 5; i++) {

			Object h1 = createMedic();
			heros.add(h1);

			fd = Class.forName(heroPath).getDeclaredField("specialAction");
			fd.setAccessible(true);
			fd.set(h1, true);
			placeRandomObjectOnMap(charCellConstructor, h1);
			herosUsed.add(h1);
		}

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(heroPath).getDeclaredField("specialAction");
		fd.setAccessible(true);

		for (Object hero : herosUsed) {

			assertTrue("After the player ends their turn all heros' targets should be reset",
					(boolean) fd.get(hero) == false);
		}
	}

	@Test(timeout = 100000)
	public void testEndTurnCellVisibilityMany2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		ArrayList<Object> herosToUse = new ArrayList<>();
		ArrayList<Point> visibleLocations = new ArrayList<>();
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(5, 4));
		points.add(new Point(5, 5));
		points.add(new Point(5, 6));
		points.add(new Point(6, 6));
		for (int i = 0; i < 4; i++) {

			Object h1 = createMedic();
			heros.add(h1);

			herosToUse.add(h1);

			fd = Class.forName(characterPath).getDeclaredField("location");
			fd.setAccessible(true);
			Point p = points.get(i);
			fd.set(h1, p);
			map[p.x][p.y] = charCellConstructor.newInstance(h1);

			visibleLocations.add(p);
			for (int j = p.x - 1; j <= p.x + 1; j++) {
				for (int j2 = p.y - 1; j2 <= p.y + 1; j2++) {

					visibleLocations.add(new Point(j, j2));
				}
			}
		}

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Point p = new Point(i, j);
				if (visibleLocations.contains(p)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {
					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 100000)
	public void testEndTurnCellVisibilityMany3() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);
		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = (Object[][]) fd.get(null);
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		ArrayList<Object> herosToUse = new ArrayList<>();
		ArrayList<Point> visibleLocations = new ArrayList<>();
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(13, 14));
		points.add(new Point(0, 14));
		points.add(new Point(0, 6));
		points.add(new Point(14, 10));
		for (int i = 0; i < 4; i++) {

			Object h1 = createMedic();
			heros.add(h1);

			herosToUse.add(h1);

			fd = Class.forName(characterPath).getDeclaredField("location");
			fd.setAccessible(true);
			Point p = points.get(i);
			fd.set(h1, p);
			map[p.x][p.y] = charCellConstructor.newInstance(h1);

			visibleLocations.add(p);
			for (int j = p.x - 1; j <= p.x + 1; j++) {
				for (int j2 = p.y - 1; j2 <= p.y + 1; j2++) {

					visibleLocations.add(new Point(j, j2));
				}
			}
		}

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(cellPath).getDeclaredField("isVisible");
		fd.setAccessible(true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Point p = new Point(i, j);
				if (visibleLocations.contains(p)) {

					assertTrue(
							"End turn should update the cells' visibility, heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));
				} else {
					assertFalse(
							"End turn should update the cells' visibility, ONLY heros' cells and their adjacent cells should be visible",
							(boolean) fd.get(map[i][j]));

				}
			}
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnResetZombieTargetMany() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		ArrayList<Object> zombiesToUse = new ArrayList<>();
		for (int i = 0; i < 5; i++) {

			Object h1 = createMedic();
			placeRandomObjectOnMap(charCellConstructor, h1);

			Object z2 = createZombie();
			zombies.add(z2);
			zombiesToUse.add(z2);
			placeRandomObjectOnMap(charCellConstructor, z2);

			fd = Class.forName(characterPath).getDeclaredField("target");
			fd.setAccessible(true);
			fd.set(z2, h1);
		}

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {
			m.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
				fail(e.getClass() + " Occured but shouldnt end turn");
			if (e.getClass().equals(Class.forName(notEnoughActionsExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");
			if (e.getClass().equals(Class.forName(noAvailableResourcesExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");

			fail("Null pointer exception occured make sure to handle null targets scenario in end turn");
		}
		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		for (Object z : zombiesToUse) {

			assertTrue("After the player ends their turn all zombies' targets should be reset", fd.get(z) == null);
		}
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieWithNoTargetHealth() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));

		Object z2 = createZombie();
		zombies.add(z2);
		placeRandomObjectOnMap(charCellConstructor, z2);

		Method m = Class.forName(gamePath).getMethod("endTurn");
		try {
			m.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
				fail(e.getClass() + " Occured but shouldnt end turn");
			if (e.getClass().equals(Class.forName(notEnoughActionsExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");
			if (e.getClass().equals(Class.forName(noAvailableResourcesExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");

			fail("Null pointer exception occured make sure to handle null targets scenario in end turn");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertTrue("After the player ends their turn all zombies' targets should be reset", (int) fd.get(z2) == 40);
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackLeft() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 5) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		for (int i = 3; i <= 6; i++) {
			for (int j = 3; j <= 6; j++) {
				map[i][j] = new CharacterCell(null);
			}
		}

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[5][4] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(4, 5));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertEquals("Zombies' HP should be updated after they attack during ending the turn", (int) (40 - dmg / 2),
				(int) fd.get(z2));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackRight2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[5][6] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(4, 5));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertEquals("Zombies should attack any valid target to their right during ending the turn", (int) (maxHp - 10),
				(int) fd.get(h1));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackUp2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[6][5] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(6, 5));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertEquals("Zombies should attack any valid target above them during ending the turn", (int) (maxHp - 10),
				(int) fd.get(h1));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackDown2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[4][5] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(4, 5));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertEquals("Zombies should attack any valid target below them during ending the turn", (int) (maxHp - 10),
				(int) fd.get(h1));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackOnlyOnce2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);

		ArrayList<Object> herosInRange = new ArrayList<>();
		ArrayList<Integer> maxHpInRange = new ArrayList<>();

		for (int i = 0; i < 3; i++) {

			int maxHp = ((int) (Math.random() * 100) + 10);
			maxHpInRange.add(maxHp);
			int dmg = ((int) (Math.random() * 10) + 3);
			int actions = ((int) (Math.random() * 5) + 5);
			String name = "Hero_";

			Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);
			heros.add(h1);
			herosInRange.add(h1);

		}
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[4][5] = charCellConstructor.newInstance(herosInRange.get(0));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(0), new Point(4, 5));

		map[6][5] = charCellConstructor.newInstance(herosInRange.get(1));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(1), new Point(6, 5));

		map[5][4] = charCellConstructor.newInstance(herosInRange.get(2));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(2), new Point(5, 4));

		boolean oneAttacked = false;

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {
			m.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
				fail(e.getClass() + " Occured but shouldnt end turn");
			if (e.getClass().equals(Class.forName(notEnoughActionsExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");
			if (e.getClass().equals(Class.forName(noAvailableResourcesExceptionPath)))
				fail(e.getClass() + " occured but shouldnt in end turn");

			fail("Null pointer exception occured make sure to handle null targets scenario in end turn");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);

		for (int i = 0; i < herosInRange.size(); i++) {
			if (oneAttacked && (int) fd.get(herosInRange.get(i)) < maxHpInRange.get(i)) {
				fail("Zombies should only attack ONE hero from the adjacent cells");
			}
			if (!oneAttacked && (int) fd.get(herosInRange.get(i)) < maxHpInRange.get(i)) {
				assertEquals("Zombies should attack one valid target in range during ending the turn",
						(int) (maxHpInRange.get(i) - 10), (int) fd.get(herosInRange.get(i)));
				oneAttacked = true;
			}

		}
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieAttackOutOfRange() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		ArrayList<Object> herosInRange = new ArrayList<>();

		for (int i = 0; i < 3; i++) {

			int maxHp = ((int) (Math.random() * 100) + 10);
			int dmg = ((int) (Math.random() * 10) + 3);
			int actions = ((int) (Math.random() * 5) + 5);
			String name = "Hero_";

			Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);
			heros.add(h1);
			herosInRange.add(h1);
		}
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[4][5] = charCellConstructor.newInstance(herosInRange.get(0));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(0), new Point(4, 5));

		map[6][5] = charCellConstructor.newInstance(herosInRange.get(1));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(1), new Point(6, 5));

		map[5][4] = charCellConstructor.newInstance(herosInRange.get(2));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(herosInRange.get(2), new Point(5, 4));

		boolean oneAttacked = false;

		int maxHp = ((int) (Math.random() * 100) + 10);
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object heroOutOfRange = medicsConstructor.newInstance(name, maxHp, dmg, actions);
		heros.add(heroOutOfRange);

		map[10][10] = charCellConstructor.newInstance(heroOutOfRange);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(heroOutOfRange, new Point(10, 10));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		try {
			m.invoke(null);

		} catch (Exception e) {
			if (!e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
				e.printStackTrace();

		}
		assertEquals("If a hero is out of range from a zombie they should NOT be affected when ending the turn", maxHp,
				fd.get(heroOutOfRange));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieNotAttackingZombie() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		ArrayList<Object> zombiesInRange = new ArrayList<>();

		zombiesInRange.add(createZombie());
		zombiesInRange.add(createZombie());
		zombiesInRange.add(createZombie());
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[4][5] = charCellConstructor.newInstance(zombiesInRange.get(0));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(zombiesInRange.get(0), new Point(4, 5));

		map[6][5] = charCellConstructor.newInstance(zombiesInRange.get(1));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(zombiesInRange.get(1), new Point(6, 5));

		map[5][4] = charCellConstructor.newInstance(zombiesInRange.get(2));

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(zombiesInRange.get(2), new Point(5, 4));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		try {
			m.invoke(null);

		} catch (Exception e) {
			if (!e.getClass().equals(Class.forName(invalidTargetExceptionPath)))
				e.printStackTrace();

		}
		assertEquals("Zombies should only attack heros during ending the turn, they should never attack zombies!", 40,
				fd.get(zombiesInRange.get(0)));
		assertEquals("Zombies should only attack heros during ending the turn, they should never attack zombies!", 40,
				fd.get(zombiesInRange.get(1)));
		assertEquals("Zombies should only attack heros during ending the turn, they should never attack zombies!", 40,
				fd.get(zombiesInRange.get(2)));
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieKnockHeroDead1() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = 2;
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[5][4] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(5, 4));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		fd = Class.forName(characterPath).getDeclaredField("currentHp");
		fd.setAccessible(true);
		assertEquals("Zombies should attack any valid target to their left during ending the turn", 0,
				(int) fd.get(h1));

		Method m2 = Class.forName(charCell).getMethod("getCharacter");

		assertTrue("Dead heros should be removed from the map", m2.invoke(map[5][4]) == null);
	}

	@Test(timeout = 10000)
	public void testEndTurnZombieKnockHeroDead2() throws Exception {
		resetGameStatics();
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);

		fd = Class.forName(gamePath).getDeclaredField("heroes");
		fd.setAccessible(true);
		ArrayList<Object> heros = (ArrayList<Object>) fd.get(null);

		Constructor<?> charCellConstructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int maxHp = 2;
		int dmg = ((int) (Math.random() * 10) + 3);
		int actions = ((int) (Math.random() * 5) + 5);
		String name = "Hero_";

		Object h1 = medicsConstructor.newInstance(name, maxHp, dmg, actions);

		heros.add(h1);
		Object z2 = createZombie();
		zombies.add(z2);

		fd = Class.forName(characterPath).getDeclaredField("target");
		fd.setAccessible(true);
		fd.set(z2, h1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		map[5][5] = charCellConstructor.newInstance(z2);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(z2, new Point(5, 5));

		map[5][4] = charCellConstructor.newInstance(h1);

		fd = Class.forName(characterPath).getDeclaredField("location");
		fd.setAccessible(true);
		fd.set(h1, new Point(5, 4));

		Method m = Class.forName(gamePath).getMethod("endTurn");

		try {

			m.invoke(null);
		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getCause() + " occured but shouldnt check the console for the error trace");
		}

		assertFalse("Dead heros should be removed from the heros list", heros.contains(h1));

	}

	@Test(timeout = 10000)
	public void testEndTurnAddZombies() throws Exception {
		resetGameStatics();
		Method m = Class.forName(gamePath).getMethod("endTurn");

		m.invoke(null);
		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
		assertTrue(
				"The game should add one new zombie to the arraylist of zombies at the end of each turn as long as the game only contains up 10 zombies",
				zombies.size() > 0);

		assertTrue(
				"The game should add ONLY ONE new zombie to the arraylist of zombies at the end of each turn as long as the game only contains up 10 zombies",
				zombies.size() == 1);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		Method m2 = Class.forName(charCell).getMethod("getCharacter");
		int countZombies = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(charCell))) {
					if (m2.invoke(map[i][j]) != null
							&& m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))
						countZombies++;
				}
			}
		}
		assertEquals(
				"The game should add one new zombie at the end of each turn as long as the game only contains up 10 zombies",
				1, countZombies);

	}

	@Test(timeout = 10000)
	public void testEndTurnAddZombies2() throws Exception {
		resetGameStatics();

		Field fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		ArrayList<Object> zombies = (ArrayList<Object>) fd.get(null);
		Object z = createZombie();
		zombies.add(z);
		Constructor<?> charCellConst = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		placeRandomObjectOnMap(charCellConst, z);

		Method m = Class.forName(gamePath).getMethod("endTurn");

		m.invoke(null);

		fd = Class.forName(gamePath).getDeclaredField("map");
		fd.setAccessible(true);
		Object[][] map = ((Object[][]) fd.get(null));

		Method m2 = Class.forName(charCell).getMethod("getCharacter");
		int countZombies = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null && map[i][j].getClass().equals(Class.forName(charCell))) {
					if (m2.invoke(map[i][j]) != null
							&& m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))
						countZombies++;
				}
			}
		}
		fd = Class.forName(gamePath).getDeclaredField("zombies");
		fd.setAccessible(true);
		zombies = (ArrayList<Object>) fd.get(null);
		assertTrue(
				"The game should add one new zombie to the arraylist of zombies at the end of each turn as long as the game only contains up 10 zombies",
				zombies.size() > 1);

		assertTrue(
				"The game should add ONLY ONE new zombie to the arraylist of zombies at the end of each turn as long as the game only contains up 10 zombies",
				zombies.size() == 2);
		assertEquals(
				"The game should add one new zombie at the end of each turn as long as the game only contains up 10 zombies",
				2, countZombies);

	}

	@Test(timeout = 10000)
	public void testEndTurnAddZombiesRandomlyPlaces() throws Exception {

		ArrayList<String> allStrings = new ArrayList<>();
		for (int l = 0; l < 16; l++) {
			resetGameStatics();
			Method m = Class.forName(gamePath).getMethod("endTurn");

			m.invoke(null);
			Field fd2 = Class.forName(gamePath).getDeclaredField("map");
			fd2.setAccessible(true);

			Object[][] map = (Object[][]) fd2.get(null);
			String s = "";
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					Method m2 = Class.forName(charCell).getMethod("getCharacter");
					if (((m2.invoke(map[i][j]) != null))
							&& (m2.invoke(map[i][j]).getClass().equals(Class.forName(zombiePath)))) {
						s += i + "" + j;
					}

				}
			}

			allStrings.add(s);
		}
		int count = 0;
		String s1 = "", s2 = "";
		for (int i = 0; i < allStrings.size(); i++) {
			if (i < allStrings.size() / 2)
				s1 += allStrings.get(i);
			else
				s2 += allStrings.get(i);
		}

		if (findSimilarity(s1, s2) > 0.5)
			fail("zombies should be randomly added to the game at the end of each turn");

	}

}