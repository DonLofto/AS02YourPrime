package adminSite;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import yourPrime.*;

public class FuncUtil {
	
	private Map<String, Subscriber> userDb = new HashMap<>();
	
	public FuncUtil(Map<String, Subscriber> userDb) {
		this.userDb = userDb;
	}
	
	public Map<String, Subscriber> getUserDb() {
		return userDb;
	}
	
	public void addSubscriber(Subscriber subscriber) {
		
		Supplier<String> userId = () -> generateId().toString();
		String id = userId.get();
		subscriber.setUserID(id);
		userDb.putIfAbsent(id, subscriber);
	}
	
	public Integer generateId() {
		return new Random().nextInt(80000);
	}
	
	public boolean modifyPassword(String userId, String newPassword) {
		Subscriber subscriber = userDb.get(userId);
		subscriber.setPassword(newPassword);
		userDb.replace(userId, subscriber);
		if (userDb.get(userId).getPassword().equals(newPassword))
			return true;
		else
			return false;
	}
	
	public boolean deleteSubscriber(String userId) {
		userDb.remove(userId);
		if (userDb.get(userId) == null)
			return true;
		else
			return false;
	}
	
	// TODO refactor the implementation of the searchSubscriber() method using Java 8 stream
	// and lambda expression. You can also use the existing interfaces if you prefer (but not required).
	//
	public List<Subscriber> searchSubscriber(String keyword) {
			return userDb.values().stream()
					.filter(subscriber -> subscriber.getUserID().contains(keyword) || subscriber.getName().contains(keyword))
					.collect(Collectors.toList());
		}
		// declare a list with type subscriber that will store the search result
		// use your subscriber map (check instance variable declared on top of this class) as stream, and
		// filter your data for matching keywords in user id or name (you can use predicate or lambda expression)
		// and add the result to your list
		// you will need to return this list

		

	
	// TODO refactor the implementation of the total sum of fees calculation using Java 8 streams
	// and lambda expression. You can also use the exisiting interfaces if you prefer (but not required).
	//
	public double calculateOverdueFees() {
		return userDb.values().stream()
				.mapToDouble(Subscriber::getFees)
				.sum();
		// declare a variable type double that will store the result of total summation of fees,
		// use your subscriber map as stream, and include the necessary operations to get total sum using 
		// the subscriber getFees() method (with lambda expersson or method reference
		//
		
	}
	
	// TODO refactor the implementation of the printAllSubscriber() method here with Java 8 stream and method
	// reference. You MUST use the function interface already defined here. The method will also print out the
	// details according to the sort by option - name of the subscriber, and the outstanding fees.
	//
	public void printAllSubscribers(String sortBy) {
		// TIP: 
		// use the function interface as string that you print out
		Map<String, Subscriber> userDb = getUserDb();
		Function<Subscriber, String> details = p -> p.getName() + " with outstanding amount = " + String.format("%.2f", p.getFees());
		
		Stream<Subscriber> stream = userDb.values().stream();
		
		// you need a condition to check for the sortBy argument, use the subscriber map as stream source, and include the necessary 
		// operations to sort (based on the argument type) and print out the string for each subscriber
		
		if (sortBy.equalsIgnoreCase("name")) {
			stream = stream.sorted(Comparator.comparing(Subscriber::getName));
		} else if (sortBy.equalsIgnoreCase("fee")) {
			stream = stream.sorted(Comparator.comparingDouble(Subscriber::getFees));
		} else {
	
			return;
		}
	
		stream.map(details).forEach(System.out::println);
		
	}
	
	
	// TODO create a method to return the average outstanding fees from all accounts using Java 8 stream and lambda expression. 
	//
	public double getAverageOutstanding() {
		Map<String, Double> mapMedia = new HashMap<>();
		List<Subscriber> subscribers = new ArrayList<>(userDb.values());
		return subscribers
			.stream()
			.mapToDouble(Subscriber::getFees)
			.average()
			.orElse(0);
		}

		// declare a variable type double that will store the results of an average operation - you will return this value
		// use subscriber map as stream input and include necessary operations to calculate average
		//
		
	
	
	// TODO create a method to return the outstanding fees from all accounts group by the media type. 
	// You should make use of Java 8 Streams and lambda expression to do this - return map
	//
	public Map<String, Double> getTotalFeesGroupByMedia() {
		// declare a map collection that will store the total fees you generate - check out the generic types
		// for your map as per the method signature. 
		//
		// Check MyMedia class and explore the getters you can call for each media type. 
		// For each subscriber, you need to calculate the total fees for each media type. As you traverse your
		// subscriber map, you will then call the list of each media, and calculate the total.
		// 
		// For e.g., if you have 5 subscribers, you will have 4 list of Movie, Book and Song. So you need to 
		// calculate the total fees for all movies, and then all books and lastly, all songs.
		// 
		// *SECRET TIP: 
		// With Java Stream, lambda expression and method reference, you can do this in 3 lines 
		// (one for each media type). Use subscriber stream to get media, then invidividual list can be 
		// processed as stream (all in a single pipeline) - but don't get hung up on it, use anything that you're
		// comfortable with ! -> the read tip :)
		//
		Map<String, Double> mapMedia = new HashMap<>();
		List<Subscriber> subscribers = new ArrayList<>(userDb.values());
		mapMedia.put("Movie", subscribers
            .stream()
            .flatMap(s -> s.getMyMedia().getMovies().stream())
            .mapToDouble(Movie::getPrice).sum());

    mapMedia.put("Book", subscribers
            .stream()
            .flatMap(s -> s.getMyMedia().getBooks().stream())
            .mapToDouble(Book::getPrice).sum());

    mapMedia.put("Song", subscribers
            .stream()
            .flatMap(s -> s.getMyMedia().getSongs().stream())
            .mapToDouble(Song::getPrice).sum());

    return mapMedia;
}

		
	
	
	// TODO create a method to return the total number of items from all accounts using Java 8 streams and lambda expression.
	// group by the media type. Use the programming logic (idioms) that you've implemented in the getTotalFeesGroupByMedia() 
	// method as a source of inspiration in completing this method.
	//
	public Map<String, Long> getCountGroupByMedia() {
		// TIP:
		// This will be exactly like the getTotalFeesByGroupMedia() method above. You just need to return the count
		// instead of the total sum. You will return three values (in a map) for each media type. The generic types
		// assigned to the map collection are a good clue to figure out the operators that you will use to construct
		// your stream pipeline/
		//
		// *SECRET TIP:
		// Don't reinvent the wheel, use what you already created above - just replace the operator
		//
		Map<String, Long> mapMedia = new HashMap<>();
		List<Subscriber> subscribers = new ArrayList<>(userDb.values());
	
		subscribers.stream()
        .flatMap(s -> Stream.of(
            new AbstractMap.SimpleEntry<>("Movie", s.getMyMedia().getMovies().size()),
            new AbstractMap.SimpleEntry<>("Book", s.getMyMedia().getBooks().size()),
            new AbstractMap.SimpleEntry<>("Song", s.getMyMedia().getSongs().size())
        ))
        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
        .forEach((mediaType, count) -> mapMedia.put(mediaType, count));
    
    return mapMedia;
	}
	
		
}
	


