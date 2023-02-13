package MPSutils;

import MPSdataStructures.Node;

import java.util.LinkedList;
import java.util.Queue;

public class CalculateRoot {

    static public Queue<Node> nodesQueue = new LinkedList<Node>();

    public static void calculateNodesValueInOrder(Node node) {
        if (node != null) {
            calculateNodesValueInOrder(node.getLeft());
            if (node.getRight() != null && node.getLeft() != null &&
                    node.getLeft().getVal() != 0.0 && node.getRight().getVal() != 0.0) {
                node.computeOutput();
                node.setCalculated(true);
            } else if (node.getRight() != null && node.getLeft() != null &&
                    (node.getLeft().getVal() == 0.0 || node.getRight().getVal() == 0.0)) {
                node.setCalculated(false);
                nodesQueue.add(node);
            }
            calculateNodesValueInOrder(node.getRight());
        }
    }

    public static void calculateRoot() {
        while (!nodesQueue.isEmpty()) {
            Node node = nodesQueue.peek();
            nodesQueue.remove();

            if ((node.getLeft().getVal() != 0.0 && node.getRight().getVal() != 0.0) ||
                    (node.getLeft().getVal() == 0.0 && node.getLeft().isCalculated() && node.getRight().getVal() != 0.0) ||
                    (node.getLeft().getVal() != 0.0 && node.getRight().getVal() == 0.0 && node.getRight().isCalculated()) ||
                    (node.getLeft().getVal() == 0.0 && node.getLeft().isCalculated() && node.getRight().getVal() == 0.0 && node.getRight().isCalculated())) {
                node.computeOutput();
                node.setCalculated(true);
            } else {
                nodesQueue.add(node);
            }
        }
    }
}
