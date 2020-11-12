import java.util.Random;

/**
 * Implementation of the DrunkardShip class
 */
public class DrunkardShip extends SpaceShip {

    //The random object of the space ship
    private final Random rand = new Random();

    //Decides if the ship is going to go left or right
    private final int leftOrRight = rand.nextInt(2);

    //Counts the turns
    private int turnsCounter = 0;

    //Decides on which turns the space ship  is going to jump
    private final int modToJump = rand.nextInt(60) + 90;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (turnsCounter % 1000 == modToJump) {
            teleport();
        }
        if (leftOrRight == 1) {
            POSITION.move(true, -1);
        } else {
            POSITION.move(true, 1);
        }
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
        turnsCounter++;
    }
}
