package MPSmetrics;

public enum NodeType {
    LEAF(1),
    AND(4),
    OR(4),
    XOR(4),
    ADDITION(5),
    SUBTRACTION(6),
    MULTIPLICATION(7),
    DIVISION(8),
    AVERAGE(12),
    GMEAN(14);



    private final Integer value;

    NodeType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}