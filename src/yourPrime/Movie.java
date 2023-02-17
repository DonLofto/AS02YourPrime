package yourPrime;

public class Movie extends Media {
	
	private static final long serialVersionUID = 1L;
	// movie sub-class inherit from Media (superclass)
	private String cast;
	private double duration;
	
	public Movie(String title, String cast, double duration, int year, int rating, Genre genre) {
		
		this.title = title;
		this.cast = cast;
		this.duration = duration;
		this.year = year;
		this.rating = rating;
		this.genre = genre;
	}
	
	public double getDuration() {
		
		return this.duration;
	}
	
	@Override
	public String toString() {

		return (this.title + " with " + this.cast + " at " + this.price + ", rating = " + getRating() + " " + getDuration());
	}

}
