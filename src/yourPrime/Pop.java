package yourPrime;

public class Pop extends Genre {
	
	private static final long serialVersionUID = 1L;

	//TODO complete method override
	@Override
	public double setPrice(int rating) {
		if (rating > 3) {
			return 2.99;
		} else if (rating == 3) {
			return 1.99;
		} 
		return 0.00;
	}
}
