package yourPrime;

public class Book extends Media {

	// book sub-class inherit from Media (superclass)
	// TODO complete all methods in this class, refer each method for details
	
	private static final long serialVersionUID = 1L;
	private String author;
	private int noPages;
	
	public Book(String title, String author, int year, int rating, int noPages, Genre genre) {
		// TODO assign all arguments to class
		this.title = title;
		this.author = author;
		this.year = year;
		this.rating = rating;
		this.noPages = noPages;
		this.genre = genre;
	}
	
	public int getNoPages() {
		// TODO return number of pages
		return noPages;
	}
	
	@Override
	public String toString() {
		// TODO return a string with the message
		// title-of-book by name-of-author price at price
		return (this.title + " by " + this.author + " at " + this.price + ", rating = " + getRating() + " " + this.noPages);
	}
}
