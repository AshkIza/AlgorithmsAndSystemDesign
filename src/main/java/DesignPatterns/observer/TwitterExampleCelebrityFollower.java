package DesignPatterns.observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/* https://codepumpkin.com/observer-design-pattern/
 * 
 * each subject registers and keeps track of all observers listening to it
 * in case of any state change, subject notify all observers by looping through all observers , 
 * 		and call handler method on each observer
 * 
 * each observer implements handler method which implements how to handle the subject state change
 * */
public class TwitterExampleCelebrityFollower {
	
	/* we can have multiple followers (observers) following a celebrity (subject/topic)
	 * we can have a follower (observer) following 2 celebrities (subjects/topics)
	 * */
	
	
	public interface Subject {
		void addObserver(Observer observer);//add a follower
		void detachObserver(Observer observer);//unfollow a follower
		void notifyObservers();// when the subject state changes 
	}
	
	public interface Observer {
		void handleSubject(Subject subject);
	}
	
	public static class Celebrity implements Subject {
		private String name;
		private Deque<String> tweetHistory = new ArrayDeque<>();
		private List<Observer> followers = new ArrayList<>();// all followers follwing this celebrity
		
		Celebrity(String name){
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public String getLastTweet() {
			return tweetHistory.getLast();
		}

		public void tweet(String tweetmessage) {
			tweetHistory.add(tweetmessage);
			notifyObservers();
	        System.out.println();
		}

		@Override
		public void addObserver(Observer observer) {
			followers.add(observer);
		}

		@Override
		public void detachObserver(Observer observer) {
			followers.remove(observer);
		}

		@Override
		public void notifyObservers() {
			followers.forEach(observer -> observer.handleSubject(this));
		}
	}
	
	public static class Follower implements Observer {
		String name;
		public Follower(String name) {
			this.name = name;
		}
		
		// this is the subject/event handler like EventListener/ EventHandler 
		// by passing in the subject as argument you can listen to different subject
		@Override
		public void handleSubject(Subject subject) {
			Celebrity celebrity = (Celebrity) subject;
			System.out.println(name + " seeing a new tweet from " 
					+ celebrity.getName() + " : " + celebrity.getLastTweet());
		}
	}

	public static void main(String[] args) {
		Celebrity selenaGomez = new Celebrity("Selena Gomez");
		Celebrity rickyMartin = new Celebrity("Ricky Martin");
		
		Follower john = new Follower("John");
		Follower jack = new Follower("Jack");
		Follower bob = new Follower("Bob");
		Follower rob = new Follower("Rob");
		Follower raj = new Follower("Roj");
		Follower bagher = new Follower("Bagher");

		
		// add followers 
		selenaGomez.addObserver(john);
		selenaGomez.addObserver(jack);
		selenaGomez.addObserver(bagher);
		
		rickyMartin.addObserver(bob);
		rickyMartin.addObserver(rob);
		
		selenaGomez.addObserver(raj);
		rickyMartin.addObserver(raj);
		
		// celebrities tweeting -- followers get notified ASAP
		selenaGomez.tweet(" Good Morning from Selena");
		selenaGomez.tweet(" I feel happy today - from spain ");
		rickyMartin.tweet(" Good night fans. today's concert is in New york ");
		selenaGomez.tweet(" Thanks for your messages ");
		
		selenaGomez.detachObserver(john);
		selenaGomez.tweet(" I just received horribale message from a fan - he is removed! ");

	}

}
