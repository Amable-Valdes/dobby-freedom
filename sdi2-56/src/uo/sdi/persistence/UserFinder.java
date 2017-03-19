package uo.sdi.persistence;

import java.util.List;
import javax.persistence.NoResultException;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

/**
 * Clase UserFinder que permite realizar consultas sobre la BBDD y 
 * devuelve objetos User ya persistentes.
 * 
 * @author Amable y Carlos
 *
 */
public class UserFinder {

	/**	Encuentra todos los usuarios en la BBDD
	 * @return	Una lista con todos los objetos User de la BBDD
	 */
	public static List<User> findAll() {
		return Jpa.getManager()
				.createNamedQuery("User.findAll", User.class)
				.getResultList();
	}
	
	/**
	 * Busca en la BBDD un usuario con login igual al pasado como parámetro.
	 * 
	 * @param login	El login del usuario que estamos buscando.
	 * @return	Un objeto User o null si no encontró nada.
	 */
	public static User findByLogin(String login) {
		try{
			return Jpa.getManager()
				.createNamedQuery("User.findByLogin", User.class)
				.setParameter(1, login)
				.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

}
