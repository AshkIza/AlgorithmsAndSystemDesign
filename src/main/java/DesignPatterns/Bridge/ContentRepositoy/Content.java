package DesignPatterns.Bridge.ContentRepositoy;

import java.io.Serializable;

public interface Content {
	
	public static class Book implements Content, Serializable {
		private static final long serialVersionUID = 1L;
		private String isbn;
		private String author;
		private String title;
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	}
	
	public static class Movie implements Content, Serializable {
		private static final long serialVersionUID = 1L;
		private String classification;
		private String runtime;
		private String title;
		private String year;
		public String getClassification() {
			return classification;
		}
		public void setClassification(String classification) {
			this.classification = classification;
		}
		public String getRuntime() {
			return runtime;
		}
		public void setRuntime(String runtime) {
			this.runtime = runtime;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		

	}
	
	

}
