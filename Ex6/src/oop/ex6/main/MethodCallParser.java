package oop.ex6.main;

import oop.ex6.variables.Type;
import oop.ex6.variables.VariableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallParser {

	public static boolean methodCallParser(String line, HashMap<String, ArrayList<Type>> methodMap,
										   HashMap<String,
			String[]> curHash, HashMap<String, String[]> fatherHash) throws MethodException,
																			VariableException {
		Pattern methodNamePattern = Pattern.compile("^\\s*[\\w]+\\s*\\(");
		Matcher methodNameMatcher = methodNamePattern.matcher(line);
		boolean methodNameFound = methodNameMatcher.find();
		if (!methodNameFound) {
			return false;
		}
		String methodName = line.substring(methodNameMatcher.start(), methodNameMatcher.end() - 1).trim();
		if (!methodMap.containsKey(methodName)) {
			throw new IllegalMethodCallException();
		}
		String listOfArgs = line.substring(methodNameMatcher.end());
		methodArgumentsVerifier(listOfArgs, methodMap.get(methodName), curHash ,fatherHash);
		return true;
	}


	private static void methodArgumentsVerifier(String listOfArgs, ArrayList<Type> listOfTypes,
												HashMap<String, String[]> curHash, HashMap<String,
			String[]> fatherHash) throws MethodException, VariableException {
		Pattern argPattern = Pattern.compile("[^=]+?([=].+?)?[,)]");
		Matcher argMatcher = argPattern.matcher(listOfArgs);
		int indexOfType = 0;
		while (argMatcher.find()) {
			if (indexOfType == listOfTypes.size()) {
				throw new IllegalArgumentsException();
			}
			String currentArg = listOfArgs.substring(argMatcher.start(), argMatcher.end() - 1).trim();
			Type currentType = listOfTypes.get(indexOfType);
			currentType.valueVerifier(currentArg, curHash, fatherHash);
			indexOfType += 1;
		}
		if (indexOfType < (listOfTypes.size())) {
			throw new IllegalArgumentsException();
		}
	}
}
