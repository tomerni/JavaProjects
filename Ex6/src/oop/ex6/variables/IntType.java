package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntType implements Type{
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws RuntimeException{
        Pattern valuePattern = Pattern.compile("[+-]?[\\d]+"); // finds a int
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean matchFound = valueMatcher.matches();
        if(matchFound){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("int") )){
            throw new RuntimeException();
        }
    }

    @Override
    public String getTypeName() {
        return "int";
    }
}
