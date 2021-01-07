package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharType implements Type{
    @Override
    public void valueVerifier(String value, HashMap<String, String[]> curHash,
                              HashMap<String, String[]> fatherHash) throws RuntimeException {
        Pattern valuePattern = Pattern.compile("'[^\",'\\\\]?'");
        Matcher valueMatcher = valuePattern.matcher(value);
        boolean matchFound = valueMatcher.matches();
        if(matchFound){
            return;
        }
        String assignedValueType = searchForType(value, curHash, fatherHash);
        if(!(assignedValueType.equals("char"))){
            throw new RuntimeException();
        }
    }

    @Override
    public String getTypeName() {
        return "char";
    }
}
