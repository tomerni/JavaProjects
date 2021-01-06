package oop.ex6.variables;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableParser {

    private final static String TRUE = "true";

    private final static String FALSE = "false";

    // TODO: Notice that we assume the line contains only a single ';' at the end of it, and has no white
    //  spaces at the beginning and at the end
    // TODO: From line 42 on, divide into several methods
    // TODO: Consider deleting the decParser method

    public static void mainVariableParser(String line, HashMap<String, String[]> curHash){

//        Pattern endPattern = Pattern.compile(";\\s*$");
//        Matcher endMatcher = endPattern.matcher(line);
//        boolean endFound = endMatcher.find();
//        if(!endFound){
//            throw new RuntimeException();
//        }
        Pattern finalPattern = Pattern.compile("^\\s*final\\s+");
        Matcher finalMatcher = finalPattern.matcher(line);
        boolean finalFound = finalMatcher.find();
        if(finalFound){
            line = line.substring(finalMatcher.end());
        }
        Pattern typePattern = Pattern.compile("^\\s*(int|boolean|double|char|String)\\s+");
        Matcher typeMatcher = typePattern.matcher(line);
        boolean decFound = typeMatcher.find();
        if(decFound){
            String type = typeMatcher.group(1);
            line = line.substring(typeMatcher.end());
            if(finalFound) {
                decParser(line, type, curHash, TRUE);
            } else {
                decParser(line ,type, curHash, FALSE);
            }
        } else {
            if(!line.contains("=") && finalFound) {
                throw new RuntimeException();
            } else {
                String[] splitLine = line.split("=");
                if(splitLine.length != 2){
                    throw new RuntimeException();
                }
                String name = splitLine[0].trim();
                String value = splitLine[1].substring(0, splitLine[1].length()-1).trim();
                String type = curHash.get(name)[0];
                String isFinal = curHash.get(name)[2];
                if(type == null || isFinal.equals(TRUE)) {
                    throw new RuntimeException();
                }
                Type valType = TypesFactory.createTypes(type);
                valType.valueVerifier(value, curHash);
                String[] varArray = curHash.get(name);
                varArray[1] = TRUE;
                curHash.put(name, varArray);
            }
        }
    }

    private static void decParser(String line, String type, HashMap<String, String[]> curHash,
                                  String isFinal) throws RuntimeException{
        Type valType = TypesFactory.createTypes(type);
        assignmentParser(line, valType, curHash, isFinal);
    }

    private static void assignmentParser(String line, Type valType, HashMap<String, String[]> curHash,
                                         String isFinal){
        Pattern varPattern = Pattern.compile("[^=]+?([=].+?)?[,;]");
        Matcher varMatcher = varPattern.matcher(line);
        while(varMatcher.find()){
            String currentVar = line.substring(varMatcher.start(), varMatcher.end()-1);
            if(currentVar.contains("=")){
                String[] splitLine = currentVar.split("=");
                if(splitLine.length != 2){
                    throw new RuntimeException();
                }
                String name = splitLine[0].trim();
                String value = splitLine[1].trim();
                valType.nameVerifier(name, curHash, true);
                valType.valueVerifier(value, curHash);
                String[] varArray = {valType.getTypeName(), TRUE, isFinal};
                curHash.put(name, varArray);
            } else {
                if(isFinal.equals(TRUE)) {
                    throw new RuntimeException();
                }
                String name = currentVar.trim();
                valType.nameVerifier(name, curHash, true);
                String[] varArray = {valType.getTypeName(), FALSE, isFinal};
                curHash.put(name, varArray);
            }
        }
    }

}
