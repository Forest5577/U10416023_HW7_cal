//U10416023

//Import the api we need
import java.util.function.BinaryOperator;

//use enum just like enum used in C++
public enum Operator {
    DIVIDE("/", (x, y) -> x / y),
    MULTIPLY("x", (x, y) -> x * y),
    SUBTRACT("-", (x, y) -> x - y),
    ADD("+", (x, y) -> x + y);

    private final String symbol;
    private final BinaryOperator<Double> equation;

    //constructor
    Operator(String symbol, BinaryOperator<Double> equation) {
        this.symbol = symbol;
        this.equation = equation;
    }
    //use enum operator to count two numbers
    public double compute(double alpha, double beta) {
        return equation.apply(alpha, beta);
    }
    //return the symbol like +-*/
    @Override
    public String toString() {
        return symbol;
    }
}
