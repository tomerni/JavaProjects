package oop.ex6.main;

public class InvalidLineStructureException  extends StructureException{

	private static final long serialVersionUID = 1L;

	InvalidLineStructureException(){
		super("ERROR: Invalid line");
	}

}
