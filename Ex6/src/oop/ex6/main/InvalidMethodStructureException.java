package oop.ex6.main;

public class InvalidMethodStructureException extends StructureException{

	private static final long serialVersionUID = 1L;

	InvalidMethodStructureException(){
		super("ERROR: Invalid method structure");
	}
}
