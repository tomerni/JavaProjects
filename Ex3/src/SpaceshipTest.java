import oop.ex3.spaceship.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * implements the spaceship tests
 */
public class SpaceshipTest {
	static Item[][] cons = ItemFactory.getConstraintPairs();
	static int[] ids = new int[3];
	static Spaceship space, space1, space2;

	/**
	 * creating the items for the test
	 */
	@BeforeClass
	public static void generatingItems() {
		ids[0] = 1;
		ids[1] = 2;
		ids[2] = 3;
		space = new Spaceship("tomer", ids, 2, cons);
		space1 = new Spaceship("tomer", ids, 2, cons);
		space2 = new Spaceship("tomer", ids, 4, cons);
	}

	/**
	 * tests the create locker method
	 */
	@Test
	public void createLockerTest() {
		assertEquals("createLockerTest(SpaceshipTest): valid create failure", 0,
					 space.createLocker(1, 10)); // can create
		assertNotNull(space.getLockers()[0]); // locker and not null
		assertEquals("createLockerTest(SpaceshipTest): locker capacity failure", 10,
					 space.getLockers()[0].getCapacity());
		// capacity of 10

		assertEquals("createLockerTest(SpaceshipTest): not a valid id failure", -1,
					 space.createLocker(10, 10)); // not a
		// valid id

		assertEquals("createLockerTest(SpaceshipTest): invalid capacity", -2,
					 space.createLocker(2, -10)); // negative
		// capacity

		assertEquals("createLockerTest(SpaceshipTest): valid create failure", 0,
					 space.createLocker(2, 40)); // can enter
		assertNotNull(space.getLockers()[1]); // locker and not null
		assertEquals("createLockerTest(SpaceshipTest): locker capacity failure", 40,
					 space.getLockers()[1].getCapacity());
		// length of
		// lockers is 40

		assertEquals("createLockerTest(SpaceshipTest): not enough room to create more lockers", -3,
					 space.createLocker(3,
										20)); // not enough
		// room for another
	}

	/**
	 * tests that the get methods don't return null
	 */
	@Test
	public void returningNotNullTest() {

		assertNotNull(space1.getCrewIDs());
		assertNotNull(space1.getLockers());
		assertNotNull(space1.getLongTermStorage());
	}

	/**
	 * tests the different ships have different lts
	 */
	@Test
	public void notTheSameLTSTest() {
		assertNotEquals(space.getLongTermStorage(), space1.getLongTermStorage());
	}

	/**
	 * tests that the array from the getLockers method is valid
	 */
	@Test
	public void validLengthOfLockersArrayTest() {
		assertEquals(4, space2.getLockers().length);
	}

}
