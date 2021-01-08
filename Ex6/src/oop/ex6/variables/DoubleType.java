package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleType implements Type {
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws VariableException {
        Pattern valuePattern = Pattern.compile("[+-]?([\\d]*[.])?[\\d]+"); // finds a double
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean matchFound = valueMatcher.matches();
        if(matchFound){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("int") || assignedValueType.equals("double"))){
            throw new IllegalValueAssignmentException();
        }
    }

    @Override
    public String getTypeName() {
        return "double";
    }
}
