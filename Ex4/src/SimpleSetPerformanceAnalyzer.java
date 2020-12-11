import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * implements the SimpleSetPerformanceAnalyzer class
 */
public class SimpleSetPerformanceAnalyzer {

    // the number of warmup iterations
    private static final int WARMUP_ITERATIONS = 70000;

    // the number of data structures to analyze
    private static final int NUM_OF_DATA_STRUCTURES = 5;

    // data1 string array
    private static final String[] data1 = Ex4Utils.file2array("src\\data1.txt");

    // data2 string array
    private static final String[] data2 = Ex4Utils.file2array("src\\data2.txt");

    // array with the data structures for data1
    private static SimpleSet[] dataSets1 = new SimpleSet[NUM_OF_DATA_STRUCTURES];

    // array with the data structures for data2
    private static SimpleSet[] dataSets2 = new SimpleSet[NUM_OF_DATA_STRUCTURES];

    // CONSTRUCTOR

    /**
     * constructs the arrays for the analyze
     */
    public SimpleSetPerformanceAnalyzer() {
    }

    // PUBLIC METHODS

    /**
     * executes the test. To disable a test just comment it (//)
     * @param args command prompt args
     */
    public static void main(String[] args) {
        initDataSet(dataSets1);
        initDataSet(dataSets2);
        addData(dataSets1, data1);
        containsCheck("hi", dataSets1);
        containsCheck("-13170890158", dataSets1);
        addData(dataSets2, data2);
        containsCheck("23", dataSets2);
        containsCheck("hi", dataSets2);
    }

    // PRIVATE METHODS

    /*
     * init the given array with the data structures
     */
    private static void initDataSet(SimpleSet[] s) {
        s[1] = new ClosedHashSet();
        s[0] = new OpenHashSet();
        s[4] = new CollectionFacadeSet(new LinkedList<String>());
        s[2] = new CollectionFacadeSet(new TreeSet<String>());
        s[3] = new CollectionFacadeSet(new HashSet<String>());
    }

    /*
     * Adds the data from the string array to the data structures in the set
     */
    private static void addData(SimpleSet[] set, String[] data) {
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            long timeBefore = System.nanoTime();
            assert data != null;
            for (String s : data) {
                set[i].add(s);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(difference / 1000000);
        }
    }

    /*
     * performs a contain check for the given word in the given set
     */
    private static void containsCheck(String word, SimpleSet[] set) {
        long timeBefore = 0;
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES - 1; i++) {
            for (int j = 0; j < 2 * WARMUP_ITERATIONS; j++) {
                set[i].contains(word);
                if (j == WARMUP_ITERATIONS) {
                    timeBefore = System.nanoTime();
                }
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(word + " " + difference / WARMUP_ITERATIONS);
        }
        timeBefore = System.nanoTime();
        for (int j = 0; j < (WARMUP_ITERATIONS / 10); j++) {
            set[4].contains(word);
        }
        long difference = System.nanoTime() - timeBefore;
        System.out.println(word + " " + difference / (WARMUP_ITERATIONS / 10));
    }
}
