public class AdditionExpression extends CompoundExpression {
	
	public AdditionExpression(Expression a , Expression b ) {
		super(a,b);
	}
	// calculate the addition expression
	public double calculate() {
		return firstExp.calculate() + secondExp.calculate();
	}
	public String toString() {
		String string1; 
		String string2;
		
		if(firstExp instanceof CompoundExpression) {
			string1 = ((CompoundExpression)firstExp).toString(); 
		}
		else {
			string1 = ((AtomicExpression)firstExp).toString(); 
		}
		if(secondExp instanceof CompoundExpression) {
			string2 = ((CompoundExpression)secondExp).toString(); 
		}
		else {
			string2 = ((AtomicExpression)secondExp).toString(); 
		}
		return  "("+string1+ " + " +string2+")"; 
	}
}
