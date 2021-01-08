package oop.ex6.variables;

public class FinalAssignmentException extends VariableException{

	private static final long serialVersionUID = 1L;

	FinalAssignmentException(){
		super("ERROR: assignment to final variable");
	}
}
