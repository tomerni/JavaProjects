package oop.ex6.main;

import oop.ex6.variables.IntType;
import oop.ex6.variables.StringType;
import oop.ex6.variables.Type;
import oop.ex6.variables.VariableParser;

import java.util.HashMap;


// TODO: Write the different scopes
// TODO: Write a class of all pattern getters
// TODO: Write the global scope
// TODO: Write exception classes and integrate them in the code
// TODO: Kill self
// TODO: Submit the code with the 'fuckyouhashsetavivyeush'
// TODO: Prettify (Magic numbers, documentation, indicative error messages...

public class sJavac {

    public static void main(String[] args){
        try{
            HashMap<String, String[]> fuckYouHash;
            HashMap<String, Type[]> lifeSucksHash;
            lifeSucksHash = new HashMap<>();
            Type[] tomer = {new IntType(), new StringType()};
            lifeSucksHash.put("heli", tomer);
            fuckYouHash = new HashMap<>();
            VariableParser.mainVariableParser("int a = 5;", fuckYouHash);
            VariableParser.mainVariableParser("int b = 5;", fuckYouHash);
            MethodCallParser.methodCallParser("heli( a);", lifeSucksHash, fuckYouHash);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

