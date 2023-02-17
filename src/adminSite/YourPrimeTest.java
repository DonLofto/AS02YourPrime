package adminSite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import yourPrime.Action;
import yourPrime.Autobiography;
import yourPrime.Book;
import yourPrime.Comedy;
import yourPrime.Drama;
import yourPrime.Fantasy;
import yourPrime.Movie;
import yourPrime.MyMedia;
import yourPrime.Pop;
import yourPrime.Rap;
import yourPrime.Rock;
import yourPrime.Song;
import yourPrime.Subscriber;

class YourPrimeTest {
	FuncUtil testFunc;
	
	// stream capture to validate output
	private final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
	
	@BeforeEach
	void setUp() {
		// dummy songs 
		Song s1 = new Song("Fight The Power", "Public Enemy", 4, new Rap());
		Song s2 = new Song("Smell Like Teen Spirit", "Nirvana", 4, new Rock());
		Song s3 = new Song("Blinding Lights", "The Weeknd", 2, new Pop());
		Song s4 = new Song("We Belong Together", "Mariah Carey", 3, new Pop());
		Song s5 = new Song("Hey Jude", "The Beatles", 3, new Rock());
				
		// dummy movies
		Movie m1 = new Movie("The Godfather", "Marlon Brando, Al Pacino", 175.0, 1972, 4, new Drama());
		Movie m2 = new Movie("Avengers: Endgame", "Ironman, Capt. America, Thor", 181.0, 2019, 2, new Action());
		Movie m3 = new Movie("Zoolander", "Ben Stiller, Owen Wilson", 90.0, 1998, 1, new Comedy());
		Movie m4 = new Movie("The Shawshank Redemption", "Tim Robbins, Morgan Freeman", 142.0, 1994, 5, new Drama());
		Movie m5 = new Movie("Forrest Gump", "Tom Hanks", 142.5, 1994, 3, new Comedy());
		
		// dummy books
		Book b1 = new Book("The Da Vinci Code", "Dan Brown", 2003, 1, 689, new Fantasy());
		Book b2 = new Book("Harry Potter and the Deathly Hallows", "J. K. Rowlings", 2007, 3, 607, new Fantasy());
		Book b3 = new Book("Show Dog", "Phil Knight", 2016, 5, 399, new Autobiography());
		Book b4 = new Book("The Fellowship of the Ring", "J. R. R. Tolkien", 1954, 2, 423, new Fantasy());
		Book b5 = new Book("Surely You're Jocking, Mr. Feynman", "Richard P. Feynman", 1985, 5, 356, new Autobiography());
		
		// create subscribers for testing purpose
		Map<Integer, List<Song>> mapLibSong = new HashMap<>();
		mapLibSong.put(0, Arrays.asList(s3, s2, s4));
		mapLibSong.put(1, Arrays.asList(s1, s2, s3, s4));
		mapLibSong.put(2, Arrays.asList(s2, s3, s4, s5));
		mapLibSong.put(3, Arrays.asList(s3, s5));
		mapLibSong.put(4, Arrays.asList(s1, s2, s4));
		
		Map<Integer, List<Movie>> mapLibMovie = new HashMap<>();
		mapLibMovie.put(0, Arrays.asList(m3, m2, m4));
		mapLibMovie.put(1, Arrays.asList(m1, m2, m3, m4));
		mapLibMovie.put(2, Arrays.asList(m2, m3));
		mapLibMovie.put(3, Arrays.asList(m3, m5));
		mapLibMovie.put(4, Arrays.asList(m1, m2, m4));
		
		Map<Integer, List<Book>> mapLibBook = new HashMap<>();
		mapLibBook.put(0, Arrays.asList(b3, b2, b4));
		mapLibBook.put(1, Arrays.asList(b1, b2, b3, b4));
		mapLibBook.put(2, Arrays.asList(b2, b4, b5));
		mapLibBook.put(3, Arrays.asList(b3, b5));
		mapLibBook.put(4, Arrays.asList(b1, b2, b4));
		
		List<String> listUser = Arrays.asList("Arthur Shelby", "Tommy Shelby", "Ada Shelby", "John Shelby", "Finn Shelby");
		List<String> listId = Arrays.asList("001", "002", "003", "004", "005");
		Map<String, Subscriber> userDb = new HashMap<>();
		MyMedia myMedia;
		for (int i = 0; i < 5; i++) {
			myMedia = new MyMedia(mapLibMovie.get(i), mapLibBook.get(i), mapLibSong.get(i));
			userDb.put(listId.get(i), new Subscriber(listId.get(i), listUser.get(i), "password", myMedia));
		}
		testFunc = new FuncUtil(userDb);
		
		// setup streams to evaluate System.out
		System.setOut(new PrintStream(byteOutput));
	}
	
	@Test
	@DisplayName("Testing for searchSubscriber()")
	void testSearchSubscriber() {
		assertEquals(testFunc.searchSubscriber("Tom").size(), 1);
	}
	
	@Test
	@DisplayName("Testing for calculateOverdueFees()")
	void testCalculateOverdueFees() {
		assertEquals(testFunc.calculateOverdueFees(), 73.69);
	}
	
	@Test
	@DisplayName("Testing for printSubscriber(name)")
	void testPrintAllSubscribersByName() {
		testFunc.printAllSubscribers("name");
		String testOutput = "Ada Shelby with outstanding amount = 13.94" + System.lineSeparator()
				+ "Arthur Shelby with outstanding amount = 12.94" + System.lineSeparator()
				+ "Finn Shelby with outstanding amount = 16.93" + System.lineSeparator()
				+ "John Shelby with outstanding amount = 9.96" + System.lineSeparator()
				+ "Tommy Shelby with outstanding amount = 19.92";
		assertEquals(byteOutput.toString().trim(), testOutput.trim());
	}

	@Test
	@DisplayName("Testing for printSubscriber(fee)")
	void testPrintAllSubscribersByFee() {
		testFunc.printAllSubscribers("fee");
		String testOutput = "John Shelby with outstanding amount = 9.96" + System.lineSeparator()
				+ "Arthur Shelby with outstanding amount = 12.94" + System.lineSeparator()
				+ "Ada Shelby with outstanding amount = 13.94" + System.lineSeparator()
				+ "Finn Shelby with outstanding amount = 16.93" + System.lineSeparator()
				+ "Tommy Shelby with outstanding amount = 19.92";
		assertEquals(byteOutput.toString().trim(), testOutput.trim());
		System.setOut(System.out);
	}
	
	@Test
	@DisplayName("Testing for getAverageOutstanding()")
	void testGetAverageOutstanding() {
		double val = testFunc.getAverageOutstanding();
		assertEquals(val, 14.738);

	}
	
	@Test
	@DisplayName("Testing for getTotalFeesGroupByMedia()")
	void testGetTotalFeesGroupByMedia() {
		Map<String, Double> mapMedia = testFunc.getTotalFeesGroupByMedia();
		assertEquals(mapMedia.values().toString(), "[14.9, 39.88, 18.91]");
	}
	
	@Test
	@DisplayName("Testing for getCountGroupByMedia()")
	void testGetCountGroupByMedia() {
		Map<String, Long> mapMedia = testFunc.getCountGroupByMedia();
		assertEquals(mapMedia.values().toString(), "[14, 16, 15]");
	}
}
