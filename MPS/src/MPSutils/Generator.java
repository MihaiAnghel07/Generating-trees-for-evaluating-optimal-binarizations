package MPSutils;

import MPSdataStructures.Node;

import java.util.*;

public class Generator {

    public static String generateOperation() {
        List<Operations> operations = new ArrayList<>(EnumSet.allOf(Operations.class));
        return operations.get(new Random().nextInt(Operations.getNrOperations())).toString();
    }

    // Used for global images
    public static Node generateNodeLeaf(ArrayList<Double> thresholds) {
        return new Node(thresholds.get(new Random().nextInt(thresholds.size())));
    }

    // Used for local images
    public static Node generateNodeLeafLocal() {
        return new Node(((double) new Random().nextInt(10)));
    }

    public static Node generateNode() {
        return new Node(generateOperation(), null, null);
    }
}
