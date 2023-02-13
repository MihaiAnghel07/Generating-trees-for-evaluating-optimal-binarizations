package MPSutils;

public enum Operations {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    AVERAGE("avg"),
    GMEAN("gmean"),

    AND("&"),
    OR("|"),
    XOR("^");


    // ! se va modifica 'nrOperations' in functie de operatiile adaugat / eliminate in lista de mai sus !
    private static final int nrOperations = 6;
    private final String value;
    Operations(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static int getNrOperations() {
        return nrOperations;
    }
}
