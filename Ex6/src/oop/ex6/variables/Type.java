package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;


public interface Type {


	String TRUE = "true";

	String FALSE = "false";

	String BOOLEAN = "boolean";

	String INT = "int";

	String DOUBLE = "double";

	String CHAR = "char";

	String STRING = "String";


	default void nameVerifier(String name, HashMap<String, String[]> curHash,
							  HashMap<String, String[]> fatherHash, boolean isDec) throws VariableException {
		if (PatternsKit.matchPattern(name, PatternsKit.validNameString) && ((!curHash.containsKey(name) && isDec) || (curHash.containsKey(name) && !isDec) ||
						   (!curHash.containsKey(name) && !isDec && fatherHash.containsKey(name)))) {
			return;
		}
		throw new IllegalVarNameException();
	}

	void valueVerifier(String value, HashMap<String, String[]> curHash,
					   HashMap<String, String[]> fatherHash) throws VariableException;

	default boolean searchForAssignedValue(String value, HashMap<String, String[]> curHash) {
		if (curHash.containsKey(value)) {
			return (curHash.get(value)[1].equals(TRUE));
		}
		return false;
	}

	default String searchForType(String value, HashMap<String, String[]> curHash,
								 HashMap<String, String[]> fatherHash) throws VariableException{
		boolean isAssignedCur = searchForAssignedValue(value, curHash);
		boolean isAssignedFather = searchForAssignedValue(value, fatherHash);
		if(!(isAssignedCur || isAssignedFather)) {
			throw new UsageOfNonAssignedException();
		}
		String assignedValueType;
		if(isAssignedCur) {
			assignedValueType = curHash.get(value)[0];
		} else {
			assignedValueType = fatherHash.get(value)[0];
		}
		return assignedValueType;
	}

	String getTypeName();
}
