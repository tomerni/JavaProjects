package oop.ex6.variables;

public class DuplicateVariableException extends VariableException {

	private static final long serialVersionUID = 1L;

	DuplicateVariableException(){
		super("ERROR: duplicate variable name in the same scope");
	}
}
