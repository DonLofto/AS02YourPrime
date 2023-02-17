package yourPrime;

public class Rap extends Genre {
	
	private static final long serialVersionUID = 1L;

	//TODO complete method override
	@Override
	double setPrice(int rating) {
		if (rating > 3) {
			return 4.99;
		} else if (rating == 3) {
			return 3.99;
		}
		return 1.99;
	}
}
