package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for all of the regex patterns
 */
public class PatternsKit {

	/** pattern for double variable */
	public final static Pattern doubleTypeString = Pattern.compile("[+-]?([\\d]*[.])?[\\d]+");

	/** pattern for char variable */
	public final static Pattern charTypeString = Pattern.compile("'[^\",'\\\\]?'");

	/** pattern for int variable */
	public final static Pattern intTypeString = Pattern.compile("[+-]?[\\d]+");

	/** pattern for string variable */
	public final static Pattern stringTypeString = Pattern.compile("\"[^\",'\\\\]*\"");

	/** pattern for valid name */
	public final static Pattern validNameString = Pattern.compile("^_[\\w]+|^[a-zA-Z][\\w]*");

	/** pattern for final variable */
	public final static Pattern finalString = Pattern.compile("^\\s*final\\s+");

	/** pattern for valid type */
	public final static Pattern validTypeString = Pattern.compile("^\\s*(int|boolean|double|char|String)" +
																  "\\s+");

	/** pattern for variable declaration */
	public final static Pattern validDecString = Pattern.compile("^(final\\s)?\\s*" +
																 "(int|boolean|double|char|String)\\s+[^;" +
																 "]*;" +
																 "\\s*");

	/** pattern for variable assignment */
	public final static Pattern validAssString = Pattern.compile("^[\\w]+\\s*=\\s*[\\w]+\\s*;\\s*");

	/** pattern for condition parenthesis */
	public final static Pattern conditionParenString = Pattern.compile("\\((.+)\\)");

	/** pattern for method parenthesis */
	public final static Pattern methodParenString = Pattern.compile("\\(([\\w\\s,]*)\\)");

	/** pattern for parameter list */
	public final static Pattern paramListString = Pattern
			.compile("\\s*(final\\s)?\\s*(int|boolean|char|double|String)\\s+([\\w]+)\\s*");

	/** pattern for method declaration */
	public final static Pattern methodDecString = Pattern.compile("^\\s*void\\s+[\\w]+\\s*\\([^;(){}]*\\)" +
																  "\\s*\\{\\s*");

	/** pattern for void declaration */
	public final static Pattern voidString = Pattern.compile("^\\s*void\\s+");

	/** pattern for method name */
	public final static Pattern methodNameString = Pattern.compile("^[a-zA-Z][\\w]*[\\s]*\\(");

	/** pattern for method arguments */
	public final static Pattern methodArgsString = Pattern.compile("[^=]+?([=].+?)?[,)]");

	/** pattern for if and while blocks */
	public final static Pattern ifWhileString = Pattern.compile("(if|while)\\s*\\(.*\\)\\s*\\{");


	/**
	 * finds the pattern in the given line
	 * @param line the given line
	 * @param p the pattern
	 * @return true if the pattern is in the string, false otherwise
	 */
	public static boolean findPattern(String line, Pattern p) {
		Matcher m = p.matcher(line);
		return m.find();
	}

	/**
	 * matches the pattern in the given line
	 * @param line the given line
	 * @param p the pattern
	 * @return true if the pattern is the string, false otherwise
	 */
	public static boolean matchPattern(String line, Pattern p) {
		Matcher m = p.matcher(line);
		return m.matches();
	}

	/**
	 * executes the pattern on the matcher
	 * @param line the given line
	 * @param p the pattern
	 * @return the Matcher object for the find, null otherwise
	 */
	public static Matcher returnFindMatcher(String line, Pattern p) {
		Matcher m = p.matcher(line);
		boolean isFound = m.find();
		if (isFound) {
			return m;
		}
		return null;
	}

	/**
	 * executes the pattern on the matcher
	 * @param line the given line
	 * @param p the pattern
	 * @return the Matcher object for the find, null otherwise
	 */
	public static Matcher returnMatchesMatcher(String line, Pattern p) {
		Matcher m = p.matcher(line);
		boolean isFound = m.matches();
		if (isFound) {
			return m;
		}
		return null;
	}
}
