package oop.ex6.variables;

public class IllegalValueAssignmentException extends VariableException{

	private static final long serialVersionUID = 1L;

	IllegalValueAssignmentException() {
		super("ERROR: illegal value assignment");
	}
}
