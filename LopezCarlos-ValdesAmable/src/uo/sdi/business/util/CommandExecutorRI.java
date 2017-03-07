package uo.sdi.business.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.sdi.persistence.util.Jpa;

public class CommandExecutorRI {

	public Object execute(Command command) throws BusinessException {

		// Un mecanico no se podrá borrar si ya ha hecho intervenciones o
		// tiene averias asignadas
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();

		Object res = null;
		
		try {
			res = command.execute();

		} catch (BusinessException bex) {
			if( trx.isActive() ){
				trx.rollback();
			}
			throw bex;
		}
		catch (PersistenceException pex) {
			trx.rollback();
			throw pex;
		}finally {
			if(em.isOpen())em.close();
		}

		trx.commit();
		
		return res;
		
	}

}
