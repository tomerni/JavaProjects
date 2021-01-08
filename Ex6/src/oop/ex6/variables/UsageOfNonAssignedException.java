package oop.ex6.variables;

public class UsageOfNonAssignedException extends VariableException{

	private static final long serialVersionUID = 1L;

	UsageOfNonAssignedException(){
		super("ERROR: usage of a non-assigned variable");
	}
}
