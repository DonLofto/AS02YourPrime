package yourPrime;
import java.io.*;
import java.util.List;

public class Subscriber {
	private String userID;
	private String name;
	private String password;
	private MyMedia myMedia;
	private String path;

	public Subscriber(String userID, String name, String password, MyMedia myMedia) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.myMedia = myMedia;
		this.path = "/Users/patrickloftus/Downloads/AS02YourPrime/src/" + userID + "myMedia.ser";
	}

	public void setUserID(String userId) {
		this.userID = userId;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public double getFees() {
		return this.myMedia.calculateFees();

	}

	public List<?> sort(String orderType) {
		return myMedia.sort(orderType);
	}

	@Override
	public String toString() {
		return (this.name + " Acc. No: " + this.userID + " total charge is " + String.format("%.2f", getFees()) + " for:\n\n" + this.myMedia.toString());
	}

	public MyMedia getMyMedia() {
		return this.myMedia;
	}

	public boolean saveMedia() {
		try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(path))) {
			objectOutput.writeObject(this.myMedia);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public MyMedia getMedia() {
		try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(path))) {
			this.myMedia = (MyMedia) objectInput.readObject();
			return myMedia;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

    public Object getOutstandingFees() {
        return null;
    }
}
