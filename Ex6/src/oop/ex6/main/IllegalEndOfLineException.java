package oop.ex6.main;

public class IllegalEndOfLineException extends StructureException{

	private static final long serialVersionUID = 1L;

	IllegalEndOfLineException(){
		super("ERROR: Invalid end of line");
	}
}
