package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;

public class DoubleType implements Type {


    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws VariableException {
        if(PatternsKit.matchPattern(value, PatternsKit.doubleTypeString)){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals(INT) || assignedValueType.equals(DOUBLE))){
            throw new IllegalValueAssignmentException();
        }
    }

    @Override
    public String getTypeName() {
        return DOUBLE;
    }
}
