package oop.ex6.main;

import oop.ex6.variables.IntType;
import oop.ex6.variables.StringType;
import oop.ex6.variables.Type;
import oop.ex6.variables.VariableParser;

import java.io.FileNotFoundException;
import java.util.HashMap;

// TODO: Each method that works with the curHash, needs to receive the fathers' map
// TODO: Combine 'ifScope' and 'whileScope' (and methodScope) into one
// TODO: Implement the lines-parsing in a scope


// TODO: Write the different scopes
// TODO: Write a class of all pattern getters
// TODO: Write exception classes and integrate them in the code
// TODO: Kill self
// TODO: Submit the code with the 'fuckyouhashsetAvivYeush'
// TODO: Prettify (Magic numbers, documentation, indicative error messages...

public class Sjavac {

    static String fileName = "C:\\Users\\tomer\\Google Drive (tomer.nissim@mail.huji.ac.il)\\Second " +
                      "year\\Semester A\\oop\\Ex6\\tests\\test%s.sjava";

//    public static void main(String[] args){
//        for(int i = 1; i < 506; i ++){
//            String fileNumber;
//            if ( i < 10){
//                fileNumber = "00" + i;
//            }
//            else if (i >= 10 && i < 100){
//                fileNumber = "0"+ i;
//            }
//            else{
//                fileNumber = String.valueOf(i);
//            }
//            runOneTime(fileNumber);
//        }
//    }

    public static void main(String[] args){
        try{
            if (args.length != 1){
                throw new FileNotFoundException();
            }
            GlobalParser g = new GlobalParser(args[0]);
            g.fileParse();
            System.out.println("0");

        } catch (RuntimeException e) {
            //e.printStackTrace();
            System.out.println("1");
        } catch (FileNotFoundException e){
            //e.printStackTrace();
            System.out.println("2");
        }
    }
}

