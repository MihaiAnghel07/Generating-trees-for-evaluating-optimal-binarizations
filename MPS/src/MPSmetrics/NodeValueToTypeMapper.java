package MPSmetrics;

import MPSdataStructures.Node;

public class NodeValueToTypeMapper {
    public static NodeType toNodeType(Node node) {
        if (node.getOp() == null) {
            return NodeType.LEAF;
        }
        switch (node.getOp()) {
            case "+" -> {
                return NodeType.ADDITION;
            }
            case "-" -> {
                return NodeType.SUBTRACTION;
            }
            case "*" -> {
                return NodeType.MULTIPLICATION;
            }
            case "/" -> {
                return NodeType.DIVISION;
            }
            case "&" -> {
                return NodeType.AND;
            }
            case "|" -> {
                return NodeType.OR;
            }
            case "^" -> {
                return NodeType.XOR;
            }
            case "avg" -> {
                return NodeType.AVERAGE;
            }
            case "gmean" -> {
                return NodeType.GMEAN;
            }
            default -> {
                return NodeType.LEAF;
            }
        }
    }
}
