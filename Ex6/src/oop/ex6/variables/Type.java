package oop.ex6.variables;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface Type {

	// TODO: Implement a class for each pattern
	default void nameVerifier(String name, HashMap<String, String[]> curHash,
							  HashMap<String, String[]> fatherHash, boolean isDec) throws RuntimeException {
		Pattern namePattern = Pattern.compile("^_[\\w]+|^[a-zA-Z][\\w]*");
		Matcher nameMatcher = namePattern.matcher(name);
		boolean matchFound = nameMatcher.matches();
		if (matchFound && ((!curHash.containsKey(name) && isDec) || (curHash.containsKey(name) && !isDec) ||
						   (!curHash.containsKey(name) && !isDec && fatherHash.containsKey(name)))) {
			return;
		}
		throw new RuntimeException();
	}

	void valueVerifier(String value, HashMap<String, String[]> curHash,
					   HashMap<String, String[]> fatherHash) throws RuntimeException;

	default boolean searchForAssignedValue(String value, HashMap<String, String[]> curHash) {
		if (curHash.containsKey(value)) {
			return (curHash.get(value)[1].equals("true"));
		}
		return false;
	}

	default String searchForType(String value, HashMap<String, String[]> curHash,
								 HashMap<String, String[]> fatherHash) throws RuntimeException{
		boolean isAssignedCur = searchForAssignedValue(value, curHash);
		boolean isAssignedFather = searchForAssignedValue(value, fatherHash);
		if(!(isAssignedCur || isAssignedFather)) {
			throw new RuntimeException();
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
