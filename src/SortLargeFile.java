import java.io.*;

public class SortLargeFile {
    public static final int MAX_ARRAY_SIZE = 100000;
    public static final int BUFFER_SIZE = 100000;

    public static void main(String[] args) throws Exception {

        sort("large data.dat", "sorted-file.dat");
        displayFile("sorted-file.dat");
    }

    public static void sort(String sourcefile, String targetfile)
            throws Exception {

        int numberOfSegments =
                initializeSegments(MAX_ARRAY_SIZE, sourcefile, "f1.dat");

        merge(numberOfSegments, MAX_ARRAY_SIZE,
                "f1.dat", "f2.dat", "f3.dat", targetfile);
    }

    private static int initializeSegments
            (int segmentSize, String originalFile, String f1)
            throws Exception {

        return segmentSize;
    }

    private static void merge(int numberOfSegments, int segmentSize,
                              String f1, String f2, String f3, String targetfile)
            throws Exception {
        if (numberOfSegments > 1) {
            mergeOneStep(numberOfSegments, segmentSize, f1, f2, f3);
            merge((numberOfSegments + 1) / 2, segmentSize * 2,
                    f3, f1, f2, targetfile);
        } else {
            File sortedFile = new File(targetfile);
            if (sortedFile.exists()) sortedFile.delete();
            new File(f1).renameTo(sortedFile);
        }
    }

    private static void mergeOneStep(int numberOfSegments,
                                     int segmentSize, String f1, String f2, String f3)
            throws Exception {
        DataInputStream f1Input = new DataInputStream(
                new BufferedInputStream(new FileInputStream(f1), BUFFER_SIZE));
        DataOutputStream f2Output = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f2), BUFFER_SIZE));

        copyHalfToF2(numberOfSegments, segmentSize, f1Input, f2Output);
        f2Output.close();

        DataInputStream f2Input = new DataInputStream(
                new BufferedInputStream(new FileInputStream(f2), BUFFER_SIZE));
        DataOutputStream f3Output = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f3), BUFFER_SIZE));

        mergeSegments(numberOfSegments / 2,
                segmentSize, f1Input, f2Input, f3Output);

        f1Input.close();
        f2Input.close();
        f3Output.close();
    }

    private static void copyHalfToF2(int numberOfSegments,
                                     int segmentSize, DataInputStream f1, DataOutputStream f2)
            throws Exception {
    }

    private static void mergeSegments(int numberOfSegments,
                                      int segmentSize, DataInputStream f1, DataInputStream f2,
                                      DataOutputStream f3) throws Exception {

    }

    private static void mergeTwoSegments(int segmentSize,
                                         DataInputStream f1, DataInputStream f2,
                                         DataOutputStream f3) throws Exception {
    }
    public static void displayFile(String filename) {
        try {
            DataInputStream input =
                    new DataInputStream(new FileInputStream(filename));
            for (int i = 0; i < 100; i++)
                System.out.print(input.readInt() + " ");
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}