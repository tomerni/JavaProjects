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
import java.util.regex.Pattern;

public class GlobalParser {

	public static HashMap<String, ArrayList<Type>> methodMap;

	private HashMap<String, String[]> globalVarsMap;

	private ArrayList<ArrayList<String>> methodsImplementation;

	private Scanner scanner;


	GlobalParser(String fileName) throws FileNotFoundException {
		methodMap = new HashMap<>();
		globalVarsMap = new HashMap<>();
		methodsImplementation = new ArrayList<>();
		scanner = new Scanner(new FileReader(fileName));
	}

	public void fileParse() throws StructureException, VariableException, MethodException {
		while (scanner.hasNext()) {
			String line = (scanner.nextLine());
			if (line.matches(".+//.*")){
				throw new InvalidLineStructureException();
			}
			line = line.trim();
			if(line.isEmpty() || line.startsWith("//")){
				continue;
			}
			if (!parseGlobalVariables(line)) {
				Pattern methodDecPattern = Pattern.compile("^\\s*void\\s+[\\w]+\\s*\\([^;(){}]*\\)" +
														   "\\s*\\{\\s*");
				Matcher methodDecMatcher = methodDecPattern.matcher(line);
				boolean methodDecFound = methodDecMatcher.find();
				if (!methodDecFound) {
					throw new InvalidLineStructureException();
				}
				buildMethodString(line);
			}
		}
		methodParsing();
	}

	private void methodParsing() throws VariableException, MethodException, StructureException {
		for(ArrayList<String> list : methodsImplementation){
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
			if (line.matches(".+//.*")){
				throw new InvalidLineStructureException();
			}
			line = scanner.nextLine().trim();
			if(line.isEmpty() || line.startsWith("//")){
				continue;
			}
			if (line.endsWith("{")) { // TODO: Change condition to something that can capture spaces
				parenthesis++;
			} else if (line.equals("}")) {
				parenthesis--;
			} else if (line.startsWith("void")) {
				throw new InvalidMethodStructureException();
			}
			curMethod.add(line);
		}
		String hasReturn = curMethod.get(curMethod.size() - 2);
		if(hasReturn.matches("return\\s*;")){
			methodsImplementation.add(curMethod);
			return;
		}
		throw new InvalidMethodStructureException();
	}

	private boolean parseGlobalVariables(String line) throws VariableException {
		Pattern varInitPattern = Pattern.compile("^(final\\s)?\\s*(int|boolean|double|char|String)\\s+[^;]*;\\s*");
		Matcher varInitMatcher = varInitPattern.matcher(line);
		boolean varInitFound = varInitMatcher.find();
		Pattern varAssignPattern = Pattern.compile("^[\\w]+\\s*=\\s*[\\w]+\\s*;\\s*");
		Matcher varAssignMatcher = varAssignPattern.matcher(line);
		boolean varAssignFound = varAssignMatcher.find();
		if (varInitFound || varAssignFound) {
			VariableParser.mainVariableParser(line, globalVarsMap, new HashMap<>());
			return true;
		}
		return false;
	}

	private void signatureVerifier(String line) throws StructureException, VariableException {
		Pattern voidPattern = Pattern.compile("^\\s*void\\s+");
		Matcher voidMatcher = voidPattern.matcher(line);
		boolean voidFound = voidMatcher.find();
		line = line.substring(voidMatcher.end());

		Pattern namePattern = Pattern.compile("^_[\\w]+[\\s]*\\(|^[a-zA-Z][\\w]*[\\s]*\\(");
		Matcher nameMatcher = namePattern.matcher(line);
		boolean matchFound = nameMatcher.find();
		if(!matchFound){
			throw new InvalidMethodStructureException();
		}
		String methodName = line.substring(nameMatcher.start(), nameMatcher.end() - 1);
		if (methodMap.containsKey(methodName)){
			throw new InvalidMethodStructureException();
		}
		line = (line.substring((nameMatcher.end() - 1))).trim();
		ArrayList<Type> typesOfParameters = parameterListVerifier(line);
		methodMap.put(methodName, typesOfParameters);
	}


	private ArrayList<Type> parameterListVerifier(String listOfParameters)
			throws StructureException, VariableException {
		Pattern allParamsPattern = Pattern.compile("\\(([\\w\\s,]*)\\)");
		Matcher allParamMatcher = allParamsPattern.matcher(listOfParameters);
		boolean allParamFound = allParamMatcher.find();
		if(!allParamFound) {
			throw new InvalidMethodStructureException();
		}
		String allParams = (allParamMatcher.group(1)).trim();

		if (allParams.isEmpty()) {
			return new ArrayList<>();
		}
		String[] eachParam = allParams.split(",", -1);
		ArrayList<Type> typesOfParameters = new ArrayList<>();
		for (String s : eachParam) {
			Pattern paramListPattern = Pattern.compile("\\s*(final\\s)?\\s*(int|boolean|char|double|String)" +
													   "\\s+([\\w]+)\\s*");
			Matcher paramListMatcher = paramListPattern.matcher(s);
			boolean paramFound = paramListMatcher.find();
			if (!paramFound) {
				throw new InvalidMethodStructureException();
			}
			Type paramType = TypesFactory.createTypes(paramListMatcher.group(2));
			paramType.nameVerifier(paramListMatcher.group(3), new HashMap<>(), new HashMap<>(), true);
			typesOfParameters.add(paramType);
		}
		return typesOfParameters;
	}
}
