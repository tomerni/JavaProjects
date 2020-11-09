/**
 * Implementation of the SpecialShip class
 */
public class SpecialShip extends AttackerShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        if (HEALTH == 1) {
            teleport();
        }
        if (HEALTH > 1 && HEALTH <= 3) {
            moveFromEnemy(angleFromClosest);
        } else {
            moveTowardEnemy(angleFromClosest);
        }
        closestShip = game.getClosestShipTo(this);
        double distFromClosest = POSITION.distanceFrom(closestShip.getPhysics());
        isShield(distFromClosest);
        angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
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
}
