import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * implements the spaceship depository tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		LockerTest.class,
		LongTermTest.class,
		SpaceshipTest.class
					})
public class SpaceshipDepositoryTest {
}
