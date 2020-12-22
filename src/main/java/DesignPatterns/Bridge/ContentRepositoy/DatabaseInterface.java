package DesignPatterns.Bridge.ContentRepositoy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseInterface implements RepositoryInterface{
	Map<String, byte[]> cache = new HashMap<>();

	@Override
	public void save(String id, byte[] bytes) {
		if(!cache.containsKey(id)) {
			cache.put(id, bytes);
			System.out.println("DB persist : " + id + bytes);
		}		
	}

	@Override
	public byte[] getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Content> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
