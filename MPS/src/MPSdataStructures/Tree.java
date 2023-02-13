package MPSdataStructures;

import MPSutils.Generator;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Tree {
    private Node root;
    private int nrNodes;
    private final ArrayList<Double> thresholds;

    private ArrayList<Double> indexes;

    public Tree(Tree tree) {
        this.root = tree.root;
        this.nrNodes = tree.nrNodes;
        this.thresholds = tree.thresholds;
    }

    public Tree(ArrayList<Double> thresholds) {
        this.thresholds = thresholds;
        this.nrNodes = new Random().nextInt(10) * 2 + 1;
        this.root = generateTree(this.nrNodes);
    }

    public Tree(Node root, int nrNodes, ArrayList<Double> thresholds) {
        this.root = root;
        this.nrNodes = nrNodes;
        this.thresholds = thresholds;
    }

    // Used for local images
    public Tree() {
        thresholds = null;
        indexes = new ArrayList<>();
        this.nrNodes = new Random().nextInt(10) * 2 + 1;
        this.root = generateTreeLocal(this.nrNodes);
    }

    private Node generateTree(int currentNrNodes) {
        if (currentNrNodes == 1) {
            return Generator.generateNodeLeaf(thresholds);
        }

        Node node = Generator.generateNode();
        currentNrNodes--;
        int nrNodesLeft = new Random().nextInt(currentNrNodes / 2) * 2 + 1;

        node.setLeft(generateTree(nrNodesLeft));
        node.setRight(generateTree(currentNrNodes - nrNodesLeft));

        return node;
    }

    public Node getRoot() {
        return root;
    }

    public int getNrNodes() {
        return nrNodes;
    }

    public ArrayList<Double> getThresholds() {
        return thresholds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tree tree)) {
            return false;
        }

        if (getNrNodes() != tree.getNrNodes()) {
            return false;
        }
        if (getRoot() != null ? !getRoot().equals(tree.getRoot()) : tree.getRoot() != null) {
            return false;
        }
        return getThresholds() != null ? getThresholds().equals(tree.getThresholds()) :
                tree.getThresholds() == null;
    }




    // ===========================================



    private Node generateTreeLocal(int currentNrNodes) {
        if (currentNrNodes == 1) {
            Node n = Generator.generateNodeLeafLocal();
            indexes.add(n.getVal());
            return n;
        }

        Node node = Generator.generateNode();
        currentNrNodes--;
        int nrNodesLeft = new Random().nextInt(currentNrNodes / 2) * 2 + 1;

        node.setLeft(generateTreeLocal(nrNodesLeft));
        node.setRight(generateTreeLocal(currentNrNodes - nrNodesLeft));


        return node;
    }



    public void replaceLeafValues(Node root, ArrayDeque<Double> newValues) {
        if (root == null) {
            return;
        }

        root.setCalculated(false);

        if (root.getLeft() == null && root.getRight() == null) {
            // Replace leaf value
            root.setVal(newValues.remove());
            root.setCalculated(true);
            return;
        }

        if (root.getLeft() != null) {
            replaceLeafValues(root.getLeft(), newValues);
        }

        if (root.getRight() != null) {
            replaceLeafValues(root.getRight(), newValues);
        }
    }




    public ArrayList<Double> getIndexes() {
        return indexes;
    }
}
