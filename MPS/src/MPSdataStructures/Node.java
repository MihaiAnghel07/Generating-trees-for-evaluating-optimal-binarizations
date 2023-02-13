package MPSdataStructures;

public class Node {
    private String op;
    private Double val;
    private Node left;
    private Node right;
    private boolean isCalculated = false;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }

    /*
        Leaf nodes - no children or operation
         */
    public Node(Double val) {
        this.val = val;
        this.op = null;
        this.left = null;
        this.right = null;
    }

    public Node(String op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
        this.val =  Double.valueOf(0);
    }

    public void computeOutput() {
        Double result = switch (this.op) {
            case "+" -> this.left.getVal() + this.right.getVal();
            case "-" -> this.left.getVal() - this.right.getVal();
            case "*" -> this.left.getVal() * this.right.getVal();
            case "/" -> (this.right.getVal() == 0) ? this.left.getVal() : this.left.getVal() / this.right.getVal();
            case "&" -> Double.valueOf((int) (double)this.left.getVal() & (int) (double)this.right.getVal());
            case "|" ->  Double.valueOf((int) (double)this.left.getVal() | (int) (double)this.right.getVal());
            case "^" ->  Double.valueOf((int) (double)this.left.getVal() ^ (int) (double)this.right.getVal());
            case "avg" -> (this.left.getVal() + this.right.getVal()) / 2;
            case "gmean" -> Math.sqrt(this.left.getVal() * this.right.getVal());
            default ->  Double.valueOf(0);
        };

        setVal(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node node)) {
            return false;
        }

        if (Double.compare(node.getVal(), getVal()) != 0) {
            return false;
        }
        if (getOp() != null ? !getOp().equals(node.getOp()) : node.getOp() != null) {
            return false;
        }
        if (getLeft() != null ? !getLeft().equals(node.getLeft()) : node.getLeft() != null) {
            return false;
        }
        return getRight() != null ? getRight().equals(node.getRight()) : node.getRight() == null;
    }

    public void trimValue() {
        // Adjust values that are not within [0, 1] interval
        if (this.val < 0) {
            Double tmp = this.val;
            tmp *= -1;
            tmp = tmp - Math.floor(tmp);
            this.val = tmp;
        } else if (this.val > 1) {
            Double tmp = this.val;
            tmp = tmp - Math.floor(tmp);
            this.val = tmp;
        }
    }
}
