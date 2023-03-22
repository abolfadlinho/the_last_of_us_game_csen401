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

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class M1PublicTests {

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
	public void testConstructorCharacter() throws ClassNotFoundException {
		Class[] inputs = { String.class, int.class, int.class };
		testConstructorExists(Class.forName(characterPath), inputs);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableLocationPresent() throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath), "location", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableLocationPrivate() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "location");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableLocationType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "location", Point.class);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableMaxHpPresent() throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath), "maxHp", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableMaxHpPrivate() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "maxHp");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableMaxHpType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "maxHp", int.class);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableCurrentHPPresent() throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath), "currentHp", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableCurrentHPPrivate() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "currentHp");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableCurrentHPType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "currentHp", int.class);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableTargetPresent() throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath), "target", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableTargetPrivate() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "target");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableTargetType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "target", Class.forName(characterPath));
	}

	@Test(timeout = 100)
	public void testCharacterLocationGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomX = (int) (Math.random() * 10) + 1;
		int randomY = (int) (Math.random() * 10) + 1;
		Object b = constructor.newInstance("name", randomX, randomY, randomX);
		testGetterLogicCharacterClass1(b, "location", new Point(randomX, randomY));

	}

	@Test(timeout = 100)
	public void testCharacterLocationSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(characterPath), "setLocation", Point.class, true);

	}

	@Test(timeout = 100)
	public void testCharacterLocationSetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomX = (int) (Math.random() * 10) + 1;
		int randomY = (int) (Math.random() * 10) + 1;
		int randomX2 = (int) (Math.random() * 10) + 1;
		int randomY2 = (int) (Math.random() * 10) + 1;
		Object b = constructor.newInstance("name", randomX, randomY, randomX2);
		testSetterLogic1(b, "location", new Point(randomX2, randomY2), new Point(randomX2, randomY2), Point.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableChampionMaxHPGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(characterPath), "getMaxHp", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterMaxHPGetterLogic() throws Exception {
		Constructor<?> Constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		Object c = Constructor.newInstance("Name_" + randomName, randomMaxHP, randomAttackDamage, randomActions);
		testGetterLogicCharacterClass1(c, "maxHp", randomMaxHP);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(characterPath), "getCurrentHp", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPGetterLogic() throws Exception {
		Constructor<?> Constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object c = Constructor.newInstance("Name_" + randomName, randomMaxHP, randomAttackDamage, randomActions);
		testGetterLogicCharacterClass1(c, "currentHp", randomMaxHP);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPSetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomMaxHP = (int) (Math.random() * 50) + 11;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		int randomCurrentHP = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		Object c = constructor.newInstance("Name_" + randomName, randomMaxHP, randomAttackDamage, randomActions);
		testSetterLogic1(c, "currentHp", randomCurrentHP, randomCurrentHP, int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterAttackDamageGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(characterPath), "getAttackDmg", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterAttackDamageGetterLogic() throws Exception {
		Constructor<?> characterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object c = characterConstructor.newInstance("Name_" + randomName, randomMaxHP, randomAttackDamage,
				randomActions);
		testGetterLogicCharacterClass1(c, "attackDmg", randomAttackDamage);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterTargetGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(characterPath), "getTarget", Class.forName(characterPath), true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterTargetGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name, random, random, random);
		Object target = constructor.newInstance("", 0, 0, 0);
		testGetterLogicCharacterClass1(myObj, "target", target);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterTargetSetter() throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(characterPath), "setTarget", Class.forName(characterPath), true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterTargetSetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		Object myObj = constructor.newInstance(name, random, random, random);
		Object target = constructor.newInstance("target", (int) (Math.random() * 50), (int) (Math.random() * 50),
				(int) (Math.random() * 50));
		testSetterLogic1(myObj, "target", target, target, Class.forName(characterPath));
	}

	@Test(timeout = 100)
	public void testEmptyConstructorVaccine() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(vaccinePath), inputs);
	}

	@Test(timeout = 1000)
	public void testCellIsSuperClassOfCharacterCell() throws Exception {
		testClassIsSubclass(Class.forName(charCell), Class.forName(cellPath));
	}

	@Test(timeout = 1000)
	public void testCellIsSuperClassOfCollectibleCell() throws Exception {
		testClassIsSubclass(Class.forName(collectibleCellPath), Class.forName(cellPath));
	}

	@Test(timeout = 1000)
	public void testCellIsSuperClassOfTrapCell() throws Exception {
		testClassIsSubclass(Class.forName(trapCellPath), Class.forName(cellPath));
	}

	@Test(timeout = 1000)
	public void testClassIsEnumDirection() throws Exception {
		testIsEnum(Class.forName(enumDirectionPath));
	}

	@Test(timeout = 1000)
	public void testConstructorClassCollectibleCell() throws Exception {
		Class[] inputs = { Class.forName(collectiblePath) };
		testConstructorExists(Class.forName(collectibleCellPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorClassTrapCell() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(trapCellPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorClassTrapCellWithInputDoesNotExist() throws Exception {
		Class[] inputs = { int.class };
		testConstructorDoesnotExist(Class.forName(trapCellPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationClassCharacterCellOneArg() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object fighter = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		Constructor<?> constructorCharacterCell = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object charCell = constructorCharacterCell.newInstance(fighter);

		String[] names = { "character", "isSafe" };
		Object[] values = { fighter, false };

		testConstructorInitialization(charCell, names, values);
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationClassCollectibleCell() throws Exception {
		Constructor<?> constructorCollectibleCell = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));
		Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
		Object c = constructorSupply.newInstance();

		Object collectCell = constructorCollectibleCell.newInstance(c);

		String[] names = { "collectible" };
		Object[] values = { c };

		testConstructorInitialization(collectCell, names, values);
	}

	@Test(timeout = 1000)
	public void testDefaultConstructorClassCell() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(cellPath), inputs);
	}

	@Test(timeout = 1000)
	public void testEnumValuesDirection() {
		String[] inputs = { "UP", "DOWN", "LEFT", "RIGHT" };
		testEnumValues("Direction", enumDirectionPath, inputs);
	}

	@Test(timeout = 1000)
	public void testFirstConstructorClassCharacterCell() throws Exception {
		Class[] inputs = { Class.forName(characterPath) };
		testConstructorExists(Class.forName(charCell), inputs);
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCharacterInClassCharacterCell() throws Exception {
		testGetterMethodExistsInClass(Class.forName(charCell), "getCharacter", Class.forName(characterPath), true);
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCollectibleInClassCollectibleCell() throws Exception {
		testGetterMethodExistsInClass(Class.forName(collectibleCellPath), "getCollectible",
				Class.forName(collectiblePath), true);
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableIsSafeInClassCharacterCell() throws Exception {
		testGetterMethodExistsInClass(Class.forName(charCell), "isSafe", boolean.class, true);
	}

	@Test(timeout = 1000)
	public void testGetterForInstanceVariableIsVisibleExistsInClassCell() throws Exception {
		testGetterMethodExistsInClass(Class.forName(cellPath), "isVisible", boolean.class, true);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCharacterInClassCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructorCharacterCell = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object charCell = constructorCharacterCell.newInstance(character);

		testGetterLogic(charCell, "character", character);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCharacterInClassCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object character = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructorCharacterCell = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object charCell = constructorCharacterCell.newInstance(character);

		testSetterLogic(charCell, "character", character, character, Class.forName(characterPath));
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCollectibleInClassCollectibleCell() throws Exception {
		Constructor<?> constructorSupply = Class.forName(supplyPath).getConstructor();
		Object supply = constructorSupply.newInstance();

		Constructor<?> constructorCollectibleCell = Class.forName(collectibleCellPath)
				.getConstructor(Class.forName(collectiblePath));
		Object collectCell = constructorCollectibleCell.newInstance(supply);

		testGetterLogic(collectCell, "collectible", supply);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableIsSafeInClassCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructorCharacter = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Object fighter = constructorCharacter.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object obj = constructor.newInstance(fighter);
		testGetterLogic(obj, "isSafe", true);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableIsSafeInClassCharacterCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructorCharacter = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Object fighter = constructorCharacter.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object obj = constructor.newInstance(fighter);
		testSetterLogic(obj, "isSafe", true, true, boolean.class);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableIsVisibleExistsInClassCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructorCharacter = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Object fighter = constructorCharacter.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object obj = constructor.newInstance(fighter);
		testGetterLogic(obj, "isVisible", false);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableIsVisibleExistsInClassCell() throws Exception {
		int random = (int) (Math.random() * 1000);
		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructorCharacter = Class.forName(fighterPath).getConstructor(String.class, int.class,
				int.class, int.class);
		Object fighter = constructorCharacter.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);
		Constructor<?> constructor = Class.forName(charCell).getConstructor(Class.forName(characterPath));
		Object obj = constructor.newInstance(fighter);
		testSetterLogic(obj, "isVisible", false, false, boolean.class);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTrapDamageInClassTrapCell() throws Exception {
		Constructor<?> constructor = Class.forName(trapCellPath).getConstructor();
		Object obj = constructor.newInstance();
		testGetterLogic(obj, "trapDamage", -15);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCharacterIsNotPresentInClassTrapCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(trapCellPath), "character", false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCharacterIsNotStaticInClassCharacterCell() throws Exception {
		testVariableIsNotStatic(charCell, "character");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCharacterIsPresentInClassCharacterCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(charCell), "character", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCharacterIsPrivateInClassCharacterCell() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(charCell), "character");
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCollectibleIsNotPresentInClassCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cellPath), "collectible", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCollectibleIsNotPresentInClassCharacterCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(charCell), "collectible", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCollectibleIsNotPresentInClassCollectibleCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(collectibleCellPath), "character", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableCollectibleIsNotPresentInClassTrapCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(trapCellPath), "collectible", false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCollectibleIsPresentInClassCollectibleCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(collectibleCellPath), "collectible", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableCollectibleIsPrivateInClassCollectibleCell() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(collectibleCellPath), "collectible");
	}

	@Test(timeout = 10000)
	public void testInstanceVariableIsSafeIsNotPresentInClassCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cellPath), "isSafe", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableIsSafeIsNotPresentInClassTrapCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(trapCellPath), "isSafe", false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsSafeIsNotStaticInClassCharacterCell() throws Exception {
		testVariableIsNotStatic(charCell, "isSafe");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsSafeIsPresentInClassCharacterCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(charCell), "isSafe", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsSafeIsPrivateInClassCharacterCell() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(charCell), "isSafe");
	}

	@Test(timeout = 10000)
	public void testInstanceVariableIsVisibleIsNotPresentInClassCharacterCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(charCell), "isVisible", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableIsVisibleIsNotPresentInClassCollectibleCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(collectibleCellPath), "isVisible", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableIsVisibleIsNotPresentInClassTrapCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(trapCellPath), "isVisible", false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsVisibleIsNotStaticInClassCell() throws Exception {
		testVariableIsNotStatic(cellPath, "isVisible");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsVisibleIsPresentInClassCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cellPath), "isVisible", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsVisibleIsPrivateInClassCell() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(cellPath), "isVisible");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableIsVisibleTypeIsBooleanInClassCell() throws Exception {
		testInstanceVariableOfType(Class.forName(cellPath), "isVisible", boolean.class);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableTrapDamageIsNotPresentInClassCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(cellPath), "trapDamage", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableTrapDamageIsNotPresentInClassCharacterCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(charCell), "trapDamage", false);
	}

	@Test(timeout = 10000)
	public void testInstanceVariableTrapDamageIsNotPresentInClassCollectibleCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(collectibleCellPath), "trapDamage", false);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTrapDamageIsNotStaticInClassTrapCell() throws Exception {
		testVariableIsNotStatic(trapCellPath, "trapDamage");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTrapDamageIsPresentInClassTrapCell() throws Exception {
		testInstanceVariableIsPresent(Class.forName(trapCellPath), "trapDamage", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTrapDamageIsPrivateInClassTrapCell() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(trapCellPath), "trapDamage");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTypeCharacterIsCharacterInClassCharacterCell() throws Exception {
		testInstanceVariableOfType(Class.forName(charCell), "character", Class.forName(characterPath));
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTypeCollectibleIsCollectibleInClassCollectibleCell() throws Exception {
		testInstanceVariableOfType(Class.forName(collectibleCellPath), "collectible", Class.forName(collectiblePath));
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTypeCollectibleIsCollectibleInClassTrapCell() throws Exception {
		testInstanceVariableOfType(Class.forName(trapCellPath), "trapDamage", int.class);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableTypeIsSafeIsBooleanInClassCharacterCell() throws Exception {
		testInstanceVariableOfType(Class.forName(charCell), "isSafe", boolean.class);
	}

	@Test(timeout = 1000)
	public void testIsCellAnAbstractClass() throws Exception {
		testClassIsAbstract(Class.forName(cellPath));
	}

	@Test(timeout = 10000)
	public void testRandomisationOfTrapCellConstructor()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> trapCellConstructor = Class.forName(trapCellPath).getConstructor();
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 200; i++) {
			Object trapCell = trapCellConstructor.newInstance();
			set.add(((TrapCell) trapCell).getTrapDamage());
		}
		assertEquals(set.contains(10) && set.contains(20) && set.contains(30) && set.size() == 3, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCharacterExistsInClassCharacterCell() throws Exception {
		testSetterMethodExistsInClass(Class.forName(charCell), "setCharacter", Class.forName(characterPath), true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableIsSafeExistsInClassCharacterCell() throws Exception {
		testSetterMethodExistsInClass(Class.forName(charCell), "setSafe", boolean.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableIsVisibleExistsInClassCell() throws Exception {
		testSetterMethodExistsInClass(Class.forName(cellPath), "setVisible", boolean.class, true);
	}

	public void testVariableIsNotStatic(String classPath, String varName)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(classPath).getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in class " + Class.forName(classPath).getSimpleName()
				+ " should not be static", (!Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameMapisStatic() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("map");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be static", (Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameZombiesisStatic() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("zombies");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be static", (Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameMapisPublic() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("map");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be public", (Modifier.isPublic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameHeroesisPublic() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("heroes");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be public", (Modifier.isPublic(modifiers)));
	}

	@Test(timeout = 800)
	public void testGameZombiesisPublic() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(gamePath).getDeclaredField("zombies");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be public", (Modifier.isPublic(modifiers)));
	}

	@Test(timeout = 100)
	public void testMapInGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "map", true);
		testInstanceVariableOfTypeDoubleArray(Class.forName(gamePath), "map", Class.forName(cellPath));
	}

	@Test(timeout = 100)
	public void testHeroesInGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "heroes", true);

		testInstanceVariableOfType(Class.forName(gamePath), "heroes", ArrayList.class);
	}

	@Test(timeout = 100)
	public void testAvailableHeroesInGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "availableHeroes", true);

		testInstanceVariableOfType(Class.forName(gamePath), "availableHeroes", ArrayList.class);
	}

	@Test(timeout = 100)
	public void testZombiesInGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "zombies", true);

		testInstanceVariableOfType(Class.forName(gamePath), "zombies", ArrayList.class);
	}

	@Test(timeout = 1000)
	public void testLoadHerosGameExist() throws Exception {
		testLoadMethodExistsInClass(Class.forName(gamePath), "loadHeroes", String.class);

	}

	@Test(timeout = 1000)
	public void testLoadChampionsMethodMix()
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

		for (int i = 0; i < 6; i++) {
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

		ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

		assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
				actualChampions.size() == heros.size());
		for (int i = 0; i < actualChampions.size(); i++) {

			assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), heros.get(i).getClass());
			assertTrue("Hero's name loaded incorrectly", checkHeroesNameEqual(actualChampions.get(i), heros.get(i)));
			assertTrue("Hero's HP loaded incorrectly", checkHeroesHPEqual(actualChampions.get(i), heros.get(i)));
			assertTrue("Hero's actions loaded incorrectly",
					checkHeroesActionEqual(actualChampions.get(i), heros.get(i)));
			assertTrue("Hero's damage loaded incorrectly", checkHeroesDmgEqual(actualChampions.get(i), heros.get(i)));
		}

	}

	@Test(timeout = 1000)
	public void testLoadFightersCorrectName() {
		try {
			writeFightersCSVForLoad();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Field fd;
		try {
			fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Fighters.csv");

			ArrayList<Object> fighters = new ArrayList<Object>();
			Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdFighter1 = fighterConstructor.newInstance("Fighter", 1000, 20, 50);
			Object createdFighter2 = fighterConstructor.newInstance("FighterGamedGedan", 5000, 100, 500);
			Object createdFighter3 = fighterConstructor.newInstance("mehFighter", 50, 1, 5);

			fighters.add(createdFighter1);
			fighters.add(createdFighter2);
			fighters.add(createdFighter3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == fighters.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(fighterPath));
				assertTrue("Fighter's name loaded incorrectly",
						checkHeroesNameEqual(actualChampions.get(i), fighters.get(i)));
			}
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}

	}

	@Test(timeout = 1000)
	public void testLoadFightersCorrectHP() {
		try {
			writeFightersCSVForLoad();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Field fd;
		try {
			fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Fighters.csv");

			ArrayList<Object> fighters = new ArrayList<Object>();
			Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdFighter1 = fighterConstructor.newInstance("Fighter", 1000, 20, 50);
			Object createdFighter2 = fighterConstructor.newInstance("FighterGamedGedan", 5000, 100, 500);
			Object createdFighter3 = fighterConstructor.newInstance("mehFighter", 50, 1, 5);

			fighters.add(createdFighter1);
			fighters.add(createdFighter2);
			fighters.add(createdFighter3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == fighters.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(fighterPath));
				assertTrue("Fighter's HP loaded incorrectly",
						checkHeroesHPEqual(actualChampions.get(i), fighters.get(i)));
			}
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}

	}

	@Test(timeout = 1000)
	public void testLoadFightersCorrectAttack() {
		try {
			writeFightersCSVForLoad();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Field fd;
		try {
			fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Fighters.csv");

			ArrayList<Object> fighters = new ArrayList<Object>();
			Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdFighter1 = fighterConstructor.newInstance("Fighter", 1000, 20, 50);
			Object createdFighter2 = fighterConstructor.newInstance("FighterGamedGedan", 5000, 100, 500);
			Object createdFighter3 = fighterConstructor.newInstance("mehFighter", 50, 1, 5);

			fighters.add(createdFighter1);
			fighters.add(createdFighter2);
			fighters.add(createdFighter3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == fighters.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(fighterPath));
				assertTrue("Fighter's max number of actions loaded incorrectly",
						checkHeroesActionEqual(actualChampions.get(i), fighters.get(i)));
			}
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 1000)
	public void testLoadFightersCorrectDmg() {
		try {
			writeFightersCSVForLoad();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Field fd;
		try {
			fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Fighters.csv");

			ArrayList<Object> fighters = new ArrayList<Object>();
			Constructor<?> fighterConstructor = Class.forName(fighterPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdFighter1 = fighterConstructor.newInstance("Fighter", 1000, 20, 50);
			Object createdFighter2 = fighterConstructor.newInstance("FighterGamedGedan", 5000, 100, 500);
			Object createdFighter3 = fighterConstructor.newInstance("mehFighter", 50, 1, 5);

			fighters.add(createdFighter1);
			fighters.add(createdFighter2);
			fighters.add(createdFighter3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == fighters.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(fighterPath));
				assertTrue("Fighter's attack damage loaded incorrectly",
						checkHeroesDmgEqual(actualChampions.get(i), fighters.get(i)));
			}
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 1000)
	public void testLoadMedsCorrectName() {
		try {
			writeMedicCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_MEDs.csv");

			ArrayList<Object> medics = new ArrayList<Object>();
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("average", 1000, 50, 20);
			Object createdMedics2 = medicsConstructor.newInstance("MedGamedGedan", 5000, 500, 100);
			Object createdMedics3 = medicsConstructor.newInstance("mehMed", 50, 5, 1);

			medics.add(createdMedics1);
			medics.add(createdMedics2);
			medics.add(createdMedics3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == medics.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(medicPath));
				assertTrue("Medic's name loaded incorrectly",
						checkHeroesNameEqual(actualChampions.get(i), medics.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 1000)
	public void testLoadMedsCorrectActions() {
		try {
			writeMedicCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_MEDs.csv");

			ArrayList<Object> medics = new ArrayList<Object>();
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("average", 1000, 50, 20);
			Object createdMedics2 = medicsConstructor.newInstance("MedGamedGedan", 5000, 500, 100);
			Object createdMedics3 = medicsConstructor.newInstance("mehMed", 50, 5, 1);

			medics.add(createdMedics1);
			medics.add(createdMedics2);
			medics.add(createdMedics3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == medics.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(medicPath));
				assertTrue("Medic's number of actions incorrectly",
						checkHeroesActionEqual(actualChampions.get(i), medics.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 1000)
	public void testLoadMedsCorrectDmg() {
		try {
			writeMedicCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_MEDs.csv");

			ArrayList<Object> medics = new ArrayList<Object>();
			Constructor<?> medicsConstructor = Class.forName(medicPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdMedics1 = medicsConstructor.newInstance("average", 1000, 50, 20);
			Object createdMedics2 = medicsConstructor.newInstance("MedGamedGedan", 5000, 500, 100);
			Object createdMedics3 = medicsConstructor.newInstance("mehMed", 50, 5, 1);

			medics.add(createdMedics1);
			medics.add(createdMedics2);
			medics.add(createdMedics3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == medics.size());
			for (int i = 0; i < actualChampions.size(); i++) {
				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(medicPath));
				assertTrue("Medic's attack damage loaded incorrectly",
						checkHeroesDmgEqual(actualChampions.get(i), medics.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
	}

	@Test(timeout = 1000)
	public void testLoadExplorersCorrectName() {
		try {
			writeExplorerCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Exp.csv");

			ArrayList<Object> explorers = new ArrayList<Object>();
			Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdExplorer1 = explorerConstructor.newInstance("average", 1000, 50, 20);
			Object createdExplorer2 = explorerConstructor.newInstance("GamedGedan", 5000, 500, 100);
			Object createdExplorer3 = explorerConstructor.newInstance("meh", 50, 5, 1);

			explorers.add(createdExplorer1);
			explorers.add(createdExplorer2);
			explorers.add(createdExplorer3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == explorers.size());
			for (int i = 0; i < actualChampions.size(); i++) {

				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(explorerPath));
				assertTrue("Explorer's name loaded incorrectly",
						checkHeroesNameEqual(actualChampions.get(i), explorers.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
	}

	@Test(timeout = 1000)
	public void testLoadExplorersCorrectHP() {
		try {
			writeExplorerCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Exp.csv");

			ArrayList<Object> explorers = new ArrayList<Object>();
			Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdExplorer1 = explorerConstructor.newInstance("average", 1000, 50, 20);
			Object createdExplorer2 = explorerConstructor.newInstance("GamedGedan", 5000, 500, 100);
			Object createdExplorer3 = explorerConstructor.newInstance("meh", 50, 5, 1);

			explorers.add(createdExplorer1);
			explorers.add(createdExplorer2);
			explorers.add(createdExplorer3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == explorers.size());
			for (int i = 0; i < actualChampions.size(); i++) {

				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(explorerPath));
				assertTrue("Explorer's HP loaded incorrectly",
						checkHeroesHPEqual(actualChampions.get(i), explorers.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
	}

	@Test(timeout = 1000)
	public void testLoadExplorersCorrectActions() {
		try {
			writeExplorerCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Exp.csv");

			ArrayList<Object> explorers = new ArrayList<Object>();
			Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdExplorer1 = explorerConstructor.newInstance("average", 1000, 50, 20);
			Object createdExplorer2 = explorerConstructor.newInstance("GamedGedan", 5000, 500, 100);
			Object createdExplorer3 = explorerConstructor.newInstance("meh", 50, 5, 1);

			explorers.add(createdExplorer1);
			explorers.add(createdExplorer2);
			explorers.add(createdExplorer3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == explorers.size());
			for (int i = 0; i < actualChampions.size(); i++) {

				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(explorerPath));
				assertTrue("Explorer's number of actions loaded incorrectly",
						checkHeroesActionEqual(actualChampions.get(i), explorers.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
	}

	@Test(timeout = 1000)
	public void testLoadExplorersCorrectDmg() {
		try {
			writeExplorerCSVForLoad();
			Field fd = Class.forName(gamePath).getDeclaredField("availableHeroes");
			fd.setAccessible(true);
			fd.set((ArrayList<Object>) fd.get(null), new ArrayList<>());

			Method m = Class.forName(gamePath).getMethod("loadHeroes", String.class);
			m.invoke(null, "test_Exp.csv");

			ArrayList<Object> explorers = new ArrayList<Object>();
			Constructor<?> explorerConstructor = Class.forName(explorerPath).getConstructor(String.class, int.class,
					int.class, int.class);
			Object createdExplorer1 = explorerConstructor.newInstance("average", 1000, 50, 20);
			Object createdExplorer2 = explorerConstructor.newInstance("GamedGedan", 5000, 500, 100);
			Object createdExplorer3 = explorerConstructor.newInstance("meh", 50, 5, 1);

			explorers.add(createdExplorer1);
			explorers.add(createdExplorer2);
			explorers.add(createdExplorer3);

			ArrayList<Object> actualChampions = (ArrayList<Object>) fd.get(null);

			assertTrue("Method loadHeroes in Game: the number of Heros Loaded is incorrect",
					actualChampions.size() == explorers.size());
			for (int i = 0; i < actualChampions.size(); i++) {

				assertEquals("Wrong Hero type loaded", actualChampions.get(i).getClass(), Class.forName(explorerPath));
				assertTrue("Explorer's attack damage loaded incorrectly",
						checkHeroesDmgEqual(actualChampions.get(i), explorers.get(i)));
			}
		} catch (FileNotFoundException | NoSuchFieldException | SecurityException | ClassNotFoundException
				| IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
	}

	@Test(timeout = 100)
	public void testConstructorGameActionException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(gameActionExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractGameActionException() throws Exception {
		testClassIsAbstract(Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassGameActionException() throws Exception {
		testClassIsSubclass(Class.forName(gameActionExceptionPath), Exception.class);
	}

	@Test(timeout = 100)
	public void testConstructorInvalidTargetException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(invalidTargetExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorInvalidTargetException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(invalidTargetExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorMovementException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(movementExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassMovementException() throws Exception {
		testClassIsSubclass(Class.forName(movementExceptionPath), Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 100)
	public void testConstructorNoAvailableResourcesException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(noAvailableResourcesExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorNoAvailableResourcesException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(noAvailableResourcesExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorNotEnoughActionsException() throws Exception {

		Class[] inputs = { String.class };
		testConstructorExists(Class.forName(notEnoughActionsExceptionPath), inputs);
	}

	@Test(timeout = 100)
	public void testEmptyConstructorNotEnoughActionsException() throws Exception {

		Class[] inputs = {};
		testConstructorExists(Class.forName(notEnoughActionsExceptionPath), inputs);
	}

	@Test(timeout = 1000)
	public void testClassIsSubclassNotEnoughActionsException() throws Exception {
		testClassIsSubclass(Class.forName(notEnoughActionsExceptionPath), Class.forName(gameActionExceptionPath));
	}

	@Test(timeout = 1000)
	public void testCharacterIsSuperClassOfHero() throws Exception {
		testClassIsSubclass(Class.forName(heroPath), Class.forName(characterPath));
	}

	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfFighter() throws Exception {
		testClassIsSubclass(Class.forName(fighterPath), Class.forName(heroPath));
	}

	@Test(timeout = 1000)
	public void testConstructorOfHero() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(Class.forName(heroPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorOfZombie() throws Exception {
		Class[] inputs = {};
		testConstructorExists(Class.forName(zombiePath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorOfExplorer() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(Class.forName(explorerPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationMedic() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Medic " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		String[] names = { "name", "maxHp", "currentHp", "attackDmg", "maxActions", "actionsAvailable",
				"vaccineInventory", "supplyInventory", "specialAction" };
		Object[] values = { nameHero, maxHpHero, maxHpHero, attackDmgHero, maxActionsHero, maxActionsHero, heroVacine,
				heroSupply, false };

		testConstructorInitialization(hero, names, values);
	}

	@Test(timeout = 2000)
	public void testConstructorInitializationZombie() throws Exception {
		int random = (int) (Math.random() * 1000);

		Object[] zombies = new Zombie[50];

		Constructor<?> constructor = Class.forName(zombiePath).getConstructor();

		for (int i = 0; i < zombies.length; i++) {
			zombies[i] = constructor.newInstance();
		}

		for (int i = 0; i < zombies.length; i++) {
			String[] names = { "name", "maxHp", "currentHp", "attackDmg" };
			Object[] values = { "Zombie " + (i + 1), 40, 40, 10 };

			testConstructorInitialization(zombies[i], names, values);
		}

	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableNamePresent() throws Exception {
		testInstanceVariableIsPresent(Class.forName(characterPath), "name", true);
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableNamePrivate() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(characterPath), "name");
	}

	@Test(timeout = 100)
	public void testCharacterInstanceVariableNameType() throws Exception {
		testInstanceVariableOfType(Class.forName(characterPath), "name", String.class);
	}

	@Test(timeout = 100)
	public void testCharacterNameGetter() throws Exception {
		testGetterMethodExistsInClass(Class.forName(characterPath), "getName", String.class, true);

	}

	@Test(timeout = 100)
	public void testCharacterNameGetterLogic() throws Exception {
		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomX = (int) (Math.random() * 10) + 1;
		int randomY = (int) (Math.random() * 10) + 1;
		String s = "name" + randomX;
		Object b = constructor.newInstance(s, randomX, randomY, randomX);
		testGetterLogicCharacterClass1(b, "name", s);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPSetter() throws ClassNotFoundException {
		testSetterMethodExistsInClass(Class.forName(characterPath), "setCurrentHp", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCharacterCurrentHPSetterLogicMax() throws Exception {
		Constructor<?> Constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		int randomCurrentHP = randomMaxHP + (int) (Math.random() * 50) + 1;
		Object c = Constructor.newInstance("Name_" + randomName, randomMaxHP, randomAttackDamage, randomActions);
		testSetterLogic1(c, "currentHp", randomCurrentHP, randomMaxHP, int.class);
	}

	@Test(timeout = 100)
	public void testVaccineImplementsCollectible() {
		try {
			testClassImplementsInterface(Class.forName(vaccinePath), Class.forName(collectiblePath));
		} catch (ClassNotFoundException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
		}
	}

	@Test(timeout = 800)
	public void testZombiesCountstatic3() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Class.forName(zombiePath).getDeclaredField("ZOMBIES_COUNT");
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in calss Game should be static", (Modifier.isStatic(modifiers)));
	}

	@Test(timeout = 100)
	public void testGetterForInstanceVariableActionsAvailableInClassHero3() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(heroPath), "getActionsAvailable", int.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testGetterAbsentInSubclasses("actionsAvailable", subClasses, int.class);
	}

	@Test(timeout = 100)
	public void testGetterForInstanceVariableSpecialActionInClassHero3() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(heroPath), "isSpecialAction", boolean.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testGetterAbsentInSubclasses("specialAction", subClasses, boolean.class);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableActionsAvailableExistsInClassHero3() throws Exception {
		testSetterMethodExistsInClass(Class.forName(heroPath), "setActionsAvailable", int.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testSetterAbsentInSubclasses("actionsAvailable", subClasses);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableActionsAvailableInClassFighter3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "actionsAvailable", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSupplyInventoryInClassFighter3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "supplyInventory", heroSupply);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableActionsAvailableInClassMedic3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "actionsAvailable", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSupplyInventoryInClassMedic3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "supplyInventory", heroSupply);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableActionsAvailableInClassExplorer3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "actionsAvailable", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSupplyInventoryInClassExplorer3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "supplyInventory", heroSupply);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableActionsAvailableInClassFighter3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "actionsAvailable", testRandom, maxActionsHero, int.class);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableSpecialActionInClassFighter3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "specialAction", true, false, boolean.class);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableActionsAvailableInClassMedic3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "actionsAvailable", testRandom, maxActionsHero, int.class);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableSpecialActionInClassMedic3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "specialAction", true, false, boolean.class);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableActionsAvailableInClassExplorer3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "actionsAvailable", testRandom, maxActionsHero, int.class);
	}

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableSpecialActionInClassExplorer3() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		int testRandom = (int) ((Math.random() * 100) + 5);

		testSetterLogic(hero, "specialAction", true, false, boolean.class);
	}

	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfMedic() throws Exception {
		testClassIsSubclass(Class.forName(medicPath), Class.forName(heroPath));
	}

	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfExplorer() throws Exception {
		testClassIsSubclass(Class.forName(explorerPath), Class.forName(heroPath));
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationFighter() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		String[] names = { "name", "maxHp", "currentHp", "attackDmg", "maxActions", "actionsAvailable",
				"vaccineInventory", "supplyInventory", "specialAction" };
		Object[] values = { nameHero, maxHpHero, maxHpHero, attackDmgHero, maxActionsHero, maxActionsHero, heroVacine,
				heroSupply, false };

		testConstructorInitialization(hero, names, values);
	}

	@Test(timeout = 100)
	public void testGetterForInstanceVariableMaxActionsInClassHero() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(heroPath), "getMaxActions", int.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testGetterAbsentInSubclasses("maxActions", subClasses, int.class);
	}

	@Test(timeout = 100)
	public void testGetterForInstanceVariableVaccineInventoryInClassHero() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(heroPath), "getVaccineInventory", ArrayList.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testGetterAbsentInSubclasses("vaccineInventory", subClasses, Vaccine.class);
	}

	@Test(timeout = 100)
	public void testGetterForInstanceVariableSupplyInventoryInClassHero() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(heroPath), "getSupplyInventory", ArrayList.class, true);
		String[] subClasses = { fighterPath, medicPath, explorerPath };
		testGetterAbsentInSubclasses("supplyInventory", subClasses, Supply.class);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableMaxActionsInClassFighter4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "maxActions", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSpecialActionInClassFighter4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "specialAction", false);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableVaccineInventoryInClassFighter4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(fighterPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "vaccineInventory", heroVacine);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableMaxActionsInClassMedic4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "maxActions", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSpecialActionInClassMedic4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "specialAction", false);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableVaccineInventoryInClassMedic4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(medicPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "vaccineInventory", heroVacine);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableMaxActionsInClassExplorer4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "maxActions", maxActionsHero);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSpecialActionInClassExplorer4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "specialAction", false);
	}

	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableVaccineInventoryInClassExplorer4() throws Exception {
		int random = (int) (Math.random() * 1000);

		String nameHero = "Fighter " + random;
		int maxHpHero = (int) (Math.random() * 100);
		int attackDmgHero = (int) (Math.random() * 100);
		int maxActionsHero = (int) (Math.random() * 5);
		ArrayList<Object> heroVacine = new ArrayList<>();
		ArrayList<Object> heroSupply = new ArrayList<>();

		Constructor<?> constructor = Class.forName(explorerPath).getConstructor(String.class, int.class, int.class,
				int.class);
		Object hero = constructor.newInstance(nameHero, maxHpHero, attackDmgHero, maxActionsHero);

		testGetterLogic(hero, "vaccineInventory", heroVacine);
	}

	private void testSetterLogic1(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
			throws Exception {

		Field f = null;
		Field ff = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
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
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);
		if ((name.equals("currentHp") && createdObject.getClass().getSimpleName().equals("Character"))
				|| name.equals("currentActionPoints")) {
			if ((int) setValue == -1 && (int) expectedValue == 0) {
				assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\". It should not be less than zero.", expectedValue, f.get(createdObject));
			} else if ((int) setValue > (int) expectedValue) {
				assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name
						+ "\". It should not exceed the max value.", expectedValue, f.get(createdObject));
			}

		}

		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name + "\".",
				expectedValue, f.get(createdObject));

	}

	private void testSetterLogic(Object createdObject, String name, Object valueIn, Object valueOut, Class type)
			throws Exception {
		Field f = null;
		Class curr = createdObject.getClass();
		while (f == null) {
			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}
		}
		f.setAccessible(true);
		String methodName;
		if (valueIn.getClass().getSimpleName().equals("Boolean") && name.substring(0, 2).equals("is")) {
			Character c = name.charAt(2);
			methodName = "set" + name.substring(2, name.length());
		} else {
			Character c = name.charAt(0);
			methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		}
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, valueIn);
		assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
				+ " should set the correct value of variable \"" + name + "\".", valueIn, f.get(createdObject));
	}

	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	private void testIsInterface(Class aClass) {
		assertEquals(aClass.getName() + " should be a abstract", true, aClass.isInterface());
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
			assertFalse("Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in "
					+ aClass.getSimpleName() + " class.", thrown);
		} else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.", thrown);
	}

	private void testConstructorInitialization(Object createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}
			}
			f.setAccessible(true);
			if (currName.equals("currentHp") || currName.equals("currentActionPoints")) {

				assertEquals(
						"The constructor of the " + createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName
								+ "\" correctly. It should be equals to the max initially.",
						currValue, f.get(createdObject));
			} else {
				assertEquals(
						"The constructor of the " + createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName + "\" correctly.",
						currValue, f.get(createdObject));
			}
		}

	}

	private void testClassIsSubclass(Class subClass, Class superClass) {
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".",
				superClass, subClass.getSuperclass());
	}

	private void testClassImplementsInterface(Class aClass, Class iClass) {
		assertTrue("Class \"" + aClass.getSimpleName() + "\" should implement \"" + iClass.getSimpleName()
				+ "\" interface.", iClass.isAssignableFrom(aClass));
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		boolean b = true;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			b = false;
		}
		if (b) {
			Class varType = f.getType();
			assertEquals("The attribute " + varType.getSimpleName() + " of instance variable: " + varName
					+ " should be of the type " + expectedType.getSimpleName(), expectedType, varType);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should be declared in class " + aClass.getSimpleName()
					+ ".", false);
		}

	}

	private void testInstanceVariableOfTypeDoubleArray(Class aClass, String varName, Class expectedType) {
		Field f = null;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			return;
		}
		Class varType = f.getType();
		assertEquals(
				"the attribute: " + varType.getSimpleName() + " should be of the type: " + expectedType.getSimpleName(),
				expectedType.getTypeName() + "[][]", varType.getTypeName());
	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {
		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		boolean b = 2 == f.getModifiers();
		assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
				+ " should not be accessed outside that class.", b);
	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
		else
			varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class) && !name.substring(0, 2).equals("is"))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());
		else if (value.getClass().equals(Boolean.class) && name.substring(0, 2).equals("is"))
			methodName = "is" + Character.toUpperCase(name.charAt(2)) + name.substring(3, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should return the correct value of variable \"" + name + "\".",
				value, m.invoke(createdObject));

	}

	private void testGetterLogicCharacterClass1(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = Class.forName(characterPath);

		while (f == null) {

			if (curr == Object.class)
				fail("Class Character" + " should have the instance variable \"" + name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

		Method m = Class.forName(characterPath).getMethod(methodName);
		assertEquals("The method \"" + methodName
				+ "\" in class Character should return the correct value of variable \"" + name + "\".", value,
				m.invoke(createdObject));

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void testSetterAbsentInSubclasses(String varName, String[] subclasses)
			throws SecurityException, ClassNotFoundException {
		String methodName = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in a subclasses.", methodIsInSubclasses);
	}

	private boolean checkHeroesNameEqual(Object hero1, Object hero2)
			throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
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
			throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
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
			throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Class curr1 = Class.forName(characterPath);

		Field maxActions1 = null;

		try {
			maxActions1 = Class.forName(heroPath).getDeclaredField("maxActions");
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
			throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
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
			assertTrue("There should not be a  constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "")
					+ " in " + aClass.getSimpleName() + " class.", thrown);
		} else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.", thrown);
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
			fail("There should be an enum called " + name + "in package " + path);
		}
	}

	private void testGetterAbsentInSubclasses(String varName, String[] subclasses, Class type)
			throws SecurityException, ClassNotFoundException {
		String methodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		if (type == boolean.class) {
			methodName = "is" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		}
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);
		}
		assertFalse("The " + methodName + " method should not be implemented in subclasses.", methodIsInSubclasses);
	}

	private void testIsEnum(Class aClass) {
		assertEquals(aClass.getName() + " should be an Enum", true, aClass.isEnum());
	}

	private void testStaticVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName() + " variable in class Game should be private", (Modifier.isPrivate(modifiers)));
	}

	private void testLoadMethodExistsInClass(Class aClass, String methodName, Class inputType) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

}
