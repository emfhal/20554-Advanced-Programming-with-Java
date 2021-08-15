public abstract class CompoundExpression  extends Expression {
	protected Expression firstExp ;
	protected Expression secondExp ; 

public CompoundExpression(Expression a , Expression b ) {
		super();
		if(a instanceof AtomicExpression ) {
			firstExp = new AtomicExpression( ((AtomicExpression)a).getNum()); 
		}
		else {
			firstExp = a;
		}
		if(b instanceof AtomicExpression ) {
			secondExp = new AtomicExpression( ((AtomicExpression)b).getNum()); 
		}
		else {
			secondExp = b;
		}
	}
public abstract String toString();
}
