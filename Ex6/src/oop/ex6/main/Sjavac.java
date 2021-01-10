package oop.ex6.main;


import oop.ex6.variables.VariableException;

import java.io.FileNotFoundException;
import java.io.IOException;



// TODO: Write a class of all pattern getters
// TODO: Kill self
// TODO: Submit the code with the 'fuckyouhashsetAvivYeush'
// TODO: Prettify (Magic numbers, documentation, indicative error messages...

public class Sjavac {

    public static void main(String[] args){
        try{
            if (args.length != 1){
                throw new FileNotFoundException();
            }
            GlobalParser g = new GlobalParser(args[0]);
            g.fileParse();
            System.out.println("0");
        } catch (VariableException | MethodException | StructureException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.out.println("1");
        } catch (IOException e){
            System.err.println(e.getMessage());
            System.out.println("2");
        }
    }
}

