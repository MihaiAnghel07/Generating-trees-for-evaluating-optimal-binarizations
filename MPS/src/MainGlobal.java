import MPSFmeasure.FmeasureCalculator;
import MPSImages.GlobalImage;
import MPSTreeFileIO.TreeFile;
import MPSdataStructures.Tree;
import MPSinputReader.GlobalInputReader;
import MPSmetrics.Metrics;
import MPSutils.Utils;

public class MainGlobal {

    public static void main(String[] args) {

        TreeFile treeFileIO = new TreeFile();

        FmeasureCalculator fmeasureCalculator = new FmeasureCalculator();
        Metrics metrics = new Metrics();
        GlobalInputReader reader = new GlobalInputReader();
        GlobalImage image;

        double bestFMeasure = 0;
        double bestMetric = 0;
        Tree bestTree = null;

        while ((image = reader.readImage()) != null) {
            int steps = 0;
            while (steps < 50) {
                Tree tree = new Tree(image.getThresholds());

                MPSutils.CalculateRoot.calculateNodesValueInOrder(tree.getRoot());
                MPSutils.CalculateRoot.calculateRoot();
                tree.getRoot().trimValue();
                double threshold = tree.getRoot().getVal();
                boolean isFmeasureEfficient = fmeasureCalculator.compareFmeasure(threshold, image);
                double treeMetric = metrics.computeMetric(tree.getRoot());
                double treeFMeasure = fmeasureCalculator.getFmeasure(threshold, image);

                if (isFmeasureEfficient) {
                    if (bestTree == null ||
                            (treeFMeasure - bestFMeasure > 0.1) ||
                            (treeFMeasure - bestFMeasure < 0.1 && treeFMeasure - bestFMeasure > 0
                                    && treeMetric < bestMetric)) {
                        bestTree = tree;
                        bestFMeasure = treeFMeasure;
                        bestMetric = treeMetric;
                    }
                }

                steps++;
            }
            if (bestTree != null) {
                System.out.println("fmeasure = " + bestFMeasure + " radacina: " + bestTree.getRoot().getVal() + " nume fisier: " + image.getName());
            } else {
                System.out.println("Nu s a gasit");
            }
        }


        if (bestTree != null) {
            treeFileIO.writeTreeToFile(bestTree);
        }


        Tree t = new Tree();
        Utils.printTree(t);
    }

}