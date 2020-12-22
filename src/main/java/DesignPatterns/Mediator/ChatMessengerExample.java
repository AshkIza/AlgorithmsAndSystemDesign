package DesignPatterns.Mediator;

import java.util.ArrayList;
import java.util.List;

/* https://www.journaldev.com/1730/mediator-design-pattern-java
 * 
 *  mediators ACTS AS A ROUTER between colleagues
 colleagues talk to each other through mediator,  colleague01 --> mediator --> colleague02 
 mediators knows and is in charge of all the communication between colleague 
 
 
 * */
public class ChatMessengerExample {
	
	public enum UserTypeEnum {
		INTERNAL, SUPPORT
	}
	
	// Colleague
	public static abstract class User {
		ChatInterface mediator;
		String userName;
		UserTypeEnum type;
		User (String userName, ChatInterface mediator){
			this.userName = userName;
			this.mediator = mediator;
			mediator.addUser(this);
		}
		
		public void send(String chatMsg) {
			System.out.println(userName + " sends the message : " + chatMsg);
			mediator.send(this, chatMsg);
		}
		
		public void receive(String chatMsg) {
			System.out.println(userName + " received the message : " + chatMsg);
		}
	}
	
	public static class InternalUser extends User{
		InternalUser(String userName, ChatInterface mediator) {
			super(userName, mediator);
			this.type = UserTypeEnum.INTERNAL;
		}
		
		public void sendToInternals(String chatMsg) {
			System.out.println(userName + " sends internal message : " + chatMsg);
			mediator.sendToInternals(this, chatMsg);
		}
	}
	
	public static class SupportUser extends User {
		SupportUser(String userName, ChatInterface mediator) {
			super(userName, mediator);
			this.type = UserTypeEnum.SUPPORT;
		}
	}
	
	
	// Mediator
	public static interface ChatInterface {
		public void send(User user, String chatMessage);
		public void sendToInternals(User user, String chatMessage);
		public void addUser(User user);
	}
	
	// concrete Mediator
	public static class ChatMessenger implements ChatInterface{
		List<User> users;
		
		ChatMessenger(){
			users = new ArrayList<>();
		}
		
		@Override
		public void addUser(User user) {
			users.add(user);
		}

		@Override
		public void send(User user, String chatMessage) {
			for(User itemUser : users) {
				if(itemUser != user) {
					itemUser.receive(chatMessage);
				}
			}
		}

		@Override
		public void sendToInternals(User user, String chatMessage) {
			for(User itemUser : users) {
				if(itemUser != user && itemUser.type == UserTypeEnum.INTERNAL) {
					itemUser.receive(chatMessage + " (internal message)");
				}
			}			
		}
	}
	
	public static void main(String[] args) {
		System.out.println( " mediators ACTS AS A ROUTER between colleagues" );
		System.out.println( " colleagues talk to each other through mediator,  colleague01 --> mediator --> colleague02 " );
		System.out.println( " mediators knows and is in charge of all the communication between colleague \n" );

		ChatInterface chatMessenger = new ChatMessenger();
		
		InternalUser internal01 = new InternalUser("internal01", chatMessenger);
		InternalUser internal02 = new InternalUser("internal02", chatMessenger);
		InternalUser internal03 = new InternalUser("internal03", chatMessenger);
		
		SupportUser support01 = new SupportUser("support01", chatMessenger);
		SupportUser support02 = new SupportUser("support02", chatMessenger);
		SupportUser support03 = new SupportUser("support03", chatMessenger);
		SupportUser support04 = new SupportUser("support04", chatMessenger);

		internal02.sendToInternals("who can work on Sunday?");
		support04.send(" I have an issue with my product ");
		
	}

}
