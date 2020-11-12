import oop.ex2.GameGUI;

import java.awt.*;

/**
 * Implementation of the HumanControlledShip class
 */
public class HumanControlledShip extends SpaceShip {


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (game.getGUI().isTeleportPressed()) {
            teleport();
        }
        checkMove(game);
        if (game.getGUI().isShieldsPressed()) {
            shieldOn();
        } else {
            IS_SHIELD = false;
        }
        if (SHOT_COUNTER > 0) {
            SHOT_COUNTER--;
        }
        if (game.getGUI().isShotPressed()) {
            fire(game);
        }
        CUR_ENERGY = updateEnergy(CUR_ENERGY);
    }

    /**
     * Gets the image of the human controlled ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * @return the image of this ship.
     */
    public Image getImage() {
        if (IS_SHIELD) {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.SPACESHIP_IMAGE;
    }

    /*
     * moves the space ship according to the pressed keys
     * @param game the game Object
     */
    private void checkMove(SpaceWars game) {
        boolean isAccelerate = false;
        int turn = 0;
        if (game.getGUI().isUpPressed()) {
            isAccelerate = true;
        }
        if (game.getGUI().isLeftPressed()) {
            turn += 1;
        }
        if (game.getGUI().isRightPressed()) {
            turn -= 1;
        }
        POSITION.move(isAccelerate, turn);
    }
}
