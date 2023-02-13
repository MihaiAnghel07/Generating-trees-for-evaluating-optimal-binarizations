package MPSImages;

import java.util.*;

public class LocalImage {
    private final String name;
    private final ArrayList<Double> pixels;
    private final ArrayList<Double> pixelClass;
    // the thresholds for the pixel with the index i are thresholds.get(i)
    private final ArrayList<Double[]> thresholds;

    public LocalImage(String name, ArrayList<Double> pixels, ArrayList<Double> pixelClass, ArrayList<Double[]> thresholds) {
        this.name = name;
        this.pixels = pixels;
        this.pixelClass = pixelClass;
        this.thresholds = thresholds;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getPixels() {
        return pixels;
    }

    public ArrayList<Double> getPixelClass() {
        return pixelClass;
    }

    public ArrayList<Double[]> getThresholds() {
        return thresholds;
    }
}