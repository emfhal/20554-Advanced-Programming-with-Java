public abstract class Expression {
     
	public abstract double calculate();
 
    /*
     * Check if two expressions are equal (only if the values are the same)
     * @Return true in case the two expressions are equals
     */
    public boolean equals(Object second) {
        double firstAnswer, secondAnswer;
		if(second == null) {
			return false;
		}
		if(!(second instanceof Expression)) {
				 return false;
		}
        firstAnswer = this.calculate();
        secondAnswer = ((Expression) (second)).calculate();
        return (firstAnswer == secondAnswer);
    }
}
