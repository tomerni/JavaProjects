package fileprocessing;

import fileprocessing.filter.BadFilterException;
import fileprocessing.filter.FilterFiles;
import fileprocessing.order.BadOrderNameException;
import fileprocessing.order.OrderFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * abstract class that processes a section
 */

public abstract class SectionProcessor {

    // Warning message
    private final static String WARNING_MESSAGE = "Warning in line %d";

    // indicates if a filter exception occurred
    private static boolean filterException = false;

    // indicates if a order exception occurred
    private static boolean orderException = false;

    /**
     * process the section using processFilter and processOrder
     * @param lineNumber the line number of the filter line
     * @param path the path of the folder
     * @param filter the filter line
     * @param order the order line
     * @return ArrayList with the files that matched the filter, in the given order. Also has the warnings
     *         if they happened.
     */
    public static ArrayList<String> processSection(int lineNumber, String path, String filter, String order) {
        orderException = false;
        filterException = false;
        File[] filteredFiles = processFilter(path, filter);
        ArrayList<File> filteredFilesArr = new ArrayList<>(Arrays.asList(filteredFiles));
        ArrayList<String> orderedFiles = processOrder(filteredFilesArr, order);
        if (orderException) {
            orderedFiles.add(0, String.format(WARNING_MESSAGE, lineNumber + 2));
        }
        if (filterException) {
            orderedFiles.add(0, String.format(WARNING_MESSAGE, lineNumber));
        }
        return orderedFiles;
    }

    /*
     * filters the files in the path using the given filter. Handles BadFilterException.
     */
    private static File[] processFilter(String path, String filter) {
        try {
            return FilterFiles.filter(path, filter);
        } catch (BadFilterException e) {
            filterException = true;
            File directory = new File(path);
            return directory.listFiles(File::isFile);
        }
    }

    /*
     * orders the files in the path using the given order. Handles BadOrderException.
     */
    private static ArrayList<String> processOrder(ArrayList<File> filtered, String order) {
        try {
            return OrderFiles.order(filtered, order);
        } catch (BadOrderNameException e) {
            orderException = true;
            try {
                return OrderFiles.order(filtered, "abs");
            } catch (BadOrderNameException f) { // CAN'T HAPPEN
                return new ArrayList<>();
            }
        }
    }
}
