package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;

public class CharType implements Type{
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws VariableException {
        if(PatternsKit.matchPattern(value, PatternsKit.charTypeString)){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("char"))){
            throw new IllegalValueAssignmentException();
        }
    }

    @Override
    public String getTypeName() {
        return "char";
    }
}
