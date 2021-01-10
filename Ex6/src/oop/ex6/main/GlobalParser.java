package oop.ex6.main;


import oop.ex6.variables.Type;
import oop.ex6.variables.TypesFactory;
import oop.ex6.variables.VariableException;
import oop.ex6.variables.VariableParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Class that handles the global scope parsing
 */
public class GlobalParser {

	/** HashMap the stores the methods in the file */
	public static HashMap<String, ArrayList<Type>> methodMap;

	// HashMap that stores the global variables in the file
	private final HashMap<String, String[]> globalVarsMap;

	// Two-dimensional array the holds the lines of each method
	private final ArrayList<ArrayList<String>> methodsImplementation;

	// The file's scanner
	private final Scanner scanner;

	private final static String INVALID_COMMENT = ".+//.*";

	private final static String RETURN_REGEX = "return\\s*;";

	private final static String VALID_COMMENT ="//";

	private final static String VOID ="void";

	private final static String PARAMS_DELIMITER = ",";

	private final static int TYPE_INDEX = 2;

	private final static int NAME_INDEX = 3;

	private final static String METHOD_CLOSING = "}";

	private final static String METHOD_OPENING = "{";


	GlobalParser(String fileName) throws FileNotFoundException {
		methodMap = new HashMap<>();
		globalVarsMap = new HashMap<>();
		methodsImplementation = new ArrayList<>();
		scanner = new Scanner(new FileReader(fileName));
	}

	/**
	 * the main function that handles the parsing of the whole file
	 * @throws StructureException structure exception
	 * @throws VariableException variable exception
	 * @throws MethodException method exception
	 */
	public void fileParse() throws StructureException, VariableException, MethodException {
		while (scanner.hasNext()) {
			String line = (scanner.nextLine());
			if (line.matches(INVALID_COMMENT)) {
				throw new InvalidLineStructureException();
			}
			line = line.trim();
			if (line.isEmpty() || line.startsWith(VALID_COMMENT)) {
				continue;
			}
			if (!parseGlobalVariables(line)) {
				if (!PatternsKit.findPattern(line, PatternsKit.methodDecString)) {
					throw new InvalidLineStructureException();
				}
				buildMethodString(line);
			}
		}
		methodParsing();
	}

	/*
	 * iterates over the methods in the file and parses them
	 */
	private void methodParsing() throws VariableException, MethodException, StructureException {
		for (ArrayList<String> list : methodsImplementation) {
			HashMap<String, String[]> fatherHash = new HashMap<>(globalVarsMap);
			Block curMethod = new Block(list, fatherHash, true);
			curMethod.parseScope();
		}
	}

	/*
	 * builds the lines of the current method and adds them to the Two-dimensional array
	 */
	private void buildMethodString(String line) throws StructureException, VariableException {
		ArrayList<String> curMethod = new ArrayList<>();
		curMethod.add(line.trim());
		signatureVerifier(line);
		int parenthesis = 1;
		while (parenthesis != 0) {
			if (!scanner.hasNext()) {
				throw new InvalidMethodStructureException();
			}
			if (line.matches(INVALID_COMMENT)) {
				throw new InvalidLineStructureException();
			}
			line = scanner.nextLine().trim();
			if (line.isEmpty() || line.startsWith(VALID_COMMENT)) {
				continue;
			}
			if (line.endsWith(METHOD_OPENING)) {
				parenthesis++;
			} else if (line.equals(METHOD_CLOSING)) {
				parenthesis--;
			} else if (line.startsWith(VOID)) {
				throw new InvalidMethodStructureException();
			}
			curMethod.add(line);
		}
		String hasReturn = curMethod.get(curMethod.size() - 2);
		if (!hasReturn.matches(RETURN_REGEX)) {
			throw new InvalidMethodStructureException();
		}
		methodsImplementation.add(curMethod);
	}

	/*
	 * parses the variables in the global scope
	 */
	private boolean parseGlobalVariables(String line) throws VariableException {
		if (PatternsKit.findPattern(line, PatternsKit.validDecString) ||
			PatternsKit.findPattern(line, PatternsKit.validAssString)) {
			VariableParser.mainVariableParser(line, globalVarsMap, new HashMap<>());
			return true;
		}
		return false;
	}

	/*
	 * verifies that the signature of the method is valid
	 */
	private void signatureVerifier(String line) throws StructureException, VariableException {
		Matcher voidMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.voidString);
		if(!(voidMatcher == null)) {
			line = line.substring(voidMatcher.end());
		}
		Matcher nameMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.methodNameString);
		if (nameMatcher == null) {
			throw new InvalidMethodStructureException();
		}
		String methodName = line.substring(nameMatcher.start(), nameMatcher.end() - 1);
		if (methodMap.containsKey(methodName)) {
			throw new InvalidMethodStructureException();
		}
		line = (line.substring((nameMatcher.end() - 1))).trim();
		ArrayList<Type> typesOfParameters = parameterListVerifier(line);
		methodMap.put(methodName, typesOfParameters);
	}

	/*
	 * return a list ot the types in the method decleration
	 */
	private ArrayList<Type> parameterListVerifier(String listOfParameters)
			throws StructureException, VariableException {
		Matcher allParamMatcher = PatternsKit.returnFindMatcher(listOfParameters,
																PatternsKit.methodParenString);
		if (allParamMatcher == null) {
			throw new InvalidMethodStructureException();
		}
		String allParams = (allParamMatcher.group(1)).trim();
		if (allParams.isEmpty()) {
			return new ArrayList<>();
		}
		String[] eachParam = allParams.split(PARAMS_DELIMITER, -1);
		ArrayList<Type> typesOfParameters = new ArrayList<>();
		for (String s : eachParam) {
			Matcher paramListMatcher = PatternsKit.returnFindMatcher(s, PatternsKit.paramListString);
			if (paramListMatcher == null) {
				throw new InvalidMethodStructureException();
			}
			Type paramType = TypesFactory.createTypes(paramListMatcher.group(TYPE_INDEX));
			paramType.nameVerifier(paramListMatcher.group(NAME_INDEX), new HashMap<>(), new HashMap<>(), true);
			typesOfParameters.add(paramType);
		}
		return typesOfParameters;
	}
}
