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

public class GlobalParser {

	public static HashMap<String, ArrayList<Type>> methodMap;

	private final HashMap<String, String[]> globalVarsMap;

	private final ArrayList<ArrayList<String>> methodsImplementation;

	private final Scanner scanner;


	GlobalParser(String fileName) throws FileNotFoundException {
		methodMap = new HashMap<>();
		globalVarsMap = new HashMap<>();
		methodsImplementation = new ArrayList<>();
		scanner = new Scanner(new FileReader(fileName));
	}

	public void fileParse() throws StructureException, VariableException, MethodException {
		while (scanner.hasNext()) {
			String line = (scanner.nextLine());
			if (line.matches(".+//.*")) {
				throw new InvalidLineStructureException();
			}
			line = line.trim();
			if (line.isEmpty() || line.startsWith("//")) {
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

	private void methodParsing() throws VariableException, MethodException, StructureException {
		for (ArrayList<String> list : methodsImplementation) {
			HashMap<String, String[]> fatherHash = new HashMap<>(globalVarsMap);
			Block curMethod = new Block(list, fatherHash, true);
			curMethod.parseScope();
		}
	}

	private void buildMethodString(String line) throws StructureException, VariableException {
		ArrayList<String> curMethod = new ArrayList<>();
		curMethod.add(line.trim());
		signatureVerifier(line);
		int parenthesis = 1;
		while (parenthesis != 0) {
			if (!scanner.hasNext()) {
				throw new InvalidMethodStructureException();
			}
			if (line.matches(".+//.*")) {
				throw new InvalidLineStructureException();
			}
			line = scanner.nextLine().trim();
			if (line.isEmpty() || line.startsWith("//")) {
				continue;
			}
			if (line.endsWith("{")) {
				parenthesis++;
			} else if (line.equals("}")) {
				parenthesis--;
			} else if (line.startsWith("void")) {
				throw new InvalidMethodStructureException();
			}
			curMethod.add(line);
		}
		String hasReturn = curMethod.get(curMethod.size() - 2);
		if (hasReturn.matches("return\\s*;")) {
			methodsImplementation.add(curMethod);
			return;
		}
		throw new InvalidMethodStructureException();
	}

	private boolean parseGlobalVariables(String line) throws VariableException {
		if (PatternsKit.findPattern(line, PatternsKit.validDecString) || PatternsKit.findPattern(line,
																								 PatternsKit.validAssString)) {
			VariableParser.mainVariableParser(line, globalVarsMap, new HashMap<>());
			return true;
		}
		return false;
	}

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
		String[] eachParam = allParams.split(",", -1);
		ArrayList<Type> typesOfParameters = new ArrayList<>();
		for (String s : eachParam) {
			Matcher paramListMatcher = PatternsKit.returnFindMatcher(s, PatternsKit.paramListString);
			if (paramListMatcher == null) {
				throw new InvalidMethodStructureException();
			}
			Type paramType = TypesFactory.createTypes(paramListMatcher.group(2));
			paramType.nameVerifier(paramListMatcher.group(3), new HashMap<>(), new HashMap<>(), true);
			typesOfParameters.add(paramType);
		}
		return typesOfParameters;
	}
}
