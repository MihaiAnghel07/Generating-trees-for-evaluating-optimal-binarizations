package MPSFmeasure;

import MPSImages.GlobalImage;
import MPSImages.LocalImage;
import MPSdataStructures.Tree;
import MPSutils.CalculateRoot;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static MPSinputReader.Reader.THRESHOLD_NR;

public class FmeasureCalculator {

    public FmeasureCalculator() {}

    private double computeFmeasureIndex(double threshold)
    {
        return Math.floor(threshold * 255);
    }

    public double getFmeasure(double threshold, GlobalImage image)
    {
        double fmeasureIndex = computeFmeasureIndex(threshold);
        return image.getFMeasures().get((int) fmeasureIndex);
    }

    public boolean compareFmeasure(double threshold, GlobalImage image)
    {
        // se calculeaza fmeasure pentru arborele nostru
        double fmeasureIndex = computeFmeasureIndex(threshold);
        double fmeasure = image.getFMeasures().get((int) fmeasureIndex);

        for (int i = 1; i < THRESHOLD_NR; i++)
        {
            // se calculeaza fmeasure pentru cei 16 algoritmi din input
            double fmeasure_alg = image.getFMeasures().get((int)computeFmeasureIndex(image.getThresholds().get(i)));
            if (fmeasure < fmeasure_alg) {
                return false;
            }
        }

        return true;
    }

    public double computeFmeasure(LocalImage localImage, Tree currentTree) {
        int fpCount = 0, fnCount = 0, tpCount = 0, tnCount = 0;
        double fmeasure = 0, rootValue = 0;

        // For each pixel
        for (int i = 0; i < localImage.getPixels().size(); i++) {
            // Fill in tree leaves values based on thresholds from current image and pixel
            ArrayList<Double> indexes = currentTree.getIndexes();
            ArrayDeque<Double> leafValues = new ArrayDeque<>();
            for (Double d : indexes) {
                leafValues.add(localImage.getThresholds().get(i)[d.intValue()]);
            }

            currentTree.replaceLeafValues(currentTree.getRoot(), leafValues);

            // Compute tree's root value
            CalculateRoot.calculateNodesValueInOrder(currentTree.getRoot());
            CalculateRoot.calculateRoot();
            currentTree.getRoot().trimValue();
            rootValue = currentTree.getRoot().getVal();

            // Attribute FP/FN/TP/TN value
            // Consider class 0 - white and class 1 - black
            if (localImage.getPixelClass().get(i) == 1) {
                if (rootValue > localImage.getPixels().get(i)) {
                    tnCount++;
                } else {
                    fnCount++;
                }
            } else {
                if (rootValue < localImage.getPixels().get(i)) {
                    tpCount++;
                } else {
                    fpCount++;
                }
            }
        }

        // Calculate fmeasure for current image
        // The formula currently uses the count of FP/FN/TP/TN values
        // and not an average of the pixel values themselves (ex. fpCount instead of fp)
        // This can be adjusted later if needed
        fmeasure = tpCount / (tpCount + 0.5 * (fpCount + fnCount));

        return fmeasure;
    }
}
