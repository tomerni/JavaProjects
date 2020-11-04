/**
 * Implementation of the BasherShip class
 */
public class BasherShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double distFromClosest = POSITION.distanceFrom(closestShip.getPhysics());
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        moveTowardEnemy(angleFromClosest);
        isShield(distFromClosest);
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
    }

    /**
     * Checks if the ship need to open the shield
     * @param dist the distance from the enemy
     */
    private void isShield(double dist) {
        if (dist <= 0.19) {
            shieldOn();
        } else {
            IS_SHIELD = false;
        }
    }
}
