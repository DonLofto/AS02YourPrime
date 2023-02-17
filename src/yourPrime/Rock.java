package yourPrime;

public class Rock extends Genre {
	
	private static final long serialVersionUID = 1L;

	//TODO complete method override
	@Override
	double setPrice(int rating) {
		if (rating > 3) {
			return 3.99;
		} else if (rating == 3) {
			return 2.99;
		}
		return 0.99;
	}
}
