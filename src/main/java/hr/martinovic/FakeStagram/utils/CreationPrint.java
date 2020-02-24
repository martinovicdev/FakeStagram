package hr.martinovic.FakeStagram.utils;
import java.util.Date;
import hr.martinovic.FakeStagram.model.Users;

public class CreationPrint {

	public static String generateText (Users user) {
		Date date = new Date();
		date.getTime();
		return "New entry " + user.getUsername() + " was created at " + date.toString();
	}

}