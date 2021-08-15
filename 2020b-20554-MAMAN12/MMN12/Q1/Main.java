import java.util.*;

public class Main {

    final static int NUMEXP = 15; //Number of total expressions
    final static int MAXVAL = 10; //Maximum value of single number (1 to 10)
    final static int MAXAGR = 5;//Maximum number of arguments in one expression (1 to 5)

    public static void main(String[] args) {

        // First step create random expressions via the numbers below
        ArrayList<Expression> arr = new ArrayList();
		Random rand = new Random();
        double randNum;
        Expression exp;

        for (int i = 0; i < NUMEXP; i++) {
            // Random value of number in expression
            randNum = rand.nextInt(MAXVAL) + 1;
            exp = new AtomicExpression(randNum);
            // Random number of arguments in expression
            int randArg = rand.nextInt(MAXAGR) + 1;
            for (int j = 0; j < randArg; j++) {
                randNum = rand.nextInt(MAXVAL) + 1;
                Expression tempExp = new AtomicExpression(randNum);
                if (rand.nextBoolean()) {
                    exp = new AdditionExpression(exp, tempExp);
                } else {
                    exp = new SubtractionExpression(exp, tempExp);
                }
            }
			arr.add(exp);
        }

        //Last step print random expression and check if there is 2 different expression with same value

        //Print all the expression and the calculation of the expression
        for (int i = 0; i < NUMEXP; i++) {
            System.out.println("Expression number " + i + " = " + arr.get(i));
            System.out.println("and his value equal to " + arr.get(i).calculate());
            System.out.println("---");
        }
        
        //Check if there is 2 different expression (i!=j) with the same values (list.get(i).equals(list.get(j))
        for (int i = 0; i < NUMEXP; i++) {
            for (int j = 0; j < NUMEXP; j++) {
                if (arr.get(i).equals(arr.get(j)) && (i != j)) {
                    System.out.println("The expressions: " + arr.get(i) + " and " + arr.get(j)+" ");
                    System.out.println("are equals to " + arr.get(i).calculate());
                    System.out.println("---");
                }
            }
        }
        
        
    }

}
