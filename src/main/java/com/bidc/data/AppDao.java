package com.bidc.data;

import java.util.List;

public interface AppDao {
	
	public <T> List getAll(Class<T> theClass);
	
	public <T> T get(Class<T> theClass,String id);
	
	public <T> boolean delete(Object instance);
	
	public <T> void reset(Class<T> theClass);
	
	public <T> boolean add(Class<T> theClass,Object o);
	
	public <T> boolean update(Class<T> theClass,Object o);
	


}
