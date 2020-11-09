/**
 * Implementation of the BasherShip class
 */
public class BasherShip extends AttackerShip {

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
        double distFromClosest = POSITION.distanceFrom(closestShip.getPhysics());
        isShield(distFromClosest);
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
    }
}
