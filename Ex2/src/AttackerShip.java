/**
 * A middle class that has functions that are relevant to attacking ships
 */
public class AttackerShip extends SpaceShip {

    /**
     * Checks if the ship needs to fire
     * @param angle the angle to the enemy
     * @param game the game Object
     */
    protected void isFire(double angle, SpaceWars game) {
        if (SHOT_COUNTER > 0) {
            SHOT_COUNTER--;
        }
        if (Math.abs(angle) < 0.21) {
            fire(game);
        }
    }

    /**
     * Checks if the ship need to open the shield
     * @param dist the distance from the enemy
     */
    protected void isShield(double dist) {
        if (dist <= 0.19) {
            shieldOn();
        } else {
            IS_SHIELD = false;
        }
    }

    /**
     * Moves the space ship toward the nearest enemy
     * @param angle the angle to the enemy
     */
    protected void moveTowardEnemy(double angle) {
        if (angle >= 0) {
            POSITION.move(true, 1);
        } else {
            POSITION.move(true, -1);
        }
    }
}
