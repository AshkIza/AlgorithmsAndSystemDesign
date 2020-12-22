package DesignPatterns.observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/*  	https://sourcemaking.com/design_patterns/observer
 *  	https://howtodoinjava.com/design-patterns/behavioral/observer-design-pattern/
 * 1.when to use observer pattern? 
 * Define a one-to-many dependency (broadcast) between objects so that when one object changes state, 
 * 	all its dependents are notified and updated automatically. 
 * 
 * The "View" part of Model-View-Controller.
 * 
 * Mediator and Observer are competing patterns. 
 * The difference between them is that Observer distributes communication by introducing "observer" and "subject" objects, 
 * 		whereas a Mediator object encapsulates the communication between other objects. 
 * We've found it easier to make reusable Observers and Subjects than to make reusable Mediators.
 * 
 * 
 * 2. Real world example of observer pattern
 * + "any social media platform" such as Facebook or twitter.
 * 	 When a person updates his status – all his followers gets the notification.
 * 
 * + In programming, observer pattern is the basis of "message oriented applications".
 *  	When a application has updated it’s state, 
 *  	it notifies the subscribers about updates. Frameworks like HornetQ, JMS work on this pattern.
 *  
 *  + (event handlers) Similarly, Java "UI based" programming, 
 *  	all keyboard and mouse events are handled by it’s listeners objects and designated functions.
 *  	 When user click the mouse, function subscribed to the mouse click event is invoked with
 *  	 all the context data passed to it as method argument.
 * 
 * */
public class NewsPublisherSubscriber {
	
	public interface Subject{// topic/subject
		void registerObserver(Observer observer);
		void notifyobservers(String newsFeed);
	}
	
	public interface Observer{// listener/handler/observer
		void handleSubject(String newsFeed);
	}
	
	public static class NewsPublisher implements Subject{
		List<Observer> observers = new ArrayList<>();
		Queue<String> newsHistory = new ArrayDeque<>();
		
		void publishNews(String news) {
			System.out.println();
			newsHistory.add(news);
			notifyobservers(news);
		}

		@Override
		public void registerObserver(Observer observer) {
			observers.add(observer);			
		}

		@Override
		public void notifyobservers(String newsFeed) {
			observers.forEach(observer -> observer.handleSubject(newsFeed));			
		}
	}
	
	public static class Guardian implements Observer {
		@Override
		public void handleSubject(String newsFeed) {
			System.out.println(" Guardian Breaking news : " + newsFeed);
		}
	}
	
	public static class NYTimes implements Observer {
		@Override
		public void handleSubject(String newsFeed) {
			System.out.println(" NYTimes ** " + newsFeed);
		}
	}
	
	public static class LeMonde implements Observer {
		@Override
		public void handleSubject(String newsFeed) {
			System.out.println(" LeMonde news feed -> " + newsFeed);
		}
	}
	
	public static class ArabNews implements Observer {
		@Override
		public void handleSubject(String newsFeed) {
			System.out.println(" ArabNews, what is happening is Arab world ? " + newsFeed);
		}
	}
	
	public static void main(String[] args) {
		NewsPublisher americasNewsFeed = new NewsPublisher();
		NewsPublisher middleEastNewsFeed = new NewsPublisher();
		
		Guardian guardianReporter = new Guardian();
		NYTimes nyTimesReporter = new NYTimes();
		LeMonde lemondeReporter = new LeMonde();
		ArabNews arabNewsReporter = new ArabNews();
		
		americasNewsFeed.registerObserver(guardianReporter);		
		americasNewsFeed.registerObserver(nyTimesReporter);		
		americasNewsFeed.registerObserver(lemondeReporter);		
		
		middleEastNewsFeed.registerObserver(guardianReporter);		
		middleEastNewsFeed.registerObserver(nyTimesReporter);		
		middleEastNewsFeed.registerObserver(lemondeReporter);
		middleEastNewsFeed.registerObserver(arabNewsReporter);	

		middleEastNewsFeed.publishNews(" Bomb exploded in Beirut");
		middleEastNewsFeed.publishNews(" Turkey found gas in black sea");
		americasNewsFeed.publishNews(" Earthquake in chille killed 200");
		americasNewsFeed.publishNews(" Bank of Canda raised interest rates");
		americasNewsFeed.publishNews(" Trump wins the election");

	}

}
