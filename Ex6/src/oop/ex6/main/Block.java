package oop.ex6.main;


import oop.ex6.variables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;

public class Block {

	private final HashMap<String, String[]> fatherHash;

	private final HashMap<String, String[]> curHash;

	private ArrayList<String> scopeLines;

	private final boolean isMethod;

	Block(ArrayList<String> scopeLines, HashMap<String, String[]> fatherHash, boolean isMethod) {
		curHash = new HashMap<>();
		this.fatherHash = fatherHash;
		this.scopeLines = scopeLines;
		this.isMethod = isMethod;
	}

	public ArrayList<String> parseScope()
			throws VariableException, MethodException, StructureException {
		Iterator<String> iter = scopeLines.iterator();
		validateFormat(iter.next());
		while (iter.hasNext()) {
			String line = iter.next().trim();
			if (parseVariables(line)) {
				continue;
			}

			if ((line.startsWith("if ") || line.startsWith("if(") || line.startsWith("while ") || line.startsWith("while(")) && line.endsWith("{")) {
				HashMap<String, String[]> innerBlockHash = new HashMap<>();
				innerBlockHash.putAll(fatherHash);
				innerBlockHash.putAll(curHash);
				ArrayList<String> remainingLines = new ArrayList<>();
				iter.forEachRemaining(remainingLines::add);
				remainingLines.add(0,line);
				Block innerBlock = new Block(remainingLines, innerBlockHash, false);
				scopeLines = innerBlock.parseScope();
				iter = scopeLines.iterator();
				continue;
			}
			if (MethodCallParser.methodCallParser(line, GlobalParser.methodMap, curHash, fatherHash)) {
				continue;
			}
			if (line.matches("return\\s*;")) {
				continue;
			}
			if (line.equals("}")) {
				break;
			}
			throw new InvalidLineStructureException();
		}
		ArrayList<String> remainingLines = new ArrayList<>();
		iter.forEachRemaining(remainingLines::add);
		return remainingLines;
	}


	private boolean parseVariables(String line) throws VariableException {
		if (PatternsKit.findPattern(line, PatternsKit.validDecString) || PatternsKit.findPattern(line,
																								 PatternsKit.validAssString)) {
			VariableParser.mainVariableParser(line, curHash, fatherHash);
			return true;
		}
		return false;
	}

	private void validateFormat(String line) throws VariableException, StructureException {
		if (!isMethod) {
			Matcher conditionMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.conditionParenString);
			if(conditionMatcher == null){
				throw new IllegalConditionStructure();
			}
			String conditions = conditionMatcher.group(1);
			String[] eachCondition = conditions.split("&&|\\|\\|", -1);
			Type booleanType = new BooleanType();
			for (String s : eachCondition) {
				String currentValue = s.trim();
				booleanType.valueVerifier(currentValue, curHash, fatherHash);
			}
		} else {
			methodFormatValidate(line);
		}
	}

	private void methodFormatValidate(String line) throws StructureException, VariableException {
		Matcher allParamMatcher = PatternsKit.returnFindMatcher(line, PatternsKit.methodParenString);

		if (allParamMatcher == null) {
			throw new InvalidMethodStructureException();
		}
		String allParams = allParamMatcher.group(1).trim();
		if (allParams.isEmpty()) {
			return;
		}
		String[] eachParam = allParams.split(",", -1);
		for (String s : eachParam) {
			Matcher paramListMatcher = PatternsKit.returnMatchesMatcher(s, PatternsKit.paramListString);

			if (paramListMatcher == null) {
				throw new InvalidMethodStructureException();
			}
			String varName = paramListMatcher.group(3);
			if(curHash.containsKey(varName)){
				throw new IllegalVarNameException();
			}
			String isFinal = "true";
			if (paramListMatcher.group(1) == null) {
				isFinal = "false";
			}
			String[] varArray = {paramListMatcher.group(2), "true", isFinal};
			curHash.put(varName, varArray);
		}
	}
}
