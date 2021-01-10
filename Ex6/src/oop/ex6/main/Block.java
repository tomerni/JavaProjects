package oop.ex6.main;

import oop.ex6.variables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Class for the scopes in the file - methods, ifs and whiles
 */
public class Block {

	// HashMap with the scope's local variables
	private final HashMap<String, String[]> fatherHash;

	// HashMap with the scope's father local variables
	private final HashMap<String, String[]> curHash;

	// the line that are relevant to the scope
	private ArrayList<String> scopeLines;

	// flag the indicates if the scope is a method
	private final boolean isMethod;

	private final static String TRUE = "true";

	private final static String FALSE = "false";

	private final static String RETURN_REGEX = "return\\s*;";

	private final static String SEPARATOR_REGEX = "&&|\\|\\|";

	private final static String PARAMS_DELIMITER = ",";

	private final static String METHOD_CLOSING = "}";

	private final static int FINAL_INDEX = 1;

	private final static int TYPE_INDEX = 2;

	private final static int NAME_INDEX = 3;

	/**
	 * constructor
	 * @param scopeLines the lines from the start of the scope until the end of its father
	 * @param fatherHash the scope's father HashMap
	 * @param isMethod flag that indicates if the scope is method
	 */
	Block(ArrayList<String> scopeLines, HashMap<String, String[]> fatherHash, boolean isMethod) {
		curHash = new HashMap<>();
		this.fatherHash = fatherHash;
		this.scopeLines = scopeLines;
		this.isMethod = isMethod;
	}

	/**
	 * iterates over the lines in the scope until finds a }
	 * @return the lines that are not in the scope
	 * @throws VariableException variable exception
	 * @throws MethodException method exception
	 * @throws StructureException structure exception
	 */
	public ArrayList<String> parseScope() throws VariableException, MethodException, StructureException {
		Iterator<String> iter = scopeLines.iterator();
		validateFormat(iter.next());
		while (iter.hasNext()) {
			String line = iter.next().trim();
			if (parseVariables(line)) {
				continue;
			}
			if (PatternsKit.matchPattern(line, PatternsKit.ifWhileString)) {
				HashMap<String, String[]> innerBlockHash = new HashMap<>();
				innerBlockHash.putAll(fatherHash);
				innerBlockHash.putAll(curHash);
				ArrayList<String> remainingLines = new ArrayList<>();
				iter.forEachRemaining(remainingLines::add);
				remainingLines.add(0, line);
				Block innerBlock = new Block(remainingLines, innerBlockHash, false);
				scopeLines = innerBlock.parseScope();
				iter = scopeLines.iterator();
				continue;
			}
			if (MethodCallParser.methodCallParser(line, GlobalParser.methodMap, curHash, fatherHash)) {
				continue;
			}
			if (line.matches(RETURN_REGEX)) {
				continue;
			}
			if (line.equals(METHOD_CLOSING)) {
				break;
			}
			throw new InvalidLineStructureException();
		}
		ArrayList<String> remainingLines = new ArrayList<>();
		iter.forEachRemaining(remainingLines::add);
		return remainingLines;
	}

	/*
	 * parses variable line in the block
	 */
	private boolean parseVariables(String line) throws VariableException {
		if (PatternsKit.findPattern(line, PatternsKit.validDecString) ||
			PatternsKit.findPattern(line, PatternsKit.validAssString)) {
			VariableParser.mainVariableParser(line, curHash, fatherHash);
			return true;
		}
		return false;
	}

	/*
	 * validates the format
	 */
	private void validateFormat(String line) throws VariableException, StructureException {
		if (!isMethod) {
			Matcher conditionMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.conditionParenString);
			if (conditionMatcher == null) {
				throw new IllegalConditionStructure();
			}
			String conditions = conditionMatcher.group(1);
			String[] eachCondition = conditions.split(SEPARATOR_REGEX, -1);
			Type booleanType = new BooleanType();
			for (String s : eachCondition) {
				String currentValue = s.trim();
				booleanType.valueVerifier(currentValue, curHash, fatherHash);
			}
		} else {
			methodFormatValidate(line);
		}
	}

	/*
	 * validate the method format
	 */
	private void methodFormatValidate(String line) throws StructureException, VariableException {
		Matcher allParamMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.methodParenString);
		if (allParamMatcher == null) {
			throw new InvalidMethodStructureException();
		}
		String allParams = allParamMatcher.group(1).trim();
		if (allParams.isEmpty()) {
			return;
		}
		String[] eachParam = allParams.split(PARAMS_DELIMITER, -1);
		for (String s : eachParam) {
			Matcher paramListMatcher = PatternsKit.returnMatchesMatcher(s, PatternsKit.paramListString);
			if (paramListMatcher == null) {
				throw new InvalidMethodStructureException();
			}
			String varName = paramListMatcher.group(NAME_INDEX);
			if (curHash.containsKey(varName)) {
				throw new IllegalVarNameException();
			}
			String isFinal = TRUE;
			if (paramListMatcher.group(FINAL_INDEX) == null) {
				isFinal = FALSE;
			}
			String[] varArray = {paramListMatcher.group(TYPE_INDEX), TRUE, isFinal};
			curHash.put(varName, varArray);
		}
	}
}
