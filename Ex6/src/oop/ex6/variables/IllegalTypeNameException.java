package oop.ex6.variables;

public class IllegalTypeNameException extends VariableException{

	private static final long serialVersionUID = 1L;

	IllegalTypeNameException(){
		super("ERROR: illegal type name");
	}
}
