package oop.ex6.main;


import oop.ex6.variables.VariableException;

import java.io.FileNotFoundException;
import java.io.IOException;



public class Sjavac {

    private final static String VALID_FILE = "0";

    private final static String ILLEGAL_FILE = "1";

    private final static int LEGAL_LENGTH = 1;

    private final static String IO_ERROR = "2";


    public static void main(String[] args){
        try{
            if (args.length != LEGAL_LENGTH){
                throw new FileNotFoundException();
            }
            GlobalParser g = new GlobalParser(args[0]);
            g.fileParse();
            System.out.println(VALID_FILE);
        } catch (VariableException | MethodException | StructureException e) {
            System.err.println(e.getMessage());
            System.out.println(ILLEGAL_FILE);
        } catch (IOException e){
            System.err.println(e.getMessage());
            System.out.println(IO_ERROR);
        }
    }
}

