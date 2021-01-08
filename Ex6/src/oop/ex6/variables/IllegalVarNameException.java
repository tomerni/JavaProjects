package oop.ex6.variables;

public class IllegalVarNameException extends VariableException {

	private static final long serialVersionUID = 1L;

	public IllegalVarNameException(){
		super("ERROR: illegal variable name");
	}
}
