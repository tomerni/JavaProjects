package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;

public class StringType implements Type {
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws VariableException{
        if(PatternsKit.matchPattern(value, PatternsKit.stringTypeString)){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("String"))){
            throw new IllegalValueAssignmentException();
        }
    }

    @Override
    public String getTypeName() {
        return "String";
    }
}
