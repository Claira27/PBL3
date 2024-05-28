package database;

import java.util.ArrayList;

import model.BacSy;

public interface DAOInterface<T> {
	ArrayList<T> selectAll();
	
	T selectById(int id);
	
	int insert(T item);
	
	int insertAll(ArrayList<T> ds);
	
	int deleteByID(int id);
	
	int deleteAll(ArrayList<T> ds);
	
	int update(T item);
}
