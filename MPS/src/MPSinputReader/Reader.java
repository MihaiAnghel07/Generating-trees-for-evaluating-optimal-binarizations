package MPSinputReader;

public class Reader {
    public static final String GLOBAL_DIRECTORY = "MPS-Global";
    public static final String LOCAL_DIRECTORY = "MPS-Local";
    public static final String LINUX = "/";
    public static final String WINDOWS = "\\";
    public static final int THRESHOLD_NR = 16;
    public static final int FMEASURES_NR = 256;
    public static final int THRESHOLD_LOCAL_NR = 10;

    public static final int MAX_THRESHOLD = 1;
    public static String getDelimitator() {
        if (System.getProperty("os.name").endsWith("Linux"))
            return LINUX;
        else
            return WINDOWS;
    }
}