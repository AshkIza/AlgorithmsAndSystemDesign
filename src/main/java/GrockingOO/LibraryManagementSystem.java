package GrockingOO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import GrockingOO.LibraryManagementSystem.Catalouge.SEARCHCRITERIA;

public class LibraryManagementSystem {
	
	static final int BOOKS_LIMIT_PER_PERSON = 3;
	static final int MAX_DAY_LIMIT_BORROW = 10;
	
	// system data structure 
	static class Person {
		String name;
		UUID personId;
		LibrarySystem libraryAccess;
	
		public Person(String name, UUID personId, LibrarySystem libraryAccess) {
			this.name = name;
			this.personId = personId;
			this.libraryAccess = libraryAccess;
		}
		public String getName() {
			return name;
		}
		public UUID getPersonId() {
			return personId;
		}
		public LibrarySystem getLibraryAccess() {
			return libraryAccess;
		}
		//either librarian or member can checkout/reserve/return book for the member
		public void checkoutBook(Book book, Member member){
			if(libraryAccess != null) {
				libraryAccess.checkoutBook(member, book);
			}
		}
		
		void reserveBook (Member member, Book book) {
			if(libraryAccess != null) {
				libraryAccess.reserveBook(member, book);
			}
		}
		
		void returnBook (Member member, Book book) {
			if(libraryAccess != null) {
				libraryAccess.returnBook(member, book);
			}
		}
	}
	
	static class Librarian extends Person {

		public Librarian(String name, UUID personId, LibrarySystem libraryAccess) {
			super(name, personId, libraryAccess);
		}
		
		Member registerAccount(String name) {
			if(libraryAccess != null) {
				return libraryAccess.registerAccount(name);
			}
			return null;
		}
		
		
		void addBook(Book book, String ... bookItembarcodes){
			if(libraryAccess != null) {
				libraryAccess.addUpdateBook(book, bookItembarcodes);
			}
		}
	}
	
	static class Member extends Person {
		List<BookItem> checkedOutItems;
		
		Member(String name, UUID personId, LibrarySystem libraryAccess){
			super(name, personId, libraryAccess);
			checkedOutItems = new ArrayList<>();
		}
		public List<BookItem> getCheckedOutItems() {
			return checkedOutItems;
		}
		public void addCheckedOutItems(BookItem bookItem) {
			getCheckedOutItems().add(bookItem);
		}
		public void checkoutBook(Book book){
			checkoutBook(book, this);
		}
		void reserveBook (Book book) {
			reserveBook(this, book);
		}
		void returnBook (Book book) {
			returnBook(this, book);
		}
		void handleNotification(String msg) {
			System.out.println("hey " + getName() + "! " + msg);
		}
	}
	
	static class Book {
		String isbn;
		String title;
		String authur;
		String category;
		Deque<Member> memebersReserving = new ArrayDeque<>();//memebers waiting for this book
		int rackNumber;
		LinkedList<String> bookItemsAvailable = new LinkedList<>();// 0 means next book request go to reserve
		
		public Book(String isbn, String title, String authur, String category, int rackNumber) {
			this.isbn = isbn;
			this.title = title;
			this.authur = authur;
			this.category = category;
			this.rackNumber = rackNumber;
		}

		public String getIsbn() {
			return isbn;
		}
		public String getTitle() {
			return title;
		}
		public String getAuthur() {
			return authur;
		}
		public String getCategory() {
			return category;
		}
		public Deque<Member> getMemebersReserving() {
			return memebersReserving;
		}
		public void addMemebersReserving(Member member) {
			getMemebersReserving().add(member);
		}
		public int getRackNumber() {
			return rackNumber;
		}
		public LinkedList<String> getBookItemsAvailable() {
			return bookItemsAvailable;
		}
		public void setBookItemsAvailable(LinkedList<String> bookItemsAvailable) {
			this.bookItemsAvailable = bookItemsAvailable;
		}
		void notifeyMembers() {
			if(!bookItemsAvailable.isEmpty() && !memebersReserving.isEmpty()) {
				memebersReserving.forEach(mem -> mem.handleNotification(getTitle() + " has become available!"));
			}
		}
	}
	
	static class BookItem {// book copy already checked-out by a member
		String barcode;
		LocalDate checkoutDate;
		Member member;//referencing the Member checking out the book(memberId)
		Book checkedBook;//referencing the Book (isbn)
		public BookItem(String barcode, LocalDate checkoutDate, Member member, Book checkedBook) {
			this.barcode = barcode;
			this.checkoutDate = checkoutDate;
			this.member = member;
			this.checkedBook = checkedBook;
		}
		public String getBarcode() {
			return barcode;
		}
		public LocalDate getCheckoutDate() {
			return checkoutDate;
		}
		public Member getMember() {
			return member;
		}
		public Book getCheckedBook() {
			return checkedBook;
		}
	}
	
