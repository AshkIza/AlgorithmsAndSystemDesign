package DesignPatterns.Bridge.ContentRepositoy;

import java.util.List;

public interface RepositoryInterface {
	public void save(String id, byte[] bytes);
	public byte[] getById(Integer id);
	public List<Content> getAll();
}