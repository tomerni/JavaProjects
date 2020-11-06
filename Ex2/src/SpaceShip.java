import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 * @author oop
 */
public class SpaceShip {


    protected SpaceShipPhysics POSITION = new SpaceShipPhysics();

    protected int MAX_ENERGY = 210;

    protected int HEALTH = 22;

    protected int CUR_ENERGY = 190;

    protected boolean IS_SHIELD = false;

    protected int SHOT_COUNTER = 0;

    /**
     * The implementation of this function is overridden in every space ship that inherits
     * this class
     * @param game the current game object
     */
    public void doAction(SpaceWars game) {
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (IS_SHIELD) {
            MAX_ENERGY += 18;
            CUR_ENERGY += 18;
        } else {
            HEALTH--;
            MAX_ENERGY -= 10;
            if (MAX_ENERGY > CUR_ENERGY) {
                CUR_ENERGY = MAX_ENERGY;
            }
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        POSITION = new SpaceShipPhysics();
        HEALTH = 22;
        MAX_ENERGY = 210;
        CUR_ENERGY = 190;
        SHOT_COUNTER = 0;
    }

    /**
     * Checks if this ship is dead.
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return HEALTH == 0;
    }

    /**
     * Gets the physics object that controls this ship.
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return POSITION;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!IS_SHIELD) {
            if (HEALTH > 0) {
                HEALTH--;
            }
            MAX_ENERGY -= 10;
            if (MAX_ENERGY > CUR_ENERGY) {
                CUR_ENERGY = MAX_ENERGY;
            }
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * @return the image of this ship.
     */
    public Image getImage() {
        if (IS_SHIELD) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (CUR_ENERGY >= 19 && SHOT_COUNTER == 0) {
            game.addShot(POSITION);
            CUR_ENERGY -= 19;
            SHOT_COUNTER = 7;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (CUR_ENERGY >= 3) {
            IS_SHIELD = true;
            CUR_ENERGY -= 3;
        } else {
            IS_SHIELD = false;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (CUR_ENERGY >= 140) {
            POSITION = new SpaceShipPhysics();
            CUR_ENERGY -= 140;
        }
    }

    /**
     * @param curEnergy the current energy
     * @return the new energy
     */
    protected int updateEnergy(int curEnergy) {
        if (curEnergy < MAX_ENERGY) {
            curEnergy += 1;
        }
        return curEnergy;
    }

}