	// acts as the data layer
	static class Catalouge {//repository of all the booked available and bookes checked out
		enum SEARCHCRITERIA{
			TITLE, AUTHOR, CATEGORY
		}
		Map<String, Book> books = new HashMap<>();//books saved by their isbn (primary key)
		List<BookItem> checkedOutBooks  = new ArrayList<>();//go through checkout book and notify member if past due
		//indexed fields (for search optimization)
		Map<String, String> searchByTile = new HashMap<>();// title, isbn
		Map<String, List<String>> searchByAuthur = new HashMap<>();//auther, {isbns}
		Map<String, List<String>> searchByCategory = new HashMap<>();//category, {isbns}

		void addUpdateBook(Book book, String ... bookItembarcodes){
			String bookIsbn = book.getIsbn();
			if(!books.containsKey(bookIsbn)) {//new book (update indexed data)
				books.put(book.getIsbn(), book);
				String category = book.getCategory();
				String authur = book.getAuthur();
				searchByTile.put(book.getTitle(), bookIsbn);
				if(!searchByAuthur.containsKey(authur)) {
					searchByAuthur.put(authur, new ArrayList<>());
				}
				searchByAuthur.get(authur).add(bookIsbn);
				
				if(!searchByCategory.containsKey(category)) {
					searchByCategory.put(category, new ArrayList<>());
				}
				searchByCategory.get(category).add(bookIsbn);
			}
			for(String barcode : bookItembarcodes) {
				books.get(bookIsbn).getBookItemsAvailable().add(barcode);
			}
		}
		//remove
		
		Book searchByTile(String title) {
			if(searchByTile.containsKey(title)) {
				String isbn = searchByTile.get(title);
				return books.get(isbn);
			} else {
				return null;
			}
		}
		
		List<Book> searchByAuthur(String authur) {
			if(searchByAuthur.containsKey(authur)) {
				List<String> isbns = searchByAuthur.get(authur);
				return isbns.stream().map(isbn -> books.get(isbn)).collect(Collectors.toList());
			} else {
				return null;
			}
		}
		
		List<Book> searchByCategory(String category) {
			if(searchByCategory.containsKey(category)) {
				List<String> isbns = searchByCategory.get(category);
				return isbns.stream().map(isbn -> books.get(isbn)).collect(Collectors.toList());
			} else {
				return null;
			}
		}
	}
	
	// acts as the service layer
	static class LibrarySystem {// acts as the mediator
		private List<Member> members = new ArrayList<>();
		private Librarian librarian;
		public Catalouge bookInventory = new Catalouge();
		
		public LibrarySystem withLibrarian(String name) {
			UUID uuid = UUID.randomUUID();
			librarian = new Librarian(name, uuid, this);
			return this;
		}
		
		void addUpdateBook(Book book, String ... bookItembarcodes){
			bookInventory.addUpdateBook(book, bookItembarcodes);
		}

		// called by librarian
		Member registerAccount(String name) {
			UUID uuid = UUID.randomUUID();
			Member newMember = new Member(name, uuid, this);
			members.add(newMember);
			return newMember;
		}
		// remove/update account
		
		// gives limited access to the library system for authorized users
		public Optional<Member> scanBarcodelogIn(UUID uuid) {
			return members.stream().filter(member -> uuid.equals(member.getPersonId())).findAny();
		}
		
		List<Book> searchCatalouge(SEARCHCRITERIA searchKeyType, String searchKeyValue){
			switch (searchKeyType) {
				case TITLE :
					Book book = bookInventory.searchByTile(searchKeyValue);
					return book == null ? null : Collections.singletonList(book);
				case AUTHOR :
					return  bookInventory.searchByAuthur(searchKeyValue);
				case CATEGORY :
					return  bookInventory.searchByCategory(searchKeyValue);
				default :
					return null;
			}
		}
		
		// can be called by either member or librarian
		void checkoutBook (Member member, Book book) {
			if(member.getCheckedOutItems().size() >= BOOKS_LIMIT_PER_PERSON) {
				System.out.println(" Member " + member.getName() + " has exceeded limit to borrow books!");
				return;
			}
			if(!book.getBookItemsAvailable().isEmpty()) {
				String barcode = book.getBookItemsAvailable().remove();
				BookItem bookItem = new BookItem(barcode, LocalDate.now(), member, book);
				member.getCheckedOutItems().add(bookItem);
				bookInventory.checkedOutBooks.add(bookItem);
			} else {
				System.out.println(" Book " + book.getTitle() + " NOT available. Request to reserve it!");
			}
		}
		
		// can be called by either member or librarian
		void reserveBook (Member member, Book book) {
			book.memebersReserving.addLast(member);
			System.out.println(" Member " + member.getName() + " reserving Book " + book.getTitle());
		}
		
