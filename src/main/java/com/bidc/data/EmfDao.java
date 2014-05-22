/**
 * 
 */
package com.bidc.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Ravi
 *
 */
public enum EmfDao implements AppDao {
	
	INSTANCE;

	@Override
	public <T> List getAll(Class<T> theClass) {
		EntityManager em = EmfService.get().createEntityManager();
		String queryString = "select t from " + theClass.getCanonicalName()+ " t ";
		Query q = em.createQuery(queryString);			
		 List<T> results = q.getResultList();
		 results.size();
		 return results;
	}
	

	@Override
	public <T> T get(Class<T> theClass, String id) {

		EntityManager em = EmfService.get().createEntityManager();
		T t = em.find(theClass, id);
		em.close();
		return t;
	}

	@Override
	public <T> boolean delete(Object intance) {
		EntityManager em = EmfService.get().createEntityManager();
		em.remove(em.merge(intance));
		em.close();
		return true;
	}

	@Override
	public <T> void reset(Class<T> theClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> boolean add(Class<T> theClass, Object instance) {
	
		EntityManager em = EmfService.get().createEntityManager();
		
		AppEntity entity = (AppEntity) instance;
		entity.setActive(true);
		
		if ( em.contains(entity)){
			em.remove(entity);
			em.flush();
		}
		em.persist(instance);
		em.close();

		return true;
	}
	
	@Override
	public <T> boolean update(Class<T> theClass, Object instance) {
	
		EntityManager em = EmfService.get().createEntityManager();
		
		em.merge(instance);
		
		em.close();
	
		return true;
	}
	

}
