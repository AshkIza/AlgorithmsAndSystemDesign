package DesignPatterns.observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import DesignPatterns.observer.NewsPublisherSubscriber.Observer;
import DesignPatterns.observer.NewsPublisherSubscriber.Subject;
import DesignPatterns.observer.NewsPublisherSubscriber.ArabNews;
import DesignPatterns.observer.NewsPublisherSubscriber.Guardian;
import DesignPatterns.observer.NewsPublisherSubscriber.LeMonde;
import DesignPatterns.observer.NewsPublisherSubscriber.NYTimes;
import DesignPatterns.observer.NewsPublisherSubscriber.NewsPublisher;


/*  Refactoring Observer Design Pattern with Lambdas
  https://www.sourcecodeexamples.net/2018/05/refactoring-observer-design-pattern.html

 * 
 * */
public class NewsPublisherSubscriberLambda {
	
	/* look at NewsPublisherSubscriber for interface definitions
	 * */
	
	static Observer GuardianComsumer = newsFeed -> System.out.println(" Guardian Breaking news (java 8): " + newsFeed);
	static Observer NYTimesConsumer = newsFeed -> System.out.println(" NYTimes ** (java 8)" + newsFeed);
	static Observer LeMondeConsumer = newsFeed -> System.out.println(" LeMonde news feed (java 8) -> " + newsFeed);
	static Observer ArabNewsCinsumer = newsFeed -> System.out.println(" ArabNews, what is happening is Arab world ? (java 8) " + newsFeed);

	
	public static void main(String[] args) {
		NewsPublisher americasNewsFeed = new NewsPublisher();
		NewsPublisher middleEastNewsFeed = new NewsPublisher();
		
		
		americasNewsFeed.registerObserver(GuardianComsumer);		
		americasNewsFeed.registerObserver(NYTimesConsumer);		
		americasNewsFeed.registerObserver(LeMondeConsumer);		
		
		middleEastNewsFeed.registerObserver(GuardianComsumer);		
		middleEastNewsFeed.registerObserver(NYTimesConsumer);		
		middleEastNewsFeed.registerObserver(LeMondeConsumer);
		middleEastNewsFeed.registerObserver(ArabNewsCinsumer);	

		middleEastNewsFeed.publishNews(" Bomb exploded in Beirut");
		middleEastNewsFeed.publishNews(" Turkey found gas in black sea");
		americasNewsFeed.publishNews(" Earthquake in chille killed 200");
		americasNewsFeed.publishNews(" Bank of Canda raised interest rates");
		americasNewsFeed.publishNews(" Trump wins the election");
	}

}