		// can be called by either member or librarian
		void returnBook (Member member, Book book) {
			Optional<BookItem> bookItem = member.checkedOutItems.stream().filter( item -> item.getCheckedBook().equals(book)).findFirst();
			if(bookItem.isPresent()) {
				member.checkedOutItems.remove(bookItem.get());
				LocalDate currentDate =  LocalDate.now();
				LocalDate dueDate = bookItem.get().getCheckoutDate().plusDays(MAX_DAY_LIMIT_BORROW);
				if(currentDate.isAfter(dueDate)) {
					//issue fine
					long noOfDaysBetween = ChronoUnit.DAYS.between(currentDate, dueDate);
					long fine = 10L * noOfDaysBetween;
					member.handleNotification(" total fine issued (and credited from bank acount): " + fine);
				}
				
				bookInventory.checkedOutBooks.remove(bookItem);
				
				String barcode = bookItem.get().getBarcode();
				book.getBookItemsAvailable().add(barcode);
				book.notifeyMembers();//notify members this book has become available
			}
		}
	}
	

	public static void main(String[] args) {
		LibrarySystem yorkLibrary = new LibrarySystem();
		Librarian librariaan01 = new Librarian("librarianName", UUID.randomUUID(), yorkLibrary);
		
		Member steve = librariaan01.registerAccount("Steve Banon");
		Member judy = librariaan01.registerAccount("Judy Smith");
		Member nicola = librariaan01.registerAccount("Nicola Tesla");
		
		librariaan01.addBook(new Book("ISBN1", "Java8", "Java geek", "programming", 3), 
				"BC-01", "BC-02", "BC-03");// 3 copies of this book
		librariaan01.addBook(new Book("ISBN2", "GCP Fundumentals", "GCP team", "programming", 3), 
				"BC-10", "BC-20");// 2 copies of this book
		librariaan01.addBook(new Book("ISBN3", "Antiagaing guide", "Dr. Allen", "health", 1), 
				"BC-11");
		librariaan01.addBook(new Book("ISBN4", "Canada hike trails", "Gloria chan", "travel", 2), 
				"BC-99");
		librariaan01.addBook(new Book("ISBN5", "Northamerica parks", "Gloria chan", "travel", 2), 
				"BC-97");
		
		// memebers log in and checkout
		UUID steveID = steve.getPersonId();
		UUID judyID = judy.getPersonId();
		UUID nicolaID = nicola.getPersonId();
		UUID unAuthorizedID = UUID.randomUUID();
		
		// login only authorized access (valid UUID exsits in the system)
		Optional<Member> potentialMember;
		potentialMember = yorkLibrary.scanBarcodelogIn(unAuthorizedID);
		if(!potentialMember.isPresent()) {
			System.out.println("unauthorized member, barcode " + unAuthorizedID + " not a valid member barcode");
		}

		// search catalouge,  checkout and reserve
		potentialMember = yorkLibrary.scanBarcodelogIn(steveID);
		if(!potentialMember.isPresent()) {
			System.out.println("unauthorized member, barcode " + steveID + " not a valid member barcode");
		}else {
			Member authorizedMember = potentialMember.get();
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.CATEGORY, "programming")
				.forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.AUTHOR, "Gloria chan")
				.forEach(book -> authorizedMember.checkoutBook(book));
		}
		
		potentialMember = yorkLibrary.scanBarcodelogIn(judyID);
		if(!potentialMember.isPresent()) {
			System.out.println("unauthorized member, barcode " + judyID + " not a valid member barcode");
		}else {
			// search catalouge and try to checkout 4 books
			Member authorizedMember = potentialMember.get();
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Java8").
				forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Canada hike trails").
				forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Canada hike trails").
				forEach(book -> authorizedMember.reserveBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.CATEGORY, "health")
			.forEach(book -> authorizedMember.checkoutBook(book));
		}
		
		
		potentialMember = yorkLibrary.scanBarcodelogIn(nicolaID);
		if(!potentialMember.isPresent()) {
			System.out.println("unauthorized member, barcode " + nicolaID + " not a valid member barcode");
		}else {
			Member authorizedMember = potentialMember.get();
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Java8").
				forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "GCP Fundumentals").
				forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Antiagaing guide").
			forEach(book -> authorizedMember.checkoutBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Antiagaing guide").
				forEach(book -> authorizedMember.reserveBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Canada hike trails").
			forEach(book -> authorizedMember.reserveBook(book));
		}
		
		
		// return books and notification for reserved books
		potentialMember = yorkLibrary.scanBarcodelogIn(steveID);
		if(!potentialMember.isPresent()) {
			System.out.println("unauthorized member, barcode " + steveID + " not a valid member barcode");
		}else {
			Member authorizedMember = potentialMember.get();
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Java8").
				forEach(book -> authorizedMember.returnBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "Canada hike trails").
				forEach(book -> authorizedMember.returnBook(book));
			authorizedMember.getLibraryAccess().searchCatalouge(SEARCHCRITERIA.TITLE, "tom & jerry").
				forEach(book -> authorizedMember.returnBook(book));// book not existed
		}
	}

}
