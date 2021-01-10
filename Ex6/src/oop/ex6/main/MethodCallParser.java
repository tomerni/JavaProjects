package oop.ex6.main;

import oop.ex6.variables.Type;
import oop.ex6.variables.VariableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * Class that handles method calls parsing
 */
public class MethodCallParser {

	/**
	 * parses a method call
	 * @param line the method call line
	 * @param methodMap HashMap with the methods
	 * @param curHash the current scope's HashMap
	 * @param fatherHash the scope's father HashMap
	 * @return true if the call is legal, false otherwise
	 * @throws MethodException method exception
	 * @throws VariableException variable exception
	 */
	public static boolean methodCallParser(String line, HashMap<String, ArrayList<Type>> methodMap,
										   HashMap<String, String[]> curHash, HashMap<String, String[]>
												   fatherHash) throws MethodException, VariableException {
		Matcher methodNameMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.methodNameString);
		if (methodNameMatcher == null) {
			return false;
		}
		String methodName = line.substring(methodNameMatcher.start(), methodNameMatcher.end() - 1).trim();
		if (!methodMap.containsKey(methodName)) {
			throw new IllegalMethodCallException();
		}
		String listOfArgs = line.substring(methodNameMatcher.end());
		methodArgumentsVerifier(listOfArgs, methodMap.get(methodName), curHash, fatherHash);
		return true;
	}

	/*
	 * verifies that the method arguments are legal
	 */
	private static void methodArgumentsVerifier(String listOfArgs, ArrayList<Type> listOfTypes,
												HashMap<String, String[]> curHash, HashMap<String,
			String[]> fatherHash) throws MethodException, VariableException {
		Matcher argMatcher = PatternsKit.methodArgsString.matcher(listOfArgs);
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
