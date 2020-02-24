package hr.martinovic.FakeStagram.jobs;

public class SingletonText {

	private static SingletonText instance = new SingletonText();

	private SingletonText(){}

	public static SingletonText getInstance(){
		return instance;
	}

	public String outputMessage() {
		return "These are the images we have so far";
	}

	public String outputMessageEmpty() {
		return "There are currently no images in the database";
	}

}