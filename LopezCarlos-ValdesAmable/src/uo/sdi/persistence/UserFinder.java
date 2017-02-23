package uo.sdi.persistence;

import javax.persistence.NoResultException;

import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class UserFinder {

	public static Object findByLogin(String login) {
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
