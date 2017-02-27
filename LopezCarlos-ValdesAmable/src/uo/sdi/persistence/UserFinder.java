package uo.sdi.persistence;

import java.util.List;
import javax.persistence.NoResultException;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class UserFinder {

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
	
	/**	Encuentra todos los usuarios en la BBDD
	 * @return	Una lista con todos los objetos User de la BBDD
	 */
	public static List<User> findAll() {
		return Jpa.getManager()
				.createNamedQuery("User.findAll", User.class)
				.getResultList();
	}

}
