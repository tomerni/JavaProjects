/**
 * Implementation of the RunnerShip class
 */
public class RunnerShip extends DefenderShip {

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
}
