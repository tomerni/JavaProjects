package oop.ex6.main;

/**
 * Common interface for the Block and GlobalParse. Holds The constants.
 */
public interface Scope {

	String TRUE = "true";

	String FALSE = "false";

	String RETURN_REGEX = "return\\s*;";

	String SEPARATOR_REGEX = "&&|\\|\\|";

	String PARAMS_DELIMITER = ",";

	String METHOD_CLOSING = "}";

	int FINAL_INDEX = 1;

	int TYPE_INDEX = 2;

	int NAME_INDEX = 3;

	String INVALID_COMMENT = ".+//.*";

	String VALID_COMMENT = "//";

	String VOID = "void";

	String METHOD_OPENING = "{";
}
