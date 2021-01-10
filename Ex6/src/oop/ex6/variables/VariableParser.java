package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * Class that responsible for parsing variable statements
 */
public class VariableParser {

	private final static String TRUE = "true";

	private final static String FALSE = "false";

	private final static String PARAMS_DELIMITER = ",";

	private final static String EQUALS = "=";

	private final static int NAME_INDEX = 0;

	private final static int VALUE_INDEX = 1;

	private final static int ASSIGNMENT_LENGTH = 2;

	private final static int FINAL_INDEX = 2;

	/**
	 * the main function which responsible for parsing the variable lines
	 * @param line variable line
	 * @param curHash the current scope's HashMap
	 * @param fatherHash the scope's father HashMap
	 * @throws VariableException variable exception
	 */
	public static void mainVariableParser(String line, HashMap<String, String[]> curHash, HashMap<String
			, String[]> fatherHash) throws VariableException {
		Matcher finalMatcher = PatternsKit.finalString.matcher(line);
		boolean finalFound = finalMatcher.find();
		if (finalFound) {
			line = line.substring(finalMatcher.end());
		}
		Matcher typeMatcher = PatternsKit.validTypeString.matcher(line);
		boolean decFound = typeMatcher.find();
		if (decFound) {
			String type = typeMatcher.group(1);
			line = line.substring(typeMatcher.end(), line.length() - 1);
			if (finalFound) {
				decParser(line, type, curHash, fatherHash, TRUE);
			} else {
				decParser(line, type, curHash, fatherHash, FALSE);
			}
		} else {
			if (!line.contains(EQUALS) && finalFound) {
				throw new FinalAssignmentException();
			} else {
				assignmentParser(line, curHash, fatherHash);
			}
		}
	}

	/*
	 * handles declaration lines
	 */
	private static void decParser(String line, String type, HashMap<String, String[]> curHash,
								  HashMap<String, String[]> fatherHash, String isFinal)
			throws VariableException {
		Type valType = TypesFactory.createTypes(type);
		String[] spiltString = line.split(PARAMS_DELIMITER, -1);
		for (String s : spiltString) {
			if (s.isEmpty()) {
				throw new VariableStructureException();
			}
			if (s.contains(EQUALS)) {
				String[] splitLine = s.split(EQUALS);
				if (splitLine.length != ASSIGNMENT_LENGTH) {
					throw new VariableStructureException();
				}
				String name = splitLine[NAME_INDEX].trim();
				String value = splitLine[VALUE_INDEX].trim();
				valType.nameVerifier(name, curHash, fatherHash, true);
				valType.valueVerifier(value, curHash, fatherHash);
				String[] varArray = {valType.getTypeName(), TRUE, isFinal};
				curHash.put(name, varArray);
			} else {
				if (isFinal.equals(TRUE)) {
					throw new FinalAssignmentException();
				}
				String name = s.trim();
				valType.nameVerifier(name, curHash, fatherHash, true);
				String[] varArray = {valType.getTypeName(), FALSE, isFinal};
				curHash.put(name, varArray);
			}
		}
	}

	/*
	 * handles assignment lines
	 */
	private static void assignmentParser(String line, HashMap<String, String[]> curHash, HashMap<String,
			String[]> fatherHash) throws VariableException {
		String[] splitLine = line.split(EQUALS);
		if (splitLine.length != ASSIGNMENT_LENGTH) {
			throw new VariableStructureException();
		}
		String name = splitLine[NAME_INDEX].trim();
		String value = splitLine[VALUE_INDEX].substring(0, splitLine[1].length() - 1).trim();
		HashMap<String, String[]> foundHash;
		if (curHash.containsKey(name)) {
			foundHash = curHash;
		} else {
			if (fatherHash.containsKey(name)) {
				foundHash = fatherHash;
			} else {
				throw new IllegalVarNameException();
			}
		}
		String type = foundHash.get(name)[0];
		String isFinal = foundHash.get(name)[FINAL_INDEX];
		if (isFinal.equals(TRUE)) {
			throw new FinalAssignmentException();
		}
		Type valType = TypesFactory.createTypes(type);
		valType.valueVerifier(value, curHash, fatherHash);
		String[] varArray = {foundHash.get(name)[NAME_INDEX], TRUE, foundHash.get(name)[FINAL_INDEX]};
		foundHash.put(name, varArray);
	}

}
