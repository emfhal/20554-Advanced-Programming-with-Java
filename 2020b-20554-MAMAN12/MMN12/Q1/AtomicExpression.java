public class AtomicExpression extends Expression {

    double numericExpression;

    //Atomic Expression
    public AtomicExpression(double valueExpression) {
        super();
        this.numericExpression = valueExpression;
    }
    public double getNum() {
		return this.numericExpression;
    }
    public double calculate() {
		return numericExpression;
	}

    public String toString() {
        return ""+numericExpression+""; // double number to string
    }
}