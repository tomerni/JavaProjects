package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;
import java.util.regex.Matcher;

public class VariableParser {

	private final static String TRUE = "true";

	private final static String FALSE = "false";

	public static void mainVariableParser(String line, HashMap<String, String[]> curHash, HashMap<String
			, String[]> fatherHash) throws VariableException{
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
			if (!line.contains("=") && finalFound) {
				throw new FinalAssignmentException();
			} else {
				assignmentParser(line, curHash, fatherHash);
			}
		}
	}

	private static void decParser(String line, String type, HashMap<String, String[]> curHash,
								  HashMap<String, String[]> fatherHash, String isFinal) throws VariableException{
		Type valType = TypesFactory.createTypes(type);
		String[] spiltString = line.split(",", -1);
		for (String s : spiltString) {
			if(s.isEmpty()){
				throw new VariableStructureException();
			}
			if (s.contains("=")) {
				String[] splitLine = s.split("=");
				if (splitLine.length != 2) {
					throw new VariableStructureException();
				}
				String name = splitLine[0].trim();
				String value = splitLine[1].trim();
				valType.nameVerifier(name, curHash, fatherHash, true);
				valType.valueVerifier(value, curHash, fatherHash);
				String[] varArray = {valType.getTypeName(), TRUE, isFinal};
				curHash.put(name, varArray);
			} else {
				if (isFinal.equals(TRUE)) {
					throw new FinalAssignmentException();
				}
				String name = s.trim();
				valType.nameVerifier(name, curHash,fatherHash, true);
				String[] varArray = {valType.getTypeName(), FALSE, isFinal};
				curHash.put(name, varArray);
			}
		}
	}

	private static void assignmentParser(String line, HashMap<String, String[]> curHash, HashMap<String,
			String[]> fatherHash) throws VariableException {
		String[] splitLine = line.split("=");
		if (splitLine.length != 2) {
			throw new VariableStructureException();
		}
		String name = splitLine[0].trim();
		String value = splitLine[1].substring(0, splitLine[1].length() - 1).trim();
		HashMap<String, String[]> foundHash;
		if (curHash.containsKey(name)){
			foundHash = curHash;
		} else{
			if(fatherHash.containsKey(name)){
				foundHash = fatherHash;
			} else{
				throw new IllegalVarNameException();
			}
		}
		String type = foundHash.get(name)[0];
		String isFinal = foundHash.get(name)[2];
		if (isFinal.equals(TRUE)) {
			throw new FinalAssignmentException();
		}
		Type valType = TypesFactory.createTypes(type);
		valType.valueVerifier(value, curHash, fatherHash);
		String[] varArray = {foundHash.get(name)[0], TRUE, foundHash.get(name)[2]};
		foundHash.put(name, varArray);
	}

}
