package oop.ex6.main;

public class IllegalMethodCallException extends MethodException {


	private static final long serialVersionUID = 1L;

	IllegalMethodCallException(){
		super("ERROR: Invalid method name");
	}
}
