package oop.ex6.variables;

/**
 * Factory class for types
 */
public class TypesFactory {

    private final static String BOOLEAN = "boolean";

    private final static String INT = "int";

    private final static String DOUBLE = "double";

    private final static String STRING = "String";

    /**
     * factory method for types
     * @param type the given type
     * @return the relevant Type object
     */
    public static Type createTypes(String type) {
        switch (type){
            case INT:
                return new IntType();
            case STRING:
                return new StringType();
            case DOUBLE:
                return new DoubleType();
            case BOOLEAN:
                return new BooleanType();
            default:
                return new CharType();
        }
    }
}
