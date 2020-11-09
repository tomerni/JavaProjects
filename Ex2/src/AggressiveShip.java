/**
 * Implementation of the AggressiveShip class
 */
public class AggressiveShip extends AttackerShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        moveTowardEnemy(angleFromClosest);
        closestShip = game.getClosestShipTo(this);
        angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        isFire(angleFromClosest, game);
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
    }
}
