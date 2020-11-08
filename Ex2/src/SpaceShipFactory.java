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
        int humanCount = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("b")) {
                list[i] = new BasherShip();
                continue;
            }
            if (args[i].equals("r")) {
                list[i] = new RunnerShip();
                continue;
            }
            if (args[i].equals("d")) {
                list[i] = new DrunkardShip();
                continue;
            }
            if (args[i].equals("h")) {
                list[i] = new HumanControlledShip();
                humanCount++;
                continue;
            }
            if (args[i].equals("a")) {
                list[i] = new AggressiveShip();
                continue;
            }
            if (args[i].equals("s")) {
                list[i] = new SpecialShip();
                continue;
            }
            return null;
        }
        if(humanCount < 1){
            return null;
        }
        return list;
    }
}
