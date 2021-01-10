package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;

/**
 * Implements the char type class by implementing the Type interface
 */
public class CharType implements Type{


    /**
     * verifies that the value matches the type
     * @param value the given value
     * @param curHash the current HashMap of the scope
     * @param fatherHash the father's scope HashMap
     * @throws VariableException invalid variable exception
     */
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws VariableException {
        if(PatternsKit.matchPattern(value, PatternsKit.charTypeString)){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals(CHAR))){
            throw new IllegalValueAssignmentException();
        }
    }

    /**
     * @return the name of the type
     */
    @Override
    public String getTypeName() {
        return CHAR;
    }
}
