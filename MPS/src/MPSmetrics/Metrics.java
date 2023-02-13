package MPSmetrics;

import MPSdataStructures.Node;

public class Metrics {
    private Integer nodesCost;
    private Integer leavesCost;

    public Metrics() {
        nodesCost = 0;
        leavesCost = 0;
    }

    private void computeCosts(Node node){
        if(node == null)
            return;

        computeCosts(node.getLeft());
            NodeType nodeType = NodeValueToTypeMapper.toNodeType(node);
            if (nodeType == NodeType.LEAF) {
                leavesCost += nodeType.getValue();
            } else {
                nodesCost += nodeType.getValue();
            }
        computeCosts(node.getRight());
    }

    public Double computeMetric(Node node) {
        computeCosts(node);
        return Double.valueOf(nodesCost) / Double.valueOf(leavesCost);
    }
}