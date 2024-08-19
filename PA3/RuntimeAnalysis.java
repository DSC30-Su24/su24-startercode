import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to analyze the sorting methods
 * present in Sorts.java
 */
public class RuntimeAnalysis {

    private static final Integer[] LARGE_INPUT_SIZES = {
            10_000,
            20_000,
            40_000,
            80_000,
            160_000,
            320_000,
            640_000,
            1_280_000,
    };
    private static final Integer[] SMALL_INPUT_SIZES = {
            1_000,
            2_000,
            4_000,
            8_000,
            16_000,
            32_000,
            64_000,
            128_000,
    };
    private static final int NUM_RUNS = 10;

    /**
     * Measures execution times of different sorting methods and
     * saves them to csv.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        System.out.println("Warming up Java");
        for (int i = 0; i < NUM_RUNS; i++) {
            int size = SMALL_INPUT_SIZES[0];
            ArrayList<Integer> arr = generateArrayList(size);
            measureExecutionTime(() -> Sorts.bucketSort(arr));
            measureExecutionTime(() -> Sorts.countSort(arr));
        }

        System.out.println("Started timings for sorts...");
        ArrayList<String> timings = new ArrayList<>();
        for (int i = 0; i < LARGE_INPUT_SIZES.length; i++) {
            ArrayList<Integer> largeList = generateArrayList(LARGE_INPUT_SIZES[i]);
            ArrayList<Integer> smallList = generateArrayList(SMALL_INPUT_SIZES[i]);

            timings.add(measureAndPrintSortingTime("Bucket Sort", largeList));
            timings.add(measureAndPrintSortingTime("Count Sort", largeList));
        }
        writeToCSV(timings);
    }

    /**
     * Generates an ArrayList of length size
     * @param size the length of the generated ArrayList
     * @return the generated ArrayList
     */
    public static ArrayList<Integer> generateArrayList(int size) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arr.add((int) (Math.random() * 1000));
        }
        return arr;
    }

    /**
     * Measures the execution time of different sorting methods,
     * while saving the data and printing the results.
     *
     * @param sortName the sorting type name
     * @param list the list to be sorted
     * @return a comma-separated String denoting information about the execution time
     */
    private static String measureAndPrintSortingTime(String sortName, ArrayList<Integer> list) {
        long sortingTime = switch (sortName) {
            case "Bucket Sort" -> measureExecutionTime(() -> Sorts.bucketSort(list));
            case "Count Sort" -> measureExecutionTime(() -> Sorts.countSort(list));
            default -> 0; // Default case, you can handle it as needed
        };
        System.out.println(sortName + " took " + sortingTime + " nanoseconds on an array of size " + list.size());
        return String.join(",", sortName, Integer.toString(list.size()), Long.toString(sortingTime));
    }

    /**
     * Measures the average execution time of a Runnable.
     *
     * @param code the code to execute
     * @return the average execution time
     */
    public static long measureExecutionTime(Runnable code) {
        long startTime, endTime;
        long totalTime = 0;

        for (int i = 0; i < NUM_RUNS; i++) {
            startTime = System.nanoTime();
            code.run();
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / NUM_RUNS;
    }

    /**
     * Writes the execution time results to a .csv file
     *
     * @param timings execution time results
     */
    public static void writeToCSV(ArrayList<String> timings){
        String fileName = "sort_runtimes.csv";
        try {
            StringBuilder csvContent = new StringBuilder();
            csvContent.append("sort_type,data_size,run_time\n");
            for (String timing : timings){
                csvContent.append(timing).append("\n");
            }
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(csvContent.toString());
            bufferedWriter.close();
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}