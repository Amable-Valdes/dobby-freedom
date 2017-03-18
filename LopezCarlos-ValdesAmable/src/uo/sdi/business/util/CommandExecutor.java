package uo.sdi.business.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import uo.sdi.persistence.util.Jpa;

/**
 * Patrón Command que nos permite ejecutar los actions del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class CommandExecutor {

	/**
	 * Patrón que nos permite ejecutar clases que implementen 
	 * la interfaz Command usando su método execute.
	 * 
	 * @param command	Clase a ejecutar.
	 * @return	Un objeto, puede ser el que queramos (TaskDTO, lista, etc.).
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
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
