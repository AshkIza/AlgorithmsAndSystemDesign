package DesignPatterns.Bridge.ContentRepositoy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import DesignPatterns.Bridge.ContentRepositoy.Content.Movie;

public class MovieDao extends ContentDAO{

	public MovieDao(RepositoryInterface repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	public String getId(Content content) {
		Movie movie = (Movie) content;
		return movie.getTitle();
	}

}
