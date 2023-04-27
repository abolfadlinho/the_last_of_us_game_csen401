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
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.TrapCell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class M1PrivateTests {
	String characterPath = "model.characters.Character";
	String collectiblePath = "model.collectibles.Collectible";
	String vaccinePath = "model.collectibles.Vaccine";
	String supplyPath = "model.collectibles.Supply";
	String fighterPath = "model.characters.Fighter";

	private String charCell = "model.world.CharacterCell";
	private String cellPath = "model.world.Cell";
	private String collectibleCellPath = "model.world.CollectibleCell";
	private String trapCellPath = "model.world.TrapCell";
	private String enumDirectionPath = "model.characters.Direction";

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

	@Test(timeout = 100)
	public void testSetterForInstanceVariableVaccineInventoryDoesNotExistInClassHero3()
			throws ClassNotFoundException {
		String[] subClasses = { heroPath };
		testSetterAbsentInSubclasses("vaccineInventory", subClasses);
	}

	@Test(timeout = 100)
	public void testSetterForInstanceVariableSupplyInventoryDoesNotExistInClassHero3()
			throws ClassNotFoundException {
		String[] subClasses = { heroPath };
		testSetterAbsentInSubclasses("supplyInventory", subClasses);
	}

	@Test(timeout = 1000)
	public void testConstructorOfFighter() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(Class.forName(fighterPath), inputs);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractCharacter() {
		try {
			testClassIsAbstract(Class.forName(characterPath));
		} catch (ClassNotFoundException e) {
			assertTrue(
					"Package model.character should contain an abstract class called Character.",
					false);
		}
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableAttackDmgPresent()
			throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath),
				"attackDmg", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableAttackDmgPrivate()
			throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "attackDmg");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableAttackDmgType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "attackDmg",
				int.class);
	}

	@Test(timeout = 100)
	public void testCoverLocationGetter() throws Exception {
		testGetterMethodExistsInClass(Class.forName(characterPath),
				"getLocation", Point.class, true);

	}

	@Test(timeout = 100)
	public void testInstanceVariableChampionMaxHPSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(characterPath), "setMaxHp",
				int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPSetterLogicZero()
			throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(
				String.class, int.class, int.class, int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object c = constructor.newInstance("Name_" + randomName, randomMaxHP,
				randomAttackDamage, randomActions);
		testSetterLogic1(c, "currentHp", -1, 0, int.class);
	}

	@Test(timeout = 100)
	public void testSupplyImplementsCollectible() {
		try {
			testClassImplementsInterface(Class.forName(supplyPath),
					Class.forName(collectiblePath));
		} catch (ClassNotFoundException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(),
					false);
		}
	}

	@Test(timeout = 100)
	public void testEmptyConstructorSupply() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(supplyPath), inputs);
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableTrapDamageInClassTrapCell()
			throws Exception {
		testGetterMethodExistsInClass(Class.forName(trapCellPath),
				"getTrapDamage", int.class, true);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCharacterIsNotPresentInClassCell()
			throws Exception {
		testInstanceVariableIsPresent(Class.forName(cellPath), "character",
				false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCollectibleIsNotStaticInClassCollectible()
			throws Exception {
		testVariableIsNotStatic(collectibleCellPath, "collectible");
	}

	@Test(timeout = 1000)
	public void testInvalidConstructorOfClassCell() throws Exception {
		Class[] inputs = { boolean.class };
		testConstructorDoesnotExist(Class.forName(cellPath), inputs);
	}

	@Test(timeout = 800)
	public void testGameAvailableHeroesisStatic() throws NoSuchFieldException,
			SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("availableHeroes");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be static",
				(Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameHeroesisStatic() throws NoSuchFieldException,
			SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("heroes");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be static",
				(Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameAvailableHeroesisPublic() throws NoSuchFieldException,
			SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("availableHeroes");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be public",
				(Modifier.isPublic(modifiers)));
	}

	@Test(timeout = 1000)
	public void testLoadMedsCorrectHP() {
		try {
			writeMedicCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField(
					"availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes",
					String.class);
			m.invoke(null, "test_MEDs.csv");

			ArrayList<Object> medics = new ArrayList<Object>();
			Constructor<?> medicsConstructor = Class.forName(medicPath)
					.getConstructor(String.class, int.class, int.class,
							int.class);
			Object createdMedics1 = medicsConstructor.newInstance("average",
					1000, 50, 20);
			Object createdMedics2 = medicsConstructor.newInstance(
					"MedGamedGedan", 5000, 500, 100);
			Object createdMedics3 = medicsConstructor.newInstance("mehMed", 50,
					5, 1);

			medics.add(createdMedics1);
			medics.add(createdMedics2);
			medics.add(createdMedics3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd
					.get(null);

			assertTrue(
					"Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == medics.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i)
						.getClass(), Class.forName(medicPath));
				assertTrue(
						"Medic's HP loaded incorrectly",
						checkHeroesHPEqual(actualChampions.get(i),
								medics.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException
				| SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 100)
	public void testEmptyConstructorGameActionException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(gameActionExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassInvalidTargetException() throws Exception {
		testClassIsSubclass(Class.forName(invalidTargetExceptionPath),
				Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 100)
	public void testConstructorMovementException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(movementExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassNoAvailableResourcesException()
			throws Exception {
		testClassIsSubclass(Class.forName(noAvailableResourcesExceptionPath),
				Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 1000)
	public void testCharacterIsSuperClassOfZombie() throws Exception {
		testClassIsSubclass(Class.forName(zombiePath),
				Class.forName(characterPath));
	}

	@Test(timeout = 1000)
	public void testConstructorOfMedic() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(Class.forName(medicPath), inputs);
	}

	@Test(timeout = 800)
	public void testHeroAbstract() throws NoSuchFieldException,
			SecurityException, ClassNotFoundException {
		int modifiers = Class.forName(heroPath).getModifiers();
		assertTrue("Hero class should be abstract",
				(Modifier.isAbstract(modifiers)));
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCharacterExistsInClassTrapCell()
			throws Exception {
		testSetterMethodExistsInClass(Class.forName(trapCellPath),
				"setTrapDamage", int.class, false);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCollectibleExistsInClassCollectibleCell()
			throws Exception {
		testSetterMethodExistsInClass(Class.forName(collectibleCellPath),
				"setCollectible", Class.forName(collectiblePath), false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterAttackDamageSetter()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(characterPath),
				"setAttackDmg", int.class, false);
	}

	@Test(timeout = 100)
	public void testCharacterNameSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(characterPath), "setName",
				String.class, false);

	}

	@Test(timeout = 100)
	public void testSetterForInstanceVariableMaxActionsDoesNotExistInClassHero3()
			throws ClassNotFoundException {
		String[] subClasses = { heroPath };
		testSetterAbsentInSubclasses("maxActions", subClasses);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpecialAction() throws Exception {
		testInstanceVariableIsPresent(Class.forName(heroPath), "specialAction",
				true);
		testInstanceVariableOfType(Class.forName(heroPath), "specialAction",
				boolean.class);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "specialAction");
	}

	@Test(timeout = 100)
	public void testInstanceVariableVaccineInventory() throws Exception {
		testInstanceVariableIsPresent(Class.forName(heroPath),
				"vaccineInventory", true);
		testInstanceVariableOfType(Class.forName(heroPath), "vaccineInventory",
				ArrayList.class);
		testInstanceVariableIsPrivate(Class.forName(heroPath),
				"vaccineInventory");
	}

	@Test(timeout = 100)
	public void testInstanceVariableSupplyInventory() throws Exception {
		testInstanceVariableIsPresent(Class.forName(heroPath),
				"supplyInventory", true);
		testInstanceVariableOfType(Class.forName(heroPath), "supplyInventory",
				ArrayList.class);
		testInstanceVariableIsPrivate(Class.forName(heroPath),
				"supplyInventory");
	}

	@Test(timeout = 100)
	public void testInstanceVariableMaxActions() throws Exception {
		testInstanceVariableIsPresent(Class.forName(heroPath), "maxActions",
				true);
		testInstanceVariableOfType(Class.forName(heroPath), "maxActions",
				int.class);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "maxActions");
	}

	@Test(timeout = 100)
	public void testInstanceVariableActionsAvailable() throws Exception {
		testInstanceVariableIsPresent(Class.forName(heroPath),
				"actionsAvailable", true);
		testInstanceVariableOfType(Class.forName(heroPath), "actionsAvailable",
				int.class);
		testInstanceVariableIsPrivate(Class.forName(heroPath),
				"actionsAvailable");
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationExplorer() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Explorer " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath)
				.getConstructor(String.class, int.class, int.class, int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero,
				attackDmgHero, maxActionsHero);

		String[] names = { "name", "maxHp", "currentHp", "attackDmg",
				"maxActions", "actionsAvailable", "vaccineInventory",
				"supplyInventory", "specialAction" };
		Object[] values = { nameHero, maxHpHero, maxHpHero, attackDmgHero,
				maxActionsHero, maxActionsHero, heroVacine, heroSupply, false };

		testConstructorInitialization(hero, names, values);
	}

	@Test(timeout = 100)
	public void testCollectibleIsInterface() {
		try {
			testIsInterface(Class.forName(collectiblePath));
		} catch (ClassNotFoundException e) {
			assertTrue(
					"Package model.collectibles should contain an interface called Collectible.",
					false);
		}
	}

	@Test(timeout = 100)
	public void testSetterForInstanceVariableSpecialActionInClassHero3()
			throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(heroPath),
				"setSpecialAction", boolean.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testSetterAbsentInSubclasses("specialAction", subClasses);
	}

	private void testSetterLogic1(Object createdObject, String name,
			Object setValue, Object expectedValue, Class type) throws Exception {

		Field f = null;
		Field ff = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName()
						+ " should have the instance variable \"" + name
						+ "\".");
			try {
				f = curr.getDeclaredField(name);
				ff = curr.getDeclaredField("maxHp");
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		ff.setAccessible(true);
		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c)
				+ name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);
		if ((name.equals("currentHp") && createdObject.getClass()
				.getSimpleName().equals("Character"))
				|| name.equals("currentActionPoints")) {
			if ((int) setValue == -1 && (int) expectedValue == 0) {
				assertEquals("The method \"" + methodName + "\" in class "
						+ createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\". It should not be less than zero.",
						expectedValue, f.get(createdObject));
			} else if ((int) setValue > (int) expectedValue) {
				assertEquals("The method \"" + methodName + "\" in class "
						+ createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\". It should not exceed the max value.",
						expectedValue, f.get(createdObject));
			}

		}

		assertEquals(
				"The method \"" + methodName + "\" in class "
						+ createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\".", expectedValue, f.get(createdObject));

	}

	private void testSetterLogic(Object createdObject, String name,
			Object valueIn, Object valueOut, Class type) throws Exception {
		Field f = null;
		Class curr = createdObject.getClass();
		while (f == null) {
			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName()
						+ " should have the instance variable \"" + name
						+ "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}
		}
		f.setAccessible(true);
		String methodName;
		if (valueIn.getClass().getSimpleName().equals("Boolean")
				&& name.substring(0, 2).equals("is")) {
			Character c = name.charAt(2);
			methodName = "set" + name.substring(2, name.length());
		} else {
			Character c = name.charAt(0);
			methodName = "set" + Character.toUpperCase(c)
					+ name.substring(1, name.length());
		}
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, valueIn);
		assertEquals(
				"The method \"" + methodName + "\" in class "
						+ createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\".", valueIn, f.get(createdObject));
	}

	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from "
				+ aClass.getSimpleName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	private void testIsInterface(Class aClass) {
		assertEquals(aClass.getName() + " should be a abstract", true,
				aClass.isInterface());
	}

	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);
			msg = msg.substring(0, msg.length() - 4);
			assertFalse(
					"Missing constructor with " + msg + " parameter"
							+ (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getSimpleName() + " class.", thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getSimpleName() + " class.", thrown);
	}

	private void testConstructorInitialization(Object createdObject,
			String[] names, Object[] values) throws NoSuchMethodException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName()
							+ " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}
			}
			f.setAccessible(true);
			if (currName.equals("currentHp")
					|| currName.equals("currentActionPoints")) {

				assertEquals(
						"The constructor of the "
								+ createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \""
								+ currName
								+ "\" correctly. It should be equals to the max initially.",
						currValue, f.get(createdObject));
			} else {
				assertEquals("The constructor of the "
						+ createdObject.getClass().getSimpleName()
						+ " class should initialize the instance variable \""
						+ currName + "\" correctly.", currValue,
						f.get(createdObject));
			}
		}

	}

	private void testClassIsSubclass(Class subClass, Class superClass) {
		assertEquals(
				subClass.getSimpleName() + " class should be a subclass from "
						+ superClass.getSimpleName() + ".", superClass,
				subClass.getSuperclass());
	}

	private void testClassImplementsInterface(Class aClass, Class iClass) {
		assertTrue("Class \"" + aClass.getSimpleName()
				+ "\" should implement \"" + iClass.getSimpleName()
				+ "\" interface.", iClass.isAssignableFrom(aClass));
	}

	private void testInstanceVariableOfType(Class aClass, String varName,
			Class expectedType) {
		Field f = null;
		boolean b = true;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			b = false;
		}
		if (b) {
			Class varType = f.getType();
			assertEquals("The attribute " + varType.getSimpleName()
					+ " of instance variable: " + varName
					+ " should be of the type " + expectedType.getSimpleName(),
					expectedType, varType);
		} else {
			assertTrue(
					"The instance variable \"" + varName
							+ "\" should be declared in class "
							+ aClass.getSimpleName() + ".", false);
		}

	}

	private void testInstanceVariableOfTypeDoubleArray(Class aClass,
			String varName, Class expectedType) {
		Field f = null;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			return;
		}
		Class varType = f.getType();
		assertEquals("the attribute: " + varType.getSimpleName()
				+ " should be of the type: " + expectedType.getSimpleName(),
				expectedType.getTypeName() + "[][]", varType.getTypeName());
	}

	private void testInstanceVariableIsPresent(Class aClass, String varName,
			boolean implementedVar) throws SecurityException {
		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse("There should be \"" + varName
					+ "\" instance variable in class " + aClass.getSimpleName()
					+ ".", thrown);
		} else {
			assertTrue(
					"The instance variable \"" + varName
							+ "\" should not be declared in class "
							+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		boolean b = 2 == f.getModifiers();
		assertTrue("The \"" + varName + "\" instance variable in class "
				+ aClass.getSimpleName()
				+ " should not be accessed outside that class.", b);
	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName,
			Class returnedType, boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2, 3).toLowerCase()
					+ methodName.substring(3);
		else
			varName = methodName.substring(3, 4).toLowerCase()
					+ methodName.substring(4);
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName
					+ " method in " + aClass.getSimpleName() + " class.", m
					.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is not a READ variable.",
					found);
		}

	}

	private void testGetterLogic(Object createdObject, String name, Object value)
			throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName()
						+ " should have the instance variable \"" + name
						+ "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c)
				+ name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class)
				&& !name.substring(0, 2).equals("is"))
			methodName = "is" + Character.toUpperCase(c)
					+ name.substring(1, name.length());
		else if (value.getClass().equals(Boolean.class)
				&& name.substring(0, 2).equals("is"))
			methodName = "is" + Character.toUpperCase(name.charAt(2))
					+ name.substring(3, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals("The method \"" + methodName + "\" in class "
				+ createdObject.getClass().getSimpleName()
				+ " should return the correct value of variable \"" + name
				+ "\".", value, m.invoke(createdObject));

	}

	private void testGetterLogicCharacterClass1(Object createdObject,
			String name, Object value) throws Exception {

		Field f = null;
		Class curr = Class.forName(characterPath);

		while (f == null) {

			if (curr == Object.class)
				fail("Class Character"
						+ " should have the instance variable \"" + name
						+ "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c)
				+ name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c)
					+ name.substring(1, name.length());

		Method m = Class.forName(characterPath).getMethod(methodName);
		assertEquals(
				"The method \""
						+ methodName
						+ "\" in class Character should return the correct value of variable \""
						+ name + "\".", value, m.invoke(createdObject));

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName,
			Class inputType, boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3, 4).toLowerCase()
				+ methodName.substring(4);
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is a WRITE variable.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is not a WRITE variable.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName
				+ " method that takes one " + inputType.getSimpleName()
				+ " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in "
				+ aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void testSetterAbsentInSubclasses(String varName,
			String[] subclasses) throws SecurityException,
			ClassNotFoundException {
		String methodName = "set" + varName.substring(0, 1).toUpperCase()
				+ varName.substring(1);
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses
					|| containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName
				+ " method should not be implemented in a subclasses.",
				methodIsInSubclasses);
	}

	private boolean checkHeroesNameEqual(Object hero1, Object hero2)
			throws IllegalArgumentException, IllegalAccessException,
			ClassNotFoundException {
		Class curr1 = Class.forName(characterPath);

		Field name1 = null;

		try {
			name1 = curr1.getDeclaredField("name");
			name1.setAccessible(true);

			String n1 = (String) name1.get(hero1);
			String n2 = (String) name1.get(hero2);
			// assertTrue("Hero's name is loaded incorrectly",
			return n1.equals(n2);

		} catch (NoSuchFieldException e) {
			curr1 = curr1.getSuperclass();
			fail("Attributes name error");
			return false;
		}
	}

	private boolean checkHeroesHPEqual(Object hero1, Object hero2)
			throws IllegalArgumentException, IllegalAccessException,
			ClassNotFoundException {
		Class curr1 = Class.forName(characterPath);

		Field maxhp1 = null;

		try {
			maxhp1 = curr1.getDeclaredField("currentHp");
			maxhp1.setAccessible(true);

			int mh1 = (int) maxhp1.get(hero1);
			int mh2 = (int) maxhp1.get(hero2);
			return mh1 == mh2;

		} catch (NoSuchFieldException e) {
			curr1 = curr1.getSuperclass();
			fail("Attributes name error");
			return false;
		}

	}

	private boolean checkHeroesActionEqual(Object hero1, Object hero2)
			throws IllegalArgumentException, IllegalAccessException,
			ClassNotFoundException {
		Class curr1 = Class.forName(characterPath);

		Field maxActions1 = null;

		try {
			maxActions1 = Class.forName(heroPath)
					.getDeclaredField("maxActions");
			maxActions1.setAccessible(true);

			int ma1 = (int) maxActions1.get(hero1);
			int ma2 = (int) maxActions1.get(hero2);
			return ma1 == ma2;

		} catch (NoSuchFieldException e) {
			curr1 = curr1.getSuperclass();
			fail("Attributes name error");
			return false;
		}

	}

	private boolean checkHeroesDmgEqual(Object hero1, Object hero2)
			throws IllegalArgumentException, IllegalAccessException,
			ClassNotFoundException {
		Class curr1 = Class.forName(characterPath);

		Field attackdmg1 = null;

		try {
			attackdmg1 = curr1.getDeclaredField("attackDmg");
			attackdmg1.setAccessible(true);

			int attdmg1 = (int) attackdmg1.get(hero1);
			int attdmg2 = (int) attackdmg1.get(hero2);

			return attdmg1 == attdmg2;

		} catch (NoSuchFieldException e) {
			curr1 = curr1.getSuperclass();
			fail("Attributes name error");
			return false;
		}

	}

	private void writeFightersCSVForLoad() throws FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter("test_Fighters.csv");
		csvWriter.println("Fighter,FIGH,1000,50,20");
		csvWriter.println("FighterGamedGedan,FIGH,5000,500,100");
		csvWriter.println("mehFighter,FIGH,50,5,1");

		csvWriter.flush();
		csvWriter.close();

	}

	private void writeMedicCSVForLoad() throws FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter("test_MEDs.csv");
		csvWriter.println("average,MED,1000,20,50");
		csvWriter.println("MedGamedGedan,MED,5000,100,500");
		csvWriter.println("mehMed,MED,50,1,5");

		csvWriter.flush();
		csvWriter.close();
	}

	private void writeExplorerCSVForLoad() throws FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter("test_Exp.csv");
		csvWriter.println("average,EXP,1000,20,50");
		csvWriter.println("GamedGedan,EXP,5000,100,500");
		csvWriter.println("meh,EXP,50,1,5");

		csvWriter.flush();
		csvWriter.close();

	}

	private void testConstructorDoesnotExist(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);
			msg = msg.substring(0, msg.length() - 4);
			assertTrue("There should not be a  constructor with " + msg
					+ " parameter" + (inputs.length > 1 ? "s" : "") + " in "
					+ aClass.getSimpleName() + " class.", thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getSimpleName() + " class.", thrown);
	}

	private static void testEnumValues(String name, String path, String[] value) {
		try {
			Class aClass = Class.forName(path);
			for (int i = 0; i < value.length; i++) {
				try {
					Enum.valueOf((Class<Enum>) aClass, value[i]);
				} catch (IllegalArgumentException e) {
					fail(aClass.getSimpleName() + " enum can be " + value[i]);
				}
			}
		} catch (ClassNotFoundException e1) {
			fail("There should be an enum called " + name + "in package "
					+ path);
		}
	}

	private void testGetterAbsentInSubclasses(String varName,
			String[] subclasses, Class type) throws SecurityException,
			ClassNotFoundException {
		String methodName = "get" + varName.substring(0, 1).toUpperCase()
				+ varName.substring(1);
		if (type == boolean.class) {
			methodName = "is" + varName.substring(0, 1).toUpperCase()
					+ varName.substring(1);
		}
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses
					|| containsMethodName(methods, methodName);
		}
		assertFalse("The " + methodName
				+ " method should not be implemented in subclasses.",
				methodIsInSubclasses);
	}

	private void testIsEnum(Class aClass) {
		assertEquals(aClass.getName() + " should be an Enum", true,
				aClass.isEnum());
	}

	private void testStaticVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in class Game should be private",
				(Modifier.isPrivate(modifiers)));
	}

	private void testLoadMethodExistsInClass(Class aClass, String methodName,
			Class inputType) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";

		assertTrue(aClass.getSimpleName() + " class should have " + methodName
				+ " method that takes one " + inputType.getSimpleName()
				+ " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in "
				+ aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	public void testVariableIsNotStatic(String classPath, String varName)
			throws NoSuchFieldException, SecurityException,
			ClassNotFoundException {
		Field f = Class.forName(classPath).getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(
				f.getName() + " variable in class "
						+ Class.forName(classPath).getSimpleName()
						+ " should not be static",
				(!Modifier.isStatic(modifiers)));
	}

}
