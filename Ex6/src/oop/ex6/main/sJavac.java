package oop.ex6.main;

import oop.ex6.variables.VariableParser;

import java.util.HashMap;


// TODO: Write the method-calling handling class
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
            fuckYouHash = new HashMap<>();
            VariableParser.mainVariableParser("int a = 5;", fuckYouHash);
            VariableParser.mainVariableParser("int b = 5; c=4;", fuckYouHash);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

