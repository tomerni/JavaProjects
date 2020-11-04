/**
 * Implementation of the AggressiveShip class
 */
public class AggressiveShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleFromClosest = POSITION.angleTo(closestShip.getPhysics());
        moveTowardEnemy(angleFromClosest);
        isFire(angleFromClosest, game);
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
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
