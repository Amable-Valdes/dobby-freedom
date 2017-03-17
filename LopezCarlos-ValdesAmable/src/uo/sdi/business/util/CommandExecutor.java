package uo.sdi.business.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import uo.sdi.persistence.util.Jpa;

public class CommandExecutor {

	public Object execute(Command command) throws BusinessException {
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		Object res = null;
		try{
			res = command.execute();
			
			trx.commit();
		} catch(RuntimeException rex) {
			rex.printStackTrace();
			if( trx.isActive() ) trx.rollback();
			throw rex;
		}
		catch (BusinessException bex) {
			if( trx.isActive() ) trx.rollback();
			throw bex;
		} finally {
			if( em.isOpen() ) em.close();
		}
		return res;
	}

}
