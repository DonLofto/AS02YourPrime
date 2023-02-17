package yourPrime;

public class Song extends Media {
	
	private static final long serialVersionUID = 1L;
	// TODO complete this class
	// variable(s):
	//  title (String), artist (String), rating (int), genre (Genre)
	// 
	// method(s):
	//  constructor(String, String, int, Genre) - assign to the this.variable(s)
	//  double getPrice() - call the Genre.setPrice(int rating), use rating as argument
	//  override toString() method to display song information
	private String artist;
	
	public Song(String title, String artist, int rating, Genre genre) {
		this.title = title;
		this.artist = artist;
		this.rating = rating;
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return (title + " by " + artist + " price at = " + this.price + ", rating = " + getRating());
	}
}
