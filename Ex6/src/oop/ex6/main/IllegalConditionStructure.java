package oop.ex6.main;

public class IllegalConditionStructure extends StructureException {

	private static final long serialVersionUID = 1L;

	IllegalConditionStructure(){
		super("ERROR: Illegal condition structure");
	}
}
