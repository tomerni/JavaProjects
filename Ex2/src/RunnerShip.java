/**
 * Implementation of the RunnerShip class
 */
public class RunnerShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double distFromClosest = POSITION.distanceFrom(closestShip.getPhysics());
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        if (distFromClosest < 0.25 && Math.abs(angleFromClosest) < 0.23) {
            teleport();
        }
        moveFromEnemy(angleFromClosest);
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
