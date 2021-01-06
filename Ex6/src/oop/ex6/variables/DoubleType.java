package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleType implements Type {
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash) throws RuntimeException {
        Pattern valuePattern = Pattern.compile("[+-]?([\\d]*[.])?[\\d]+"); // finds a double
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean matchFound = valueMatcher.matches();
        if(matchFound){
            return;
        }
        boolean isAssigned = searchForAssignedValue(value, curHash);
        if(!isAssigned) {
            throw new RuntimeException();
        }
        String assignedValueType = curHash.get(value)[0];
        if(!(assignedValueType.equals("int") || assignedValueType.equals("double"))){
            throw new RuntimeException();
        }
    }

    @Override
    public String getTypeName() {
        return "double";
    }
}
