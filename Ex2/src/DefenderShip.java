/**
 * A middle class that has functions that are relevant to defending ships
 */
public class DefenderShip extends SpaceShip{
    /**
     * Moves the space ship from the nearest enemy
     * @param angle the angle to the enemy
     */
    protected void moveFromEnemy(double angle) {
        if (angle >= 0) {
            POSITION.move(true, -1);
        } else {
            POSITION.move(true, 1);
        }
    }
}
