package oop.ex6.variables;

public class TypesFactory {

    public static Type createTypes(String type) {
        switch (type){
            case "int":
                return new IntType();
            case "String":
                return new StringType();
            case "double":
                return new DoubleType();
            case "boolean":
                return new BooleanType();
            default:
                return new CharType();
        }
    }
}
