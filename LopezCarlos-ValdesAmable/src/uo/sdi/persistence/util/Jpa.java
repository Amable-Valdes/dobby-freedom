package uo.sdi.persistence.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Esta clase creada por Alberto le pide al controlador el EntityManager
 * para poder realizar operaciones de JPA.
 * 
 * @author Alberto
 *
 */
public class Jpa {

	private static EntityManagerFactory emf = null;
	private static ThreadLocal<EntityManager> emThread = 
		new ThreadLocal<EntityManager>();
	
	public static EntityManager createEntityManager() {
		EntityManager entityManager = getEmf().createEntityManager();
		emThread.set(entityManager);
		return entityManager;
	}

	public static EntityManager getManager() {
		return emThread.get();
	}

	private static EntityManagerFactory getEmf() {
		if (emf == null){
			emf = jndiFind("java:/ForumJpaFactory");
		}
		return emf;
	}

	private static EntityManagerFactory jndiFind(String name) {
		Context ctx;
		try {
			ctx = new InitialContext();
			
			return (EntityManagerFactory) ctx.lookup(name);
			
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
