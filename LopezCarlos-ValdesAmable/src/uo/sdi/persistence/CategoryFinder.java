package uo.sdi.persistence;

import java.util.List;

import javax.persistence.NoResultException;

import uo.sdi.model.Category;
import uo.sdi.persistence.util.Jpa;

/**
 * Clase CategoryFinder que permite realizar consultas sobre la BBDD y 
 * devuelve objetos Category ya persistentes.
 * 
 * @author Amable y Carlos
 *
 */
public class CategoryFinder {

	/**
	 * Lista todas las Category de de la BBDD.
	 * 
	 * @return	Todas las Category d la BBDD.
	 */
	public static List<Category> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Category.findAll", Category.class)
				.getResultList();
	}

	public static Category findByUserAndName(Long id, String categoryName) {
		try{
			return Jpa.getManager()
				.createNamedQuery("Category.findByUserAndName", Category.class)
				.setParameter(1, id)
				.setParameter(2, categoryName)
				.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public static Category findById(Long categoryId) {
		try{
			return Jpa.getManager()
				.createNamedQuery("Category.findById", Category.class)
				.setParameter(1, categoryId)
				.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	/**
	 * Lista todas las Category de un usuario de la BBDD.
	 * 
	 * @param loginUser	El login del usuario del que queremos las Category.
	 * @return	Una lista de Category
	 */
	public static List<Category> findByUser(String loginUser) {
		return Jpa.getManager()
				.createNamedQuery("Category.findByUser", Category.class)
				.getResultList();
	}

}
