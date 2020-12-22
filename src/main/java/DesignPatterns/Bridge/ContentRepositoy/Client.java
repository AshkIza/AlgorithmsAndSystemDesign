package DesignPatterns.Bridge.ContentRepositoy;

import java.io.Serializable;

import DesignPatterns.Bridge.ContentRepositoy.Content.Book;
import DesignPatterns.Bridge.ContentRepositoy.Content.Movie;




public class Client {
	
	public static void main(String[] args) {
		Movie movie = new Movie();
		movie.setClassification("Action");
		movie.setTitle("John Wick");
		movie.setRuntime("2:15");
		movie.setYear("2014");
		
		Book book = new Book();
		book.setIsbn("isb-0123");
		book.setAuthor("mark twain");
		book.setTitle("bookTitle");
				
		RepositoryInterface databaseinstance = new DatabaseInterface();
		ContentDAO moviedao = new MovieDao(databaseinstance);
		moviedao.persist(movie);
		
		ContentDAO bookdao = new BookDao(databaseinstance);
		bookdao.persist(book);

		System.out.println("\n  Dealing with 2 contracts(interfaces). \n" + 
				"  Each can have 2 or more implementations. (expect change from both sides)\n" + 
				"Bridge pattern -> decouple abstraction and implementation\n" + 
				"\n" + 
				"Interfaces and abstract classes \n" + 
				"abstractImplementor takes the second interface as composition.\n" + 
				"We can have multiple implementations of abstractImplementor, and second interface\n" + 
				"\n" + 
				"		Abstraction interfaceOne = new RefinedAbstraction01();\n" + 
				"		Implementor interfaceTwo = new ConcreteImplementor02(interfaceOne);\n" + 
				"		interfaceTwo.operation();");
		
		

	}

}
