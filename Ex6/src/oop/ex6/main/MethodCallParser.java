package oop.ex6.main;

import oop.ex6.variables.Type;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallParser {

	public static void methodCallParser(String line, HashMap<String, Type[]> methodMap, HashMap<String,
			String[]> curHash) throws RuntimeException {
		Pattern methodNamePattern = Pattern.compile("^\\s*[\\w]+\\s*\\(");
		Matcher methodNameMatcher = methodNamePattern.matcher(line);
		boolean methodNameFound = methodNameMatcher.find();
		if (!methodNameFound) {
			throw new RuntimeException();
		}
		String methodName = line.substring(methodNameMatcher.start(), methodNameMatcher.end() - 1).trim();
		if (!methodMap.containsKey(methodName)) {
			throw new RuntimeException();
		}
		String listOfArgs = line.substring(methodNameMatcher.end());
		methodArgumentsVerifier(listOfArgs, methodMap.get(methodName), curHash);
	}


	private static void methodArgumentsVerifier(String listOfArgs, Type[] listOfTypes,
												   HashMap<String, String[]> curHash) throws RuntimeException{
		Pattern argPattern = Pattern.compile("[^=]+?([=].+?)?[,)]");
		Matcher argMatcher = argPattern.matcher(listOfArgs);
		int indexOfType = 0;
		while(argMatcher.find()){
			if(indexOfType == listOfTypes.length) {
				throw new RuntimeException();
			}
			String currentArg = listOfArgs.substring(argMatcher.start(), argMatcher.end()-1);
			Type currentType = listOfTypes[indexOfType];
			currentType.valueVerifier(currentArg, curHash);
			indexOfType += 1;
		}
		if(indexOfType < listOfTypes.length - 1){
			throw new RuntimeException();
		}
	}
}
