package oop.ex6.main;

import oop.ex6.variables.VariableException;

import java.io.IOException;

/**
 * The main class of the project
 */
public class Sjavac {

    private final static String VALID_FILE = "0";

    private final static String ILLEGAL_FILE = "1";

    private final static int LEGAL_LENGTH = 1;

    private final static String IO_ERROR = "2";

    private final static String INVALID_NUM_OF_ARGS_ERROR_MESSAGE = "ERROR: invalid number of arguments";

    /**
     * the main function of the project
     * @param args list of the arguments
     */
    public static void main(String[] args){
        try{
            if (args.length != LEGAL_LENGTH){
                throw new IOException(INVALID_NUM_OF_ARGS_ERROR_MESSAGE);
            }
            GlobalParser g = new GlobalParser(args[0]);
            g.fileParse();
            System.out.println(VALID_FILE);
        } catch (VariableException | MethodException | StructureException e) {
            System.err.println(e.getMessage());
            System.out.println(ILLEGAL_FILE);
        } catch (IOException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println(IO_ERROR);
        }
    }
}

