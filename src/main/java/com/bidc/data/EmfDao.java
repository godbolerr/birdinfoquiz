/**
 * 
 */
package com.bidc.data;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author 115750
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
	public List<BIDCTransaction> getTransactionsForAccount(String accountId) {
		EntityManager em = EmfService.get().createEntityManager();
		String queryString = "SELECT t FROM " + BIDCTransaction.class.getCanonicalName() + " t  where accountId = '" + accountId + "'" ;
		Query q = em.createQuery(queryString);			
		List<BIDCTransaction> results = q.getResultList();
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
	public <T> boolean delete(Class<T> theClass, String id) {
		EntityManager em = EmfService.get().createEntityManager();
		QRecord t = em.find(QRecord.class, id);
		em.remove(t);
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
	
	

	@Override
	public BIDCAccount getAccountDetails(String customerId) {
		EntityManager em = EmfService.get().createEntityManager();
		String queryString = "select t from BIDCAccount t  where t.customerId = '" + customerId + "'";
		Query q = em.createQuery(queryString);			
		List<BIDCAccount> results = q.getResultList();
		
		return (BIDCAccount) results.get(0);

	}

}
