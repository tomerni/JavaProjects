package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BooleanType implements Type {

    private final static String TRUE = "true";

    private final static String FALSE = "false";

    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws RuntimeException{
        if (value.equals(TRUE) || value.equals(FALSE)){
            return;
        }
        Pattern valuePattern = Pattern.compile("[+-]?([\\d]*[.])?[\\d]+"); // finds a double
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean matchFound = valueMatcher.matches();
        if(matchFound){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("boolean") || assignedValueType.equals("int") ||
                assignedValueType.equals("double"))){
            throw new RuntimeException();
        }
    }

    @Override
    public String getTypeName() {
        return "boolean";
    }
}
