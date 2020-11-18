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
            switch (args[i].charAt(0)) {
                case 'b':
                    list[i] = new BasherShip();
                    break;
                case 'r':
                    list[i] = new RunnerShip();
                    break;
                case 'd':
                    list[i] = new DrunkardShip();
                    break;
                case 'h':
                    list[i] = new HumanControlledShip();
                    humanCount++;
                    break;
                case 'a':
                    list[i] = new AggressiveShip();
                    break;
                case 's':
                    list[i] = new SpecialShip();
                    break;
                default:
                    return null;
            }
        }
        if (humanCount > 1) {
            return null;
        }
        return list;
    }
}
