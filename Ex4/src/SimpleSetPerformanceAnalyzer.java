import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.*;

public class SimpleSetPerformanceAnalyzer {

    static final int WARMUP_ITERATIONS = 70000;

    static final int NUM_OF_DATA_STRUCTURES = 5;

    static final String[] data1 = Ex4Utils.file2array("C:\\Users\\tomer\\Google Drive (tomer.nissim@mail" +
            ".huji.ac.il)\\Second year\\Semester A\\oop\\Ex4\\src\\data1.txt");

    static final String[] data2 = Ex4Utils.file2array("C:\\Users\\tomer\\Google Drive (tomer.nissim@mail" +
            ".huji.ac.il)\\Second year\\Semester A\\oop\\Ex4\\src\\data2.txt");

    private final SimpleSet[] dataSets1;

    private final SimpleSet[] dataSets2;

    // CONSTRUCTOR

    /**
     *
     */
    public SimpleSetPerformanceAnalyzer() {
        dataSets1 = new SimpleSet[NUM_OF_DATA_STRUCTURES];
        dataSets2 = new SimpleSet[NUM_OF_DATA_STRUCTURES];
        initDataSet(dataSets1);
        initDataSet(dataSets2);
    }

    // PRIVATE METHODS

    private void initDataSet(SimpleSet[] s) {
        s[0] = new ClosedHashSet();
        s[1] = new OpenHashSet();
        s[2] = new CollectionFacadeSet(new LinkedList<String>());
        s[3] = new CollectionFacadeSet(new TreeSet<String>());
        s[4] = new CollectionFacadeSet(new HashSet<String>());
    }

    @Test
    public void addData1() {
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            long timeBefore = System.nanoTime();
            assert data1 != null;
            for (String s : data1) {
                dataSets1[i].add(s);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("addData1 " + (difference / 1000000));
        }
    }

    @Test
    public void addData2() {
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            long timeBefore = System.nanoTime();
            assert data2 != null;
            for (String s : data2) {
                dataSets2[i].add(s);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("addData2 " + (difference / 1000000));
        }
    }

    private void containsHI() {
        long timeBefore = 0;
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            for (int j = 0; j < 2 * WARMUP_ITERATIONS; j++) {
                dataSets1[i].contains("hi");
                if (j == WARMUP_ITERATIONS) {
                    timeBefore = System.nanoTime();
                }
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("containsHI " + difference / WARMUP_ITERATIONS);
        }
    }

    private void contains13170890158() {
        long timeBefore = 0;
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            for (int j = 0; j < 2 * WARMUP_ITERATIONS; j++) {
                dataSets1[i].contains("-13170890158");
                if (j == WARMUP_ITERATIONS) {
                    timeBefore = System.nanoTime();
                }
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("contains-13170890158 " + difference / WARMUP_ITERATIONS);
        }
    }

    private void contains23() {
        long timeBefore = 0;
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            for (int j = 0; j < 2 * WARMUP_ITERATIONS; j++) {
                dataSets1[i].contains("23");
                if (j == WARMUP_ITERATIONS) {
                    timeBefore = System.nanoTime();
                }
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("contains23 " + difference / WARMUP_ITERATIONS);
        }
    }

    private void containsHi2() {
        long timeBefore = 0;
        for (int i = 0; i < NUM_OF_DATA_STRUCTURES; i++) {
            for (int j = 0; j < 2 * WARMUP_ITERATIONS; j++) {
                dataSets1[i].contains("hi");
                if (j == WARMUP_ITERATIONS) {
                    timeBefore = System.nanoTime();
                }
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("containsHi2 " + difference / WARMUP_ITERATIONS);
        }
    }

    @Test
    public void dataTest() {
        addData1();
        containsHI();
        contains13170890158();
        addData2();
        contains23();
        containsHi2();
    }
}
