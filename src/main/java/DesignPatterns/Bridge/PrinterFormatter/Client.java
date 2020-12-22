package DesignPatterns.Bridge.PrinterFormatter;

public class Client {

	public static void main(String[] args) {
		Movie movie = new Movie();
		movie.setClassification("Action");
		movie.setTitle("John Wick");
		movie.setRuntime("2:15");
		movie.setYear("2014");
		
		Formatter textformatter = new PlainTextFormatter();
		Formatter csvformatter = new  CsvFormatter();
		Printer movieprinter = new MoviePrinter(movie);
		String text = movieprinter.print(textformatter);
		System.out.println("movieprinter.print(textformatter)");
		System.out.println(text);
		
		String csv = movieprinter.print(csvformatter);
		System.out.println("movieprinter.print(csvformatter)");
		System.out.println(csv);
		
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
