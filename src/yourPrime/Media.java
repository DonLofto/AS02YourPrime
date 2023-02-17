package yourPrime;

import java.io.Serializable;

public class Media implements Serializable {
	
	// superclass for the media types available in YourPrime streaming  platform.
	private static final long serialVersionUID = 1L;
	protected String title = "";
	protected int year = 0;
	protected int rating = 0;
	protected double price = 0.0;
	protected Genre genre;
	
	public double getPrice() {
		this.price = genre.setPrice(rating);
		return price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getRating() {
		return rating;
	}
}
