/**
 * Implementation of the SpecialShip class
 */
public class SpecialShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double distFromClosest = POSITION.distanceFrom(closestShip.getPhysics());
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        if (HEALTH == 1) {
            teleport();
        }
        if (HEALTH > 1 && HEALTH <= 3) {
            moveFromEnemy(angleFromClosest);
        } else {
            moveTowardEnemy(angleFromClosest);
        }
        isShield(distFromClosest);
        isFire(angleFromClosest, game);
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
    }

    /**
     * Moves the space ship from the nearest enemy
     * @param angle the angle to the enemy
     */
    private void moveFromEnemy(double angle) {
        if (angle >= 0) {
            POSITION.move(true, -1);
        } else {
            POSITION.move(true, 1);
        }
    }

    /**
     * Checks if the ship need to open the shield
     * @param dist the distance from the enemy
     */
    private void isShield(double dist) {
        if (dist <= 0.08) {
            shieldOn();
        } else {
            IS_SHIELD = false;
        }
    }

    /**
     * Checks if the ship needs to fire
     * @param angle the angle to the enemy
     * @param game the game Object
     */
    private void isFire(double angle, SpaceWars game) {
        if (SHOT_COUNTER > 0) {
            SHOT_COUNTER--;
        }
        if (Math.abs(angle) < 0.21) {
            fire(game);
        }
    }
}
