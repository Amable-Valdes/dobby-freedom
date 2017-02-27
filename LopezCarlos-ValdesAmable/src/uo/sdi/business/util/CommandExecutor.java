package uo.sdi.business.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.sdi.persistence.util.Jpa;

public class CommandExecutor {

	public Object execute(Command command) throws BusinessException {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("taskmanager");
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		Object res = null;
		try{
			res = command.execute();
			
			trx.commit();
		} catch(RuntimeException rex) {
			if( trx.isActive() ) trx.rollback();
			throw rex;
		}
		catch (BusinessException bex) {
			if( trx.isActive() ) trx.rollback();
			throw bex;
		} finally {
			if( em.isOpen() ) em.close();
		}
		emf.close();
		return res;
	}

}
