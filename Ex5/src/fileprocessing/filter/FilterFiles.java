package fileprocessing.filter;

import java.io.File;

/**
 * abstract class for filtering the files
 */
public abstract class FilterFiles {

    /**
     * filter the files in the path according to the given filter
     * @param directoryPath the directory path
     * @param filterLine the string of the filter line
     * @return list of the files that matched the filter
     * @throws BadFilterException if the validate function throws an exception, it passes it to her caller
     */
    public static File[] filter(String directoryPath, String filterLine) throws BadFilterException {
        String[] splitString = filterLine.split("#");
        String typeOfFilter = splitString[0];
        boolean flag = true;
        if (splitString[splitString.length - 1].equals("NOT")) {
            flag = false;
        }
        Filter curFilter = FilterFactory.createFilter(typeOfFilter, splitString, flag);
        curFilter.validate(); // CAN THROW EXCEPTION
        File directory = new File(directoryPath);
        return directory.listFiles(curFilter);
    }
}
