package com.bidc.data;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfService {
	private static EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	private EmfService() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
		
}

