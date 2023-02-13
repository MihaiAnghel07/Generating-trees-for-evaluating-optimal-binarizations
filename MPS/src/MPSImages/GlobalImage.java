package MPSImages;

import java.util.*;

public class GlobalImage {
    private final String name;
    private final ArrayList<Double> thresholds;
    private final ArrayList<Double> fMeasures;
    private final Double optimalTreshold;
    public static Random rand = new Random();

    public GlobalImage(String name, ArrayList<Double> thresholds, ArrayList<Double> fMeasures) {
        this.name = name;
        this.thresholds = thresholds;
        this.fMeasures = fMeasures;
        this.optimalTreshold = thresholds.get(rand.nextInt(thresholds.size()));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getThresholds() {
        return thresholds;
    }

    public ArrayList<Double> getFMeasures() {
        return fMeasures;
    }

    public Double getOptimalTreshold() {
        return optimalTreshold;
    }

    @Override
    public String toString() {
        return "GlobalImage{" +
                "name='" + name + '\'' +
                ", thresholds=" + thresholds +
                ", fMeasures=" + fMeasures +
                ", optimalTreshold=" + optimalTreshold +
                '}';
    }
}