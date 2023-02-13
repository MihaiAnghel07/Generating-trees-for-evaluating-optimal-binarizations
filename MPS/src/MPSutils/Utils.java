package MPSutils;

import MPSdataStructures.Node;
import MPSdataStructures.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static void printInorder(Node node)
    {
        if (node == null)
            return;

        printInorder(node.getLeft());

        if(node.getOp() != null) {
            System.out.print(node.getOp() + " ");
        } else {
            System.out.print(node.getVal() + " ");
        }

        printInorder(node.getRight());
    }

    public static int getMaxDepth(Node node) {
        if (node == null)
            return 0;
        return Math.max(getMaxDepth(node.getLeft()), getMaxDepth(node.getRight())) + 1;
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    public static void printTree(Tree tree) {
        int maxLevel = getMaxDepth(tree.getRoot());

        printNodes(Collections.singletonList(tree.getRoot()), 1, maxLevel);
    }

    private static void printNodes(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print("V=" + node.getVal() + " O=" + node.getOp());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= endgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i + 1);
                if (node == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (node.getLeft() != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (node.getRight() != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println();
        }

        printNodes(newNodes, level + 1, maxLevel);
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

    public static double normalizeValue(double value) {
        if (value < 0)
            value = -value;

        if (value > 1)
            while (value > 1)
                value /= 2;

        return value;
    }
}
