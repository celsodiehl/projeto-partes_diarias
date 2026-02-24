package br.com.projeto.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("MGUSollusPU");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
