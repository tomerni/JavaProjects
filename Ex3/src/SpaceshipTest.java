import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;

public class SpaceshipTest {
	static Item[][] cons = ItemFactory.getConstraintPairs();
	static int[] ids = new int[3];

	@Test
	public void createLockerTest() {
		ids[0] = 1;
		ids[1] = 2;
		ids[2] = 3;
		Spaceship space = new Spaceship("tomer",ids,2, cons);

		assertEquals(0, space.createLocker(1,10)); // can create
		assertEquals(10, space.getLockers()[0].getCapacity()); // capacity of 10

		assertEquals(-1, space.createLocker(10,10)); // not a valid id

		assertEquals(-2, space.createLocker(2,-10)); // negative capacity

		assertEquals(0, space.createLocker(2,40)); // can enter
		assertEquals(40, space.getLockers()[1].getCapacity()); // length of lockers is 40

		assertEquals(-3, space.createLocker(3,20)); // not enough room for another

	}

}
