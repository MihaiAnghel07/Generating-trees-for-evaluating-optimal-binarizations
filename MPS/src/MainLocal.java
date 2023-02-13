import MPSFmeasure.FmeasureCalculator;
import MPSImages.LocalImage;
import MPSdataStructures.Tree;
import MPSinputReader.LocalInputReader;
import MPSutils.Utils;

import java.util.ArrayList;

public class MainLocal {
    public static void main(String[] args) {

        // Repeat steps below multiple times, in order to determine optimal tree
        int n_steps = 3;
        double bestFmeasure = 0;
        Tree bestTree = null;
        Tree currentTree = null;
        FmeasureCalculator fmeasureCalculator = new FmeasureCalculator();

        for (int k = 0; k < n_steps; k++) {
            // Create reader for each tree
            LocalInputReader localReader = new LocalInputReader();
            LocalImage localImage;

            currentTree = new Tree();
            Utils.printTree(currentTree);

            // Average fmeasure attributed to the tree created previously
            Double averageFmeasure = Double.valueOf(0);

            // Fmeasures calculated for each image
            ArrayList<Double> fmeasures = new ArrayList<>();

            // For each file/image
            while ((localImage = localReader.readImage()) != null) {
                // Calculate fmeasure for current image
                double fmeasure = fmeasureCalculator.computeFmeasure(localImage, currentTree);
                fmeasures.add(fmeasure);
            }

            System.out.println("Calculating average");
            // Calculate average fmeasure for tree
            for (Double fm : fmeasures) {
                averageFmeasure += fm;
            }
            System.out.println("Sum done");
            averageFmeasure = averageFmeasure / fmeasures.size();
            System.out.println("Average: " + averageFmeasure);


            // Compare current average fmeasure with best fmeasure and update best fmeasure if needed
            if (averageFmeasure > bestFmeasure) {
                bestFmeasure = averageFmeasure;
                bestTree = currentTree;
            }
        }
        System.out.println("================BEST TREE================");
        Utils.printTree(bestTree);
        System.out.println("Best tree fmeasure: " + bestFmeasure);


    }
}