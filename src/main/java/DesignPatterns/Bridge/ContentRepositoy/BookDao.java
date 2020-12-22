package DesignPatterns.Bridge.ContentRepositoy;

import DesignPatterns.Bridge.ContentRepositoy.Content.Book;
import DesignPatterns.Bridge.ContentRepositoy.Content.Movie;

public class BookDao extends ContentDAO{

	public BookDao(RepositoryInterface repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	public String getId(Content content) {
		Book book = (Book) content;
		return book.getIsbn();
	}

}
