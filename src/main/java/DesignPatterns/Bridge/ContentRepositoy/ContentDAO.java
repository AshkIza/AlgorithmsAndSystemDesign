package DesignPatterns.Bridge.ContentRepositoy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import DesignPatterns.Bridge.ContentRepositoy.Content.Movie;

public abstract class ContentDAO {
	RepositoryInterface repositoryInterface;
	
	public ContentDAO(RepositoryInterface repositoryInterface) {
		this.repositoryInterface = repositoryInterface;
	}

	public void persist(Content content) {
		repositoryInterface.save(getId(content), getBytes(content));
	}
	
	//public Content fetch(int id);
	
	public abstract String getId(Content content);
	
	public byte[] getBytes(Content content) {
		ByteArrayOutputStream output = new ByteArrayOutputStream(); 
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
			objectOutputStream.writeObject(content);
			objectOutputStream.close();
		}catch(IOException e) {
			
		}
		return output.toByteArray();
	}
}
	
