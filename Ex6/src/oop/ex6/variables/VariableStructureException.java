package oop.ex6.variables;

public class VariableStructureException extends VariableException{

	private static final long serialVersionUID = 1L;

	VariableStructureException(){
		super("ERROR: Invalid variable assignment or declaration line structure");
	}

}
