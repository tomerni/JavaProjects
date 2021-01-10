package oop.ex6.variables;

import oop.ex6.main.PatternsKit;

import java.util.HashMap;

/**
 * interface for the types in the Sjava language
 */
public interface Type {

	String TRUE = "true";

	String FALSE = "false";

	String BOOLEAN = "boolean";

	String INT = "int";

	String DOUBLE = "double";

	String CHAR = "char";

	String STRING = "String";

	/**
	 * checks if the given name is valid
	 * @param name the given name
	 * @param curHash the current HashMap of the scope
	 * @param fatherHash the father's scope HashMap
	 * @param isDec flag that indicates if the is a declaration
	 * @throws VariableException variable exception
	 */
	default void nameVerifier(String name, HashMap<String, String[]> curHash,
							  HashMap<String, String[]> fatherHash, boolean isDec) throws VariableException {
		if (PatternsKit.matchPattern(name, PatternsKit.validNameString) &&
			((!curHash.containsKey(name) && isDec) || (curHash.containsKey(name) && !isDec) ||
			 (!curHash.containsKey(name) && !isDec && fatherHash.containsKey(name)))) {
			return;
		}
		throw new IllegalVarNameException();
	}

	/**
	 * verifies that the value matches the type
	 * @param value the given value
	 * @param curHash the current HashMap of the scope
	 * @param fatherHash the father's scope HashMap
	 * @throws VariableException invalid variable exception
	 */
	void valueVerifier(String value, HashMap<String, String[]> curHash,
					   HashMap<String, String[]> fatherHash) throws VariableException;

	/**
	 * checks if the given value is already assigned
	 * @param value the given value
	 * @param curHash the current HashMap of the scope
	 * @return true if the the var is assigned, false otherwise
	 */
	default boolean searchForAssignedValue(String value, HashMap<String, String[]> curHash) {
		if (curHash.containsKey(value)) {
			return (curHash.get(value)[1].equals(TRUE));
		}
		return false;
	}

	/**
	 * returns the name of the given value
	 * @param value the given value
	 * @param curHash the current HashMap of the scope
	 * @param fatherHash the father's scope HashMap
	 * @return the name of the type of the given value
	 * @throws VariableException invalid variable exception
	 */
	default String searchForType(String value, HashMap<String, String[]> curHash,
								 HashMap<String, String[]> fatherHash) throws VariableException {
		boolean isAssignedCur = searchForAssignedValue(value, curHash);
		boolean isAssignedFather = searchForAssignedValue(value, fatherHash);
		if (!(isAssignedCur || isAssignedFather)) {
			throw new UsageOfNonAssignedException();
		}
		String assignedValueType;
		if (isAssignedCur) {
			assignedValueType = curHash.get(value)[0];
		} else {
			assignedValueType = fatherHash.get(value)[0];
		}
		return assignedValueType;
	}

	/**
	 * @return returns the type name of the class
	 */
	String getTypeName();
}
