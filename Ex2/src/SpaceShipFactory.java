/**
 * SpaceShipFactory class
 */
public class SpaceShipFactory {
    /**
     * Creates an array of ships according to the given arguments
     * @param args list of the command prompt arguments
     * @return array with the relevant space ship objects
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] list = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("b")) {
                list[i] = new BasherShip();
            } else {
                if (args[i].equals("r")) {
                    list[i] = new RunnerShip();
                } else {
                    if (args[i].equals("d")) {
                        list[i] = new DrunkardShip();
                    } else {
                        if (args[i].equals("h")) {
                            list[i] = new HumanControlledShip();
                        } else {
                            if (args[i].equals("a")) {
                                list[i] = new AggressiveShip();
                            } else {
                                list[i] = new SpecialShip();
                            }
                        }
                    }
                }
            }
        }
        return list;
        }
    }
