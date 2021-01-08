package oop.ex6.main;

public class IllegalArgumentsException extends MethodException{

	private static final long serialVersionUID = 1L;

	IllegalArgumentsException(){
		super("ERROR: Illegal number of arguments");
	}

}
