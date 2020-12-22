package javeCorePractice;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExecutorServiceCallableCompleteableFuture {
	
	public static class Loan{
		Employment employment;
		Property property;
		Employer employer;
		Identification identification;
		public Loan(Employment employment, Property property, Employer employer, Identification identification) {
			this.employment = employment;
			this.property = property;
			this.employer = employer;
			this.identification = identification;
		}
		@Override
		public String toString() {
			return "Loan [employment=" + employment + ", property=" + property + ", employer=" + employer
					+ ", identification=" + identification + "]\n";
		}
		
	}
	public static class Employment{
		String jobTitle;
		LocalDate employedSince;
		double salary;
		public Employment(String jobTitle, LocalDate employedSince, double salary) {
			super();
			this.jobTitle = jobTitle;
			this.employedSince = employedSince;
			this.salary = salary;
		}
		@Override
		public String toString() {
			return "Employment [jobTitle=" + jobTitle + ", employedSince=" + employedSince + ", salary=" + salary + "]\n";
		}
	}
	public static class Property{
		String streetAddress;
		String city;
		double purchasedPrice;
		public Property(String streetAddress, String city, double purchasedPrice) {
			super();
			this.streetAddress = streetAddress;
			this.city = city;
			this.purchasedPrice = purchasedPrice;
		}
		@Override
		public String toString() {
			return "Property [streetAddress=" + streetAddress + ", city=" + city + ", purchasedPrice=" + purchasedPrice
					+ "]\n";
		}
	}
	public static class Employer{
		String employerName;
		String buinessType;
		int numOfEmployees;
		public Employer(String employerName, String buinessType, int numOfEmployees) {
			super();
			this.employerName = employerName;
			this.buinessType = buinessType;
			this.numOfEmployees = numOfEmployees;
		}
		@Override
		public String toString() {
			return "Employer [employerName=" + employerName + ", buinessType=" + buinessType + ", numOfEmployees="
					+ numOfEmployees + "]\n";
		}
	}
	public static class Identification{
		String firstname;
		String lastName;
		LocalDate dob;
		Long ssn;
		public Identification(String firstname, String lastName, LocalDate dob, Long ssn) {
			super();
			this.firstname = firstname;
			this.lastName = lastName;
			this.dob = dob;
			this.ssn = ssn;
		}
		@Override
		public String toString() {
			return "Identification [firstname=" + firstname + ", lastName=" + lastName + ", dob=" + dob + ", ssn=" + ssn
					+ "]";
		}
	}
	
	public static class ReportGenerator<T> implements Callable<String> {
		T report;
		ReportGenerator(T reportType){
			this.report = reportType; 
		}
		@Override
		public String call() throws Exception {
			Thread.sleep(10000);
			return (String) report.toString();
		}
	}
	
	static ReportGenerator<Employment> employmentGenerator;
	static ReportGenerator<Property> propertyGenerator;
	static ReportGenerator<Employer> employerGenerator;
	static ReportGenerator<Identification> identificationGenerator;
	
	public static class ReportSupplier<T> implements Supplier<String>{
		T report;
		ReportSupplier(T reportType){
			this.report = reportType; 
		}
		@Override
		public String get() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return (String) report.toString();
		}
	}
	
	static Supplier<String> employmentSupplier;
	static Supplier<String> propertySupplier;
	static Supplier<String> employerSupplier;
	static Supplier<String> identificationSupplier;


	public static void initThreads(Loan loan) {
	    employmentGenerator = new ReportGenerator<Employment>(loan.employment);
		propertyGenerator = new ReportGenerator<Property>(loan.property);		
		employerGenerator = new ReportGenerator<Employer>(loan.employer);
		identificationGenerator = new ReportGenerator<Identification> (loan.identification);
	}
	
	public static void initSuppliers(Loan loan) {
		employmentSupplier = (Supplier<String>) new ReportSupplier<Employment>(loan.employment);
		propertySupplier = (Supplier<String>) new ReportSupplier<Property>(loan.property);		
		employerSupplier = (Supplier<String>) new ReportSupplier<Employer>(loan.employer);
		identificationSupplier = (Supplier<String>) new ReportSupplier<Identification> (loan.identification);
	}
	
	public static void synchronousReportGenerator() {
		List<String> report = new ArrayList<>();
		Future<String> future;
		Duration duration = null;
		ExecutorService synExecutor = Executors.newSingleThreadExecutor();
		try {
			LocalTime t1 = LocalTime.now();
			future = synExecutor.submit(employmentGenerator);
			report.add(future.get());//blocking 
			
			future = synExecutor.submit(propertyGenerator);
			report.add(future.get());//blocking 
			
			future = synExecutor.submit(employerGenerator);
			report.add(future.get());//blocking 
			
			future = synExecutor.submit(identificationGenerator);
			report.add(future.get());//blocking 
			LocalTime t2 = LocalTime.now();
			duration = Duration.between(t1,t2);
		}catch(InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}finally {
			String syncReport = report.stream().reduce("Loan Report : \n", String::concat);
			System.out.println(String.format("Synchronous report : (duration %s)", duration));
			System.out.println(syncReport);
			synExecutor.shutdown();
		}
	}
	
	public static void AsynchronousReportGenerator() {
		Future<String> future;
		List<String> report = new ArrayList();
		Duration duration = null;
		ExecutorService executor = Executors.newFixedThreadPool(4);
		try {
			LocalTime t1 = LocalTime.now();
			List<Future<String>> resultPromisses = executor.invokeAll(
				Arrays.asList(employmentGenerator,
							propertyGenerator, 
							employerGenerator, 
							identificationGenerator));
		
			executor.shutdown();//stop accepting new tasks and shut down after all running threads finish their current work.
			report  = resultPromisses.stream().map(f-> {
				try {
					return f.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					return " error in execution";
				}
			}).collect(Collectors.toList());
			LocalTime t2 = LocalTime.now();
			duration = Duration.between(t1,t2);
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}finally {
			String asyncReport = report.stream().reduce(" Report : \n", String::concat);
			System.out.println(String.format("Asynchronous report : (duration %s)", duration));
			System.out.println(asyncReport);
		}
	}
	
	
	public static void AsynchronousReportGeneratorCompleteableFuture() {
	
	
	/*CompletableFuture<Void> result = CompletableFuture
			.allOf(employmentSupplier, propertySupplier, employerSupplier, identificationSupplier);
	
	result.thenApply(  f -> {
		completeableFutures.stream(future -> futre.get()).
		}
	);*/

	}
	
	

	public static void main(String[] args) {
		Employment emp = new Employment( "CEO", LocalDate.parse("2013-01-16"), 200000.00);
		Property prop = new Property( "67 millcrest cresent", "North York", 650000.00);
		Employer employer = new Employer("rxNetwrok", " High-tech", 32);
		Identification id = new Identification("John", "Tturlow", LocalDate.of(1984,02, 11), 1265783L);
		Loan loan = new Loan(emp, prop, employer, id);
		initThreads(loan);
		synchronousReportGenerator();
		System.out.println();
		AsynchronousReportGenerator();
		
		initSuppliers(loan);
	}

}
